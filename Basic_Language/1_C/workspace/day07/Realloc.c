//Realloc.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	int* arData = (int*)calloc(5,sizeof(int));
	for (int i = 0; i < 5; i++) {
		arData[i] = i + 1;
	}
	arData = (int*)realloc(arData, 10 * sizeof(int));
	for (int i = 0; i < 10; i++) {
		printf("%d\n", arData[i]);
	}
	free(arData);
}