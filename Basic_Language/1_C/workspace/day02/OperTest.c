//OperTest.c
#include<stdio.h>
void main() {
	int num = 0;
	printf("정수 : ");
	scanf_s("%d", &num);

	//num>0 ? printf("양수입니다\n") : printf("음수입니다\n");
	
	printf(num > 0 ? "양수입니다\n" : "음수입니다\n");
}