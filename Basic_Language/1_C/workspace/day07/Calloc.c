//Calloc.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	int* p = (int*)calloc(5, sizeof(int));
	if (p != NULL) {
		for (int i = 0; i < 5; i++) {
			printf("%d\n", p[i]);
		}
		free(p);
	}
}