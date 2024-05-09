package bms;

import java.util.Scanner;

public class LoginView {
	public LoginView() {
		Scanner sc = new Scanner(System.in);
		BankManager manger = new BankManager();
		
		System.out.print("계좌번호 : ");
		String account = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		
		//로그인 처리 메소드 호출
		if(manger.login(account,pw)) {
			//session이라는 HashMap에 현재 "loginUser" 라는 Key로 로그인 성공한 객체가 담겨있는 상태
			//get으로 해당 객체를 꺼내와서 사용
			//session은 Value의 타입이 Object 이기 때문에 저장할 때 업캐스팅이 되며 저장
			//사용할때는 다운캐스팅 해서 사용
			System.out.println(((Bank)BankManager.session.get("loginUser")).name+"님 다시 만나니 반가워요~");
			
			//메인화면
			new MainView();
		}
		else {
			System.out.println("알 수 없는 오류 / 로그인 실패");
		}
	}
}







