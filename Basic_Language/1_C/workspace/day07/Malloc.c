//Malloc.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	//NULL : 비어있다.(주소값의 초기값)
	int* p = NULL;
	
	//p = (int*)malloc(20);
	p = (int*)malloc(5*sizeof(int));

	//printf("%x\n", p);
	if (p != NULL) {
		*(p + 0) = 10;
		p[0] = 10;
		*(p + 1) = 20;

		//printf("%d %d", p[0], p[1]);

		for (int i = 0; i < 5; i++) {
			printf("%d\n", p[i]);
		}
		free(p);
	}
}