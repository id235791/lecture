package day02;

import java.util.Scanner;

public class Quiz {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("다음 중 프로그래밍 언어가 아닌것은?\n"
				+ "1. C언어\n2. Java\n3. 망둥어\n4. 파이썬");
		String choice = sc.next();
		
		String result = "";
//		"문자열1".equals("문자열2") : "문자열1"과 "문자열2"가 일치한다면 true
//		if(choice.equals("3")) {
//			result = "정답입니다.";
//		}
//		//~~거나, ~~또는, ~~혹은 : ||
//		//~~이며, ~~그리고, ~~하고 : &&
//		else if(choice.equals("1") || choice.equals("2") || choice.equals("4")) {
//			result = "오답입니다.";
//		}
//		else {
//			result = "잘못 입력하셨습니다.";
//		}
		switch(choice) {
		case "3":
			result = "정답입니다.";
			break;
		case "1":case "2":case "4":
			result = "오답입니다.";
			break;
		default:
			result = "잘못 입력하셨습니다.";
		}
		
		System.out.println(result);
		
	}
}









