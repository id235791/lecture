package day04;

import java.util.Scanner;

public class NestedFor {
	public static void main(String[] args) {
		int[][] arrData = {
				{10,20,30},
				{40,50,60}
		};
		
//		System.out.println(arrData[0][0]);
//		System.out.println(arrData[0][1]);
//		System.out.println(arrData[0][2]);
		
//		System.out.println(arrData[1][0]);
//		System.out.println(arrData[1][1]);
//		System.out.println(arrData[1][2]);
		
//		for (int i = 0; i < 6; i++) {
//			System.out.println(arrData[i/3][i%3]);
//		}
		
//		for (int j = 0; j < 3; j++) {
//			System.out.println(arrData[0][j]);
//		}
//		for (int j = 0; j < 3; j++) {
//			System.out.println(arrData[1][j]);
//		}
		
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < 3; j++) {
//				System.out.println(arrData[i][j]);
//			}
//		}
		
//		-----2단-----
//		2 X 1 = 2
//		2 X 2 = 4
//		...
//		2 X 9 = 18
//		-----3단-----
//		...
//		System.out.println("-----2단-----");
//		for (int j = 1; j <= 9; j++) {
//			System.out.printf("2 X %d = %d\n",j,2*j);
//		}
//		System.out.println("-----3단-----");
//		for (int j = 1; j <= 9; j++) {
//			System.out.printf("3 X %d = %d\n",j,3*j);
//		}
		
		for (int i = 2; i <= 9; i++) {
			System.out.println("-----"+i+"단-----");
			for (int j = 1; j <= 9; j++) {
				System.out.printf("%d X %d = %d\n",i,j,i*j);
			}	
		}
		
		int[][] arrData2 = new int[3][];
		arrData2[0] = new int[5];
		arrData2[1] = new int[3];
		arrData2[2] = new int[7];
		
		Scanner sc = new Scanner(System.in);
		
//		System.out.println("0행의 데이터 입력 시작!");
//		System.out.print("0행 0열 데이터 : ");
//		arrData2[0][0] = sc.nextInt();
//		System.out.print("0행 1열 데이터 : ");
//		arrData2[0][1] = sc.nextInt();
//		System.out.print("0행 2열 데이터 : ");
//		arrData2[0][2] = sc.nextInt();
//		System.out.print("0행 3열 데이터 : ");
//		arrData2[0][3] = sc.nextInt();
//		System.out.print("0행 4열 데이터 : ");
//		arrData2[0][4] = sc.nextInt();
		
//		System.out.println("0행의 데이터 입력 시작!");
//		for (int i = 0; i < 5; i++) {
//			System.out.print("0행 "+i+"열 데이터 : ");
//			arrData2[0][i] = sc.nextInt();
//		}
//		
//		System.out.println("1행의 데이터 입력 시작!");
//		for (int i = 0; i < 3; i++) {
//			System.out.print("1행 "+i+"열 데이터 : ");
//			arrData2[1][i] = sc.nextInt();
//		}
		
		
		for (int j = 0; j < 3; j++) {
			System.out.println(j+"행의 데이터 입력 시작!");
			for (int i = 0; i < arrData2[j].length; i++) {
				System.out.print(j+"행 "+i+"열 데이터 : ");
				arrData2[j][i] = sc.nextInt();
			}
		}
		
		/*
			*****	*		*****	    *		*********
			*****	**		****	   ***		 *******
			*****	***		***		  *****		  *****
			*****	****	**		 *******	   ***
			*****	*****	*		*********	    *
			
			국어점수 그거 여러 학생용으로 만들기
			학생수 입력 -> 과목수, 과목명 입력받기 -> 첫번째 학생부터
			~ n번째 학생까지의 점수 입력받기
			출력은 한 사람당 총점, 평균 / 전체 평균 출력
			각 과목별 평균
		*/
	}
}









