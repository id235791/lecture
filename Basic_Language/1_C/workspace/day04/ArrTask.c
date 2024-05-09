#include<stdio.h>
void main() {
	/*
	int arData[5] = { 10,20,30,40,50 };
	int* ptr = arData;
	printf("%d\n", sizeof(arData));	//배열은 연결된 방들의 총 크기
	printf("%d\n", sizeof(ptr));	//포인터변수는 무조건 4바이트
	*/

	//10칸짜리 배열 만들어서 1~10까지 for문을 이용해서 넣고
	//다시 출력하기 int arData[10];
	int arData[10];
	for (int i = 0; i < 10; i++) {
		arData[i] = i + 1;
	}
	for (int i = 0; i < 10; i++) {
		printf("%d\n", *(arData+i));
	}


	//10칸짜리 배열 만들어서 10~1까지 for문을 이용해서 넣고
	//다시 출력하기
	for (int i = 0; i < 10; i++) {
		arData[i] = 10 - i;
	}
	for (int i = 0; i < 10; i++) {
		printf("%d\n", arData[i]);
	}

	//다섯개의 정수 입력받아서 총합 구하기
	//1번째 정수 : 
	//2번째 정수 : 
	int arData2[10];
	int len = sizeof(arData2) / sizeof(int);
	for (int i = 0; i < len; i++) {
		printf("%d번째 정수 : ",i+1);
		scanf_s("%d", &arData2[i]);
	}
	int sum = 0;
	for (int i = 0; i < len; i++) {
		sum += arData2[i];
	}
	printf("총합 : %d\n", sum);
	printf("평균 : %.2f\n", sum /(double)len);
	
}