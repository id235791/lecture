//Dalloc.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	int num = 0;
	scanf_s("%d", &num);

	//int arData[num];
	int* p = (int*)malloc(num*sizeof(int));
	p[0] = 10;
	printf("%d\n", p[0]);

	free(p);

	p = (int*)calloc(10, sizeof(int));

	free(p);
}