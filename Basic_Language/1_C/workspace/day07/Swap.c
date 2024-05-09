//Swap.c
#include<stdio.h>
void main() {
	int data1 = 10;//100
	int data2 = 20;//200
	swap(&data1, &data2);//100,200
	
}
//				100			200
void swap(int* p1, int* p2) {
	//p1 : &data1
	//p2 : &data2
	int temp = 0;
	//temp = data1;
	temp = *p1;
	//data1 = data2;
	*p1 = *p2;
	//data2 = temp;
	*p2 = temp;
}