package bms;

import java.util.Scanner;

public class MainView {
	public MainView() {
		Scanner sc = new Scanner(System.in);
		int money = 0;
		//BankManager 클래스에 있는 session 객체를 편하게 쓰기 위해 따로 변수에 담기
		Bank session = BankManager.session;
		
		while(true) {
			System.out.println("1. 입금하기\n2. 출금하기\n3. 잔액보기\n4. 로그아웃");
			int choice = sc.nextInt();
			if(choice == 4) {
				System.out.println("다음에 다시 만나요~");
				//로그아웃이기 때문에 로그인 성공한 정보를 담는 session을 초기화
				//지역변수 session은 MainView 생성자 호출이 끝남과 동시에 자동으로 사라지므로
				//실제 로그인 정보를 저장해놓은 BankManager.session 만 초기화한다.
				BankManager.session = null;
				break;
			}
			//session 객체는 업캐스팅 하며 저장했던 객체를 그대로 꺼내서 담아준 것이므로 업캐스팅 되어있는 상태이다.
			//따라서 오버라이딩 한 메소드는 새로운 내용으로 수행하게 된다.(자식에서 추가된 내용이 없으므로 굳이 다운캐스팅 안 해도 됨)
			//어떤 은행 객체가 업캐스팅 되어있는지에 따라 특정 메소드는 새로운 내용으로 수행
			switch(choice) {
			case 1:
				System.out.print("입금하실 금액 : ");
				money = sc.nextInt();
				session.deposit(money);
				break;
			case 2:
				System.out.print("출금하실 금액 : ");
				money = sc.nextInt();
				if(!session.withdraw(money)) {
					System.out.println("저런~ 안타까운 잔액이네요~");
				}
				break;
			case 3:
				session.show();
				break;
			}
		}
	}
}





