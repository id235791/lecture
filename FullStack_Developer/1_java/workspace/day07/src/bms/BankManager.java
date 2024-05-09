package bms;

import java.util.Random;

public class BankManager {
	static Bank[][] arUser = new Bank[3][1000];
	static int[] arCnt = {0,0,0};
	//로그인 성공한 정보를 담는 공간
	static Bank session = null;
	
	String join(int bankChoice, String name, String pw) {
		//bankChoice : 1(국민은행) - 5자리, 2(신한은행) - 6자리, 3(우리은행) - 7자리
		//계좌 개설에 관한 로직이 작성되는 구간
		
		//랜덤한 계좌번호를 생성하는 과정은 여러번 사용되기 때문에 따로 메소드로 만들어서 관리
		String account = makeAccount(bankChoice);
		
		//bankChoice(선택한 은행)에 따라 회원수를 따로 세고있다.(arCnt배열의 각 방)
		//그 은행에 가입된 회원 수만큼 반복을 돌면서 중복을 검사해야 하기 때문에
		//반복 횟수는 arCnt[bankChoice-1] 방에 있는 값
		for (int i = 0; i < arCnt[bankChoice-1]; i++) {
			//중복된 계좌번호를 찾은 경우
			if(arUser[bankChoice-1][i].account.equals(account)) {
				//기존에 생성했던 임의의 계좌번호 대신 새롭게 생성
				account = makeAccount(bankChoice);
				//새롭게 만든 계좌번호가 중복검사를 지나왔던 계좌번호와 똑같은 경우도 있을 수 있음
				//반복문을 0부터 다시 돌며 그 은행의 첫번째 회원부터 재 검사를 진행해야 한다.
				//i를 -1로 해주면 증감식을 통해 0으로 바뀌면서 다음 반복을 진행하게 되고
				//그 말은 for문을 처음부터 다시 돈다는 뜻이다.
				i = -1;
			}
		}
		//위의 for문에서 중복검사를 모두 통과했다면, i값이 정상적으로 증가되어 for문이 정상적으로 종료된다.
		//선택한 은행이 뭐든간에 일단 세 은행 객체 전부 생성해서 각 방에 넣어두기
		Bank[] makers = {
			new Kookmin(account, pw, name),
			new Shinhan(account, pw, name),
			new Woori(account, pw, name)
		};
		
		//국민은행(사용자 선택(bankChoice) : 1 / arCnt 방 번호, arUser 행 번호 : 0)
		//사용자가 선택한 bankChoice 보다 1 감소한 값이 해당 은행의 방, 행 번호다
		//(arUser 배열의 0 행은 국민은행 회원 객체들이 담겨있는 행)
		//국민은행의 새로운 회원이 들어가야하는 위치는 arUser[0][현재가입된회원수] 이다.
		//현재 가입된 회원수는 arCnt[0]에 있는 값이므로 최종 위치는 arUser[0][arCnt[0]]
		//선택한 은행에 따라 집어넣어야 하는 객체가 달라지고 그것은 makers 배열에 담겨있다.
		//makers 배열의 알맞은 방에 있는 객체를 2차원 배열의 알맞은 위치에 넣어준다.
		//arUser의 각 방은 Bank 타입이고, makers배열에 있는 객체들은 자식타입이다.
		//대입과 동시에 자동으로 업캐스팅
		arUser[bankChoice-1][ arCnt[bankChoice-1] ] = makers[bankChoice-1];
		//새로운 회원이 가입되었으므로 해당 은행의 회원수는 1 증가
		arCnt[bankChoice-1]++;
		
//		switch(bankChoice) {
//		case 1:
//			arUser[0][arCnt[0]] = new Kookmin(account, pw, name);
//			arCnt[0]++;
//			break;
//		case 2:
//			arUser[1][arCnt[1]] = new Shinhan(account, pw, name);
//			arCnt[1]++;
//			break;
//		case 3:
//			arUser[2][arCnt[2]] = new Woori(account, pw, name);
//			arCnt[2]++;
//			break;
//		}
		//가입 성공했으면 성공한 계좌번호 리턴
		return account;
	}
	
	String makeAccount(int bankChoice) {
		Random r = new Random();
		String account = null;
		
//		account = r.nextInt(9*10의4승)+1*10의4승+"";
//		account = r.nextInt(9*(int)Math.pow(10,bankChoice+3))+(int)Math.pow(10,bankChoice+3)+"";
		switch(bankChoice) {
		case 1:
			//10000~99999
			//0~89999
			account = r.nextInt(90000)+10000+"";
			break;
		case 2:
			account = r.nextInt(900000)+100000+"";
			break;
		case 3:
			account = r.nextInt(9000000)+1000000+"";
			break;
		}
		return account;
	
	}

	public boolean login(String account, String pw) {
		//입력한 계좌번호의 길이는 은행마다 다르다(국민 : 5 / 신한 : 6 / 우리 : 7)
		//-5를 해주면 해당하는 은행의 행, 방 번호가 나온다.
		int idx = account.length()-5;
		
		//arCnt[idx] : 해당 은행의 회원 수
		for (int i = 0; i < arCnt[idx]; i++) {
			//arUser[idx][i] : 그 은행의 모든 회원 순회
			if(arUser[idx][i].account.equals(account)) {
				if(arUser[idx][i].pw.equals(pw)) {
					//현재의 arUser[idx][i]은 로그인 하려고 입력한 계좌번호와 비밀번호가
					//완전히 일치하는 객체 -> 로그인 성공한 그 객체
					//로그인 정보를 저장하는 session에 해당 객체 저장(업캐스팅된 객체를 저장)
					session = arUser[idx][i];
					//성공 리턴
					return true;
				}
			}
		}
		//위의 if문에 들어가지 못했다면 실패 리턴
		return false;
	}
	
	
	
	
}




