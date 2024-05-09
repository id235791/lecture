package bms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BankManager {
	static ArrayList<ArrayList<Bank>> arUser = null;
	//사용자가 우리 프로그램을 사용하는 동안 계속 유지되어야 하는 정보를 저장하는 공간
	//어떤 값들, 데이터들을 저장할지 알 수 없기 때문에 Value의 타입은 Object
	static HashMap<String, Object> session = null;
	
	public BankManager() {
		//arUser, session 공간은 실제 기능이 호출되기 전까지는 존재하지 않아도 된다.
		//실제 기능이 호출되려면 BankManager 타입의 객체가 생성되어야 하고,
		//따라서 생성자에 arUser, session 객체를 생성해주는 코드를 작성한다.
		//앞으로 많은 BankManager 타입의 객체가 생성될 것인데, 그럴 때마다 arUser와 session이
		//새롭게 생성되면 안된다.(처음에만 생성하고 이후에는 존재하던것 그대로 사용)
		//싱글톤 패턴으로 설계한다.(null 일때만 생성 후 초기화)
		if(arUser == null) {
			BankManager.arUser = new ArrayList<ArrayList<Bank>>();
			BankManager.arUser.add(new ArrayList<Bank>());
			BankManager.arUser.add(new ArrayList<Bank>());
			BankManager.arUser.add(new ArrayList<Bank>());
		}
		if(session == null) {
			BankManager.session = new HashMap<String, Object>();
		}
	}
	
	String join(int bankChoice, String name, String pw) {
		String account = makeAccount(bankChoice);
		
		//이차원 리스트 안에 담긴 소 리스트 중에 내가 선택한 은행에 맞는 리스트를 꺼내서 temp에 임시저장
		ArrayList<Bank> temp = arUser.get(bankChoice-1); 
		
		//그 temp(선택한 은행 리스트)에 담긴 객체의 개수( .size() ) 만큼 반복
		for (int i = 0; i < temp.size(); i++) {
			//객체 하나( temp.get(i) )씩 계좌번호 비교
			if(temp.get(i).account.equals(account)) {
				account = makeAccount(bankChoice);
				i = -1;
			}
		}
		Bank[] makers = {
			new Kookmin(account, pw, name),
			new Shinhan(account, pw, name),
			new Woori(account, pw, name)
		};
		//temp는 사용자가 선택한 은행 리스트를 따로 꺼내놓은 것이기 때문에
		//그 temp에다가 새로운 객체 add()로 추가
		temp.add(makers[bankChoice-1]);
		
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
		int idx = account.length()-5;
		//temp : 로그인 시도하는 계좌에 해당하는 은행 리스트
		ArrayList<Bank> temp = arUser.get(idx);
		//그 은행 리스트에서 Bank 객체 하나씩을 user로 꺼내오며 전체 순회
		for (Bank user : temp) {
			//그 객체(user)의 계좌번호와 비밀번호 비교
			if(user.account.equals(account)) {
				if(user.pw.equals(pw)) {
					//이 때의 user 객체는 내가 로그인 시도하는 그 객체
					//session에 "loginUser" 라는 Key로 저장
					session.put("loginUser", user);
					//성공 리턴
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
}




