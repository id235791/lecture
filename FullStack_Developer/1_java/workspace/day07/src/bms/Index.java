package bms;

import java.util.Scanner;

//index : 처음으로 시작하는 곳
public class Index {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("☆★☆★☆★BMS 프로그램 입니다★☆★☆★☆");
		while(true) {
			System.out.println("1. 계좌개설\n2. 로그인\n3. 나가기");
			int choice = sc.nextInt();
			if(choice == 3) {
				System.out.println("안녕ㅎl ㄱr세yo...☆");
				break;
			}
			switch(choice) {
			case 1:
				//계좌 개설을 위해서 사용자의 많은 입출력이 있기 때문에
				//화면 구현 코드들을 따로 클래스(JoinView)로 생성해서 관리한다.
				//JoinView 내부의 무언가를 호출하기 위해서는 객체화를 거쳐야 한다.
				//f() 호출하는 과정까지 갈 필요 없이 생성자 내부에 입출력 화면을 구현하는
				//코드들을 작성해 놓고 호출해 사용한다.
//				new JoinView().f();
				new JoinView();
				break;
			case 2:
				new LoginView();
				break;
			}
		}
	}
}





