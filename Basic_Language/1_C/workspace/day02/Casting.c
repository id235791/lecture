//Casting.c
#include<stdio.h>
void main() {
	//아스키 코드 : 각 문자들마다 대응되는 숫자값들이 있다.
	//'A' : 65
	//'a' : 97
	//'0' : 48
	/*
	printf("%d\n", 'A' + 5);
	printf("%f\n", 105 + 0.0);
	*/
	
	int num1 = 0, num2 = 0;
	printf("첫번째 정수 : ");
	scanf_s("%d", &num1);
	printf("두번째 정수 : ");
	scanf_s("%d", &num2);

	//% : 나머지 연산자(a%b - a를 b로 나눈 나머지)
	printf("몫 : %d\n", num1 / num2);
	printf("나머지 : %d\n", num1 % num2);
	printf("나눗셈 결과 : %f\n",(double)num1/num2);
	
}