package day02;

import java.util.Scanner;

public class IfTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int num = sc.nextInt();
		
		String result = "";
		if(num > 0) {
			result = "양수입니다.";
		}
		else if(num < 0) {
			result = "음수입니다.";
		}
		else {
			result = "0입니다.";
		}
		
		System.out.println(result);
		
	}
}







