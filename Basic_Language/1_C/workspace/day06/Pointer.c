//Pointer.c
#include<stdio.h>
void f1(int data) {
	data += 10;
}
void f2(int* ptr) {
	*ptr += 10;
}
void main() {
	int data = 10;		//100������ data��� �̸����� 10 ���� �־��!
	//f1(data);
	//printf("%d\n", data);

	//f2(&data);
	//printf("%d\n", data);
	int* ptr = &data;	//250������ ptr�̶�� �̸����� 100���� ���� �־��!
	int** dptr = &ptr;	//dptr�̶�� �̸����� 250���� ���� �־��!
	
	printf("%d\n", **dptr);
}