package day03;

import java.util.Scanner;

public class ArTask {
	public static void main(String[] args) {
//		5칸 빈 배열 생성 후 / 1~5 넣어준 후 / 각 방에 있는 값들 순서대로 출력
		int[] arData1 = new int[5];
//		arData1[0] = 1;
//		arData1[1] = 2;
//		arData1[2] = 3;
//		arData1[3] = 4;
//		arData1[4] = 5;
		for (int i = 0; i < 5; i++) {
			arData1[i] = i+1;
		}
		for (int i = 0; i < arData1.length; i++) {
			System.out.println(arData1[i]);
		}
		
//		5칸 빈 배열 생성 후 10, 7, 4, 1, -2 넣어준 후 순서대로 출력
		int[] arData2 = new int[5];
//		arData2[0] = 10;
//		arData2[1] = 7;
//		arData2[2] = 4;
//		arData2[3] = 1;
//		arData2[4] = -2;
		for (int i = 0; i < 5; i++) {
			arData2[i] = i*-3+10;
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(arData2[i]);
		}
		
//		사용자에게 정수 3개 입력 받아서 전체 출력 후 총 합도 출력
		Scanner sc = new Scanner(System.in);
		int[] arData3 = new int[3];
//		arData3[0] = sc.nextInt();
//		arData3[1] = sc.nextInt();
//		arData3[2] = sc.nextInt();
		int sum = 0;
		for (int i = 0; i < arData3.length; i++) {
			arData3[i] = sc.nextInt();
			//기존의 총 합 구하기 알고리즘은 1~10까지의 합
			//"1~10 번방(i)"의 "값들(arData3[i])"의 합
			sum += arData3[i];
		}
		for (int i = 0; i < arData3.length; i++) {
			System.out.println(arData3[i]);
		}
		System.out.println("총 합 : "+sum);
		
//		1,100,7,35,78,2,17,890,25,6 반복문 사용해서 출력
		int[] arData4 = {1,100,7,35,78,2,17,890,25,6};
		for (int i = 0; i < arData4.length; i++) {
			System.out.println(arData4[i]);
		}
		
//		20칸 빈 배열 생성 후 홀수번째 방은 -2,-4,-6,-8,... 넣고
//		짝수번째 방은 1,3,5,7,... 넣기
		int[] arData5 = new int[20];
//		arData5[0] = 1;
//		arData5[2] = 3;
//		arData5[4] = 5;
//		
//		arData5[1] = -2;
//		arData5[3] = -4;
		for (int i = 0; i < arData5.length; i++) {
			if(i%2 == 0) {
				arData5[i] = i+1;
			}
			else {
				arData5[i] = -i-1;
			}
		}
		for (int i = 0; i < arData5.length; i++) {
			System.out.println(arData5[i]);
		}
		
//		20칸 빈 배열 생성 후 홀수번째 방은 -1,-4,-7,-10,... 넣고
//		짝수번째 방은 2,7,12,17,... 넣기
		int[] arData6 = new int[20];
//		arData6[0] = 2;
//		arData6[2] = 7;
//		arData6[4] = 12;
////	i*5/2+2
//		
//		arData6[1] = -1;
//		arData6[3] = -4;
//		arData6[5] = -7;
////	i*-3/2+0.5
		for (int i = 0; i < arData6.length; i++) {
			if(i%2==0) {
				arData6[i] = (int)((double)i*5/2+2);
			}
			else {
				arData6[i] = (int)((double)i*-3/2+0.5);
			}
		}
		
//		사용자에게 국어점수, 수학점수, 영어점수 입력받은 후 전체 점수 출력하고
//		총점과 평균 출력
//		입력
//		국어 점수 : 
//		수학 점수 : 
//		...
//		출력
//		국어점수 : 100점
//		수학점수 : 80점
//		영어점수 : 90점
//		총점 : 270점
//		평균 : 90.0점
		String[] arSubject = {"국어","수학","영어","JAVA"};
		int[] arScore = new int[arSubject.length];
		int sum2 = 0;
		for (int i = 0; i < arSubject.length; i++) {
			System.out.print(arSubject[i]+" 점수 : ");
			arScore[i] = sc.nextInt();
			sum2 += arScore[i];
		}
		for (int i = 0; i < arSubject.length; i++) {
			System.out.println(arSubject[i]+" 점수 : "+arScore[i]+"점");
		}
		System.out.println("총점 : "+sum2+"점");
		System.out.printf("평균 : %.1f점\n",sum2/(double)arSubject.length);
//		위의 문제에서 사용자에게 과목 수와 과목 명 먼저 입력받고 똑같은 과정 하기
		
	}
}
