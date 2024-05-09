//Pointer.c
#include<stdio.h>
void f1(int data) {
	data += 10;
}
void f2(int* ptr) {
	*ptr += 10;
}
void main() {
	int data = 10;		//100번지에 data라는 이름으로 10 값을 넣어라!
	//f1(data);
	//printf("%d\n", data);

	//f2(&data);
	//printf("%d\n", data);
	int* ptr = &data;	//250번지에 ptr이라는 이름으로 100번지 값을 넣어라!
	int** dptr = &ptr;	//dptr이라는 이름으로 250번지 값을 넣어라!
	
	printf("%d\n", **dptr);
}