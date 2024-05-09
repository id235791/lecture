package day02;

import java.util.Scanner;

public class WhileTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//1. while 문
//		String choice = "";
//		while(!choice.equals("0")) {
//			System.out.println("다음 중 프로그래밍 언어가 아닌것은?\n"
//					+ "1. C언어\n2. Java\n3. 망둥어\n4. 파이썬\n0. 나가기");
//			choice = sc.next();
//			
//			if(choice.equals("3")) {
//				System.out.println("정답입니다.");
//			}
//			else if(choice.equals("1") || choice.equals("2") || choice.equals("4")) {
//				System.out.println("오답입니다.");
//			}
//			else if(choice.equals("0")) {
//				System.out.println("안녕히ㄱr세요...☆");
//			}
//			else {
//				System.out.println("잘못 입력하셨습니다.");
//			}
//		}
		
		//2. do ~ while 문
//		String choice = "0";
//		do{
//			System.out.println("다음 중 프로그래밍 언어가 아닌것은?\n"
//					+ "1. C언어\n2. Java\n3. 망둥어\n4. 파이썬\n0. 나가기");
//			choice = sc.next();
//			
//			if(choice.equals("3")) {
//				System.out.println("정답입니다.");
//			}
//			else if(choice.equals("1") || choice.equals("2") || choice.equals("4")) {
//				System.out.println("오답입니다.");
//			}
//			else if(choice.equals("0")) {
//				System.out.println("안녕히ㄱr세요...☆");
//			}
//			else {
//				System.out.println("잘못 입력하셨습니다.");
//			}
//		}while(!choice.equals("0"));
		
		//3. 무한반복
		String choice = "0";
		while(true){
			System.out.println("다음 중 프로그래밍 언어가 아닌것은?\n"
					+ "1. C언어\n2. Java\n3. 망둥어\n4. 파이썬\n0. 나가기");
			choice = sc.next();
			
			if(choice.equals("3")) {
				System.out.println("정답입니다.");
			}
			else if(choice.equals("1") || choice.equals("2") || choice.equals("4")) {
				System.out.println("오답입니다.");
			}
			else if(choice.equals("0")) {
				System.out.println("안녕히ㄱr세요...☆");
				break;
			}
			else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}
