package bms;

import java.util.Scanner;

public class JoinView {
	public JoinView() {
		//화면구현
		Scanner sc = new Scanner(System.in);
		System.out.println("1. 국민은행\n2. 신한은행\n3. 우리은행");
		int bankChoice = sc.nextInt();
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		//처리
		BankManager manager = new BankManager();
		//처리하는 로직은 코드가 길고 이곳은 화면만 구현하는 곳이기 때문에 따로 관리한다.
		//가입 성공시 account : 랜덤한 계좌번호 / 가입 실패시 account : null
		String account = manager.join(bankChoice, name, pw);
		if(account != null) {
			System.out.println(name+"님 계좌 개설을 환영합니다~");
			System.out.println("계좌번호 : "+account);
		}
		else {
			System.out.println("알 수 없는 오류 발생 / 계좌 개설 실패!");
		}
		
	}
	
	void f() {
		
	}
}
