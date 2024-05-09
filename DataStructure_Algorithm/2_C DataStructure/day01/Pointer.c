//Pointer.c
#include<stdio.h>
void main() {
	int data1 = 10;
	int data2 = data1;
	data2 += 10;
	printf("%d\n", data1);

	int* p = &data1;
	//*&data1 = 10;
	*p += 10;
	printf("%d\n", data1);
	//printf("%d\n", *&data1);
	printf("%d\n", *p);

	int arData1[3] = {10,20,30};
	int* arData2 = arData1;

	arData2[2] = 3000;
	arData1[1] = 2000;
}