//ForTest.c
#include<stdio.h>
void main() {
	int i = 0;
	for (;i<10;i++) {

	}
	printf("%d\n", i);
	
	//12부터 2씩 증가하면서 22번 반복하는 반복문
	//횟수*증감량+초기값
	//12 14 16 18 20 22 24 26 28 30
	//32						 50
	//52 54 (56)
	for (i = 12;i<56;i+=2)
	{

	}
	printf("%d", i);
}