package day02;

import java.util.Scanner;

public class ForTask {
	public static void main(String[] args) {
		//초기식은 i=0, 증감식은 i++
		
		//1부터 10까지 출력하기
//		System.out.println(1);
//		System.out.println(2);
//		System.out.println(3);
//		System.out.println(4);
//		System.out.println(5);
//		System.out.println(6);
//		System.out.println(7);
//		System.out.println(8);
//		System.out.println(9);
//		System.out.println(10);
		
		for(int i=0;i<10;i++){
			System.out.println(i*1+1);
		}
		
		//10부터 1까지 출력하기
		for (int i = 0; i < 10; i++) {
			System.out.println(i*-1+10);
		}
		
		//12부터 3씩 증가하며 13번 출력하기
		for (int i = 0; i < 13; i++) {
			System.out.println(i*3+12);
		}
		
		//100 94 88 ... 28 출력하기
		for (int i = 100; i>22; i-=6) {
			System.out.println(i);
		}
		for (int i = 0; i < 13; i++) {
			System.out.println(i*-6+100);
		}
				
		//1부터 10까지의 총 합 구하기
//		int sum = 0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +10;
		
		int sum = 0;
		
//		sum += 1;
//		sum += 2;
//		sum += 3;
//		sum += 4;
//		...
//		sum += 9;
//		sum += 10;
		
		for (int i = 0; i < 10; i++) {
			sum += i+1;
		}
		System.out.println("총합 : "+sum);
		
		//n 하나 입력받아서 / 1부터 n까지의 총 합 구하기
		Scanner sc = new Scanner(System.in);
		System.out.print("n : ");
		int n = sc.nextInt();
		
		int sum2 = 0;
		for (int i = 0; i < n; i++) {
			sum2 += i+1;
		}
		System.out.println("n까지의 총 합 : "+sum2);
		
		//n, m 입력받아서 n부터 m까지 출력하기
		// %
		//1부터 100까지 중 짝수만 출력하기
		//1부터 100까지 중 3과 5의 공배수만 출력하기
		
		//아스키 코드
		//A부터 F 출력하기
		//1부터 100까지 중 3의 배수와 5의 배수를 출력하되 공배수는 제외하고 출력하기
	
		//AbCdEf...Yz 출력하기
		//A(65+0)		a(97)
		//C(65+2)		b(97+1)
		//E(65+4)		d(97+3)
		
		//중첩반복문 사용하지 않고 구구단 전부 출력하기
		//10진수 숫자 입력받아서 2진수로 출력하기
		//ABCDefghIJKLmnopQRSTuvwxYZ 출력하기
		//AbCDefGHIjklMNOPqrstUVWXYz 출력하기
	}
}
