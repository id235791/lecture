package view;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("☆231229 국비반 JAVA 최종 프로젝트 UMS★");
		while(true) {
			System.out.println("1. 회원가입\n2. 로그인\n3. 나가기");
			int choice = sc.nextInt();
			
			if(choice == 1) {
				new JoinView();
			}
			else if(choice == 2) {
				new LoginView();
			}
			else if(choice == 3) {
				System.out.println("다음에 또 오세요~");
				break;
			}
		}
	}
}
