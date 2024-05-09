package bms;

import java.util.Scanner;

public class MainView {
	public MainView() {
		Scanner sc = new Scanner(System.in);
		int money = 0;
		//session에 있는 로그인 성공한 객체는 Main에서도 사용할 것이기 때문에 접근해서 꺼내오기
		Bank session = (Bank)BankManager.session.get("loginUser");
		
		while(true) {
			System.out.println("1. 입금하기\n2. 출금하기\n3. 잔액조회\n4. 로그아웃");
			int choice = sc.nextInt();
			if(choice == 4) {
				System.out.println("다음에 다시 만나요~");
				//로그아웃시 session 공간을 지우는게 아니라 session에 "loginUser" 로 저장되어 있는
				//Value를 지워야 한다. 따라서 remove() 메소드를 사용하거나 혹은
				//해당 Key값으로 null값을 새롭게 추가해주어서 Value가 지워진듯한 효과를 내야 한다.
				BankManager.session.remove("loginUser");
//				BankManager.session.put("loginUser", null);
				break;
			}
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
					System.out.println("안타까운 잔액이네요~");
				}
				break;
			case 3:
				session.show();
				break;
			}
		}
	}
}






