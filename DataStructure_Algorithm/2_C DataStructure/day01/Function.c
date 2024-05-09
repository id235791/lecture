//Function.c
#include<stdio.h>
int f(int x) {
	printf("f 함수입니다!\n");
	return 2 * x + 1;
}
void add5(int data) {
	data += 5;
}
void add5_p(int* p) {
	//p : &main함수data
	
	//*&main함수data += 5;
	*p += 5;
}
void main() {
	//21;
	printf("%d\n",f(10));
	int data = 10;
	add5(data);
	add5_p(&data);
	printf("%d\n", data);
}