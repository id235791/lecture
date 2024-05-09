//ArrayTest.c
#include<stdio.h>
void main() {
	//배열명 변수는 일종의 포인터 변수이다.
	int arData[] = {10, 20, 30, 40, 50};
	//arData(주소값) + 정수(n) : 주소연산(n만큼 이동)
	/*
	printf("%d\n", *(arData + 0));
	printf("%d\n", *(arData + 1));
	printf("%d\n", *(arData + 2));
	printf("%d\n", *(arData + 3));
	printf("%d\n", *(arData + 4));
	*/
	/*
	for (int i = 0; i < 5; i++) {
		printf("%d\n", *(arData + i));
	}
	*/

	// *(배열명 + n)
	// 배열명[n]
	int* ptr = arData;
	printf("%d\n", ptr[2]);// *(ptr+2)

}
