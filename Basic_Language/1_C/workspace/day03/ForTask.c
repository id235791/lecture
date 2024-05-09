//ForTask.c
#include<stdio.h>
void main() {
	//최대한 for문의 초기식은 int i=0 고정, 증감식은 i++ 고정
	
	//1 ~ 10 까지 출력하기
	/*
	for (int i = 1;i<=10;i++) {
		printf("%d ", i);
	}
	*/
	for (int i = 0; i < 10; i++){	
		printf("%d ", i + 1);
	}
	printf("\n");
	//10 ~ 1 까지 출력하기
	/*
	for (int i = 10;i>0; i--) {
		printf("%d ", i);
	}
	*/
	//0		10
	//1		9
	//2		8
	//i*-1+10
	for (int i = 0; i < 10; i++) {
		printf("%d ", 10 - i);
	}
	printf("\n");
	//입력 하나 받아서 1부터 그 수까지 출력하기
	int num = 0;
	printf("정수 : ");
	scanf_s("%d", &num);
	for (int i = 0; i < num; i++)
	{
		printf("%d ", i + 1);
	}
	printf("\n");

	//1부터 10까지의 합 출력하기
	int sum = 0;
	for (int i = 1; i <= 10; i++) {
		sum += i;
	}
	printf("%d\n", sum);

	//n,m 입력받아서 n부터 m까지 출력하기
	int n = 0,m = 0;
	printf("n : ");
	scanf_s("%d", &n);
	printf("m : ");
	scanf_s("%d", &m);

	for (int i = n; i <= m; i++)
	{
		printf("%d ", i);
	}
	printf("\n");
	//1부터 100까지중 짝수만 출력하기
	for (int i = 1; i <= 100; i++) {
		if (i % 2 == 0) {
			printf("%d ", i);
		}
	}
	printf("\n");
	//1부터 100까지중 3과 5의 공배수 출력하기
	//A부터 F까지 출력하기
	//A(65+0)		a(97) 
	//C(65+2)		b(97+1)
	//E(65+4)		d(97+3)
	//AbCdEf......z 출력하기

	//구구단 for문 한개만 써서 출력하기
	//10진법 숫자 하나 입력받아서 2진법으로 출력하기
}