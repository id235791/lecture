package bms;

import java.util.Scanner;

public class LoginView {
	public LoginView() {
		Scanner sc = new Scanner(System.in);
		BankManager manager = new BankManager();
		
		System.out.print("계좌번호 : ");
		String account = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		//로그인 처리 메소드 호출
		if(manager.login(account,pw)) {
			//성공이기 때문에 BankManager.session에 특정 객체가 담겨져 있는 상태이므로 내부에서 사용 가능
			System.out.println(BankManager.session.name+"님 다시 보니 반가워요~");
			//메인화면
			new MainView();
		}
		else {
			System.out.println("알 수 없는 오류 / 로그인 실패");
		}
	}
}





