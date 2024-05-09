package day03;

import java.util.Scanner;

public class ArTest1 {
	public static void main(String[] args) {
		int data1 = 10;
		int data2 = 5;
		int data3 = -4;
		int data4 = 0;
		int data5 = 7;
		
		int[] arData = {10, 5, -4, 0, 7};
//		System.out.println(arData[0]);
//		System.out.println(arData[1]);
//		System.out.println(arData[2]);
//		System.out.println(arData[3]);
//		System.out.println(arData[4]);
		for (int i = 0; i < 5; i++) {
			System.out.println(arData[i]);
		}
		
//		배열의 index 자리에 i를 쓰자
		arData[0] = 10;
		arData[1] = 20;
		arData[2] = 30;
		arData[3] = 40;
		arData[4] = 50;
		
//		for (int i = 10; i <= 50; i+=10) {
//			arData[???] = i;
//		}
		for (int i = 0; i < 5; i++) {
			arData[i] = i*10+10;
		}
		
//		선언방법 1
		int[] arData2 = {10,1,7,5,14,-4};
//		for문의 반복횟수를 해당하는 배열의 길이로 해놓으면 유지보수에 편리하다.
//		적절한 상황에 사용해야 함
		for (int i = 0; i < arData2.length; i++) {
			System.out.println(arData2[i]);
		}
		
//		선언방법 2
		int[] arData3 = new int[5];
		Scanner sc = new Scanner(System.in);
		
//		System.out.print("정수 : ");
//		arData3[0] = sc.nextInt();
//		System.out.print("정수 : ");
//		arData3[1] = sc.nextInt();
//		System.out.print("정수 : ");
//		arData3[2] = sc.nextInt();
//		System.out.print("정수 : ");
//		arData3[3] = sc.nextInt();
//		System.out.print("정수 : ");
//		arData3[4] = sc.nextInt();
		for (int i = 0; i < arData3.length; i++) {
			System.out.print("정수 : ");
			arData3[i] = sc.nextInt();
		}
		
		for (int i = 0; i < arData3.length; i++) {
			System.out.println(arData3[i]);
		}
	}
}






