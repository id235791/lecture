//ArrayTest.c
#include<stdio.h>
void main() {
	//�迭�� ������ ������ ������ �����̴�.
	int arData[] = {10, 20, 30, 40, 50};
	//arData(�ּҰ�) + ����(n) : �ּҿ���(n��ŭ �̵�)
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

	// *(�迭�� + n)
	// �迭��[n]
	int* ptr = arData;
	printf("%d\n", ptr[2]);// *(ptr+2)

}
