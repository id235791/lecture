package day01;

import java.util.Scanner;

public class InputTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("당신의 이름은 : ");
		String name = sc.next();
		
		//이곳 밑으로는 사용자가 이미 입력을 해서
		//name이라는 변수에 특정 값이 담겨있다고 가정하고 개발한다.
		System.out.println(name+"님 안녕하세요~");
		
		
		System.out.print("주소를 입력하세요 : ");
		sc.nextLine();
		String addr = sc.nextLine();
		
		System.out.println("사는곳 : "+addr);
		
		
		System.out.print("당신의 나이는 : ");
		int age = sc.nextInt();
		
		System.out.println("5년 뒤 나이 : "+(age+5));
	}
}