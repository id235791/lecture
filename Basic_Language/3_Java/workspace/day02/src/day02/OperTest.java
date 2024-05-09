package day02;

import java.util.Scanner;

public class OperTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int num = sc.nextInt();
		
		String result = num>0 ? "양수입니다." :
			(num < 0 ? "음수입니다." : "0입니다.");
		
		System.out.println(result);
		
	}
}
