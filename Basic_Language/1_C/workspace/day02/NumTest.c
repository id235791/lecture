//NumTest.c
#include<stdio.h>
void main() {
	//두 정수 입력받고 합 구하기
	int num1 = 0, num2 = 0;
	printf("두 정수를 입력하세요(예 : 10,20) : ");
	scanf_s("%d,%d", &num1,&num2);//10,20 | num1 : 10 / num2 : 20
	printf("두 정수의 합 : %d\n", num1 + num2);
}