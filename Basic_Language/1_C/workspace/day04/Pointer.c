#include<stdio.h>
void main() {
	int data = 10;
	int* ptr = &data;//142����

	//printf("%x\n",ptr);

	*ptr = 20;
	printf("%d\n", data);
	printf("%d\n", *ptr);
}