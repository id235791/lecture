#include<stdio.h>
#define MAX 7
int arData[MAX];
int h(int data) {
	return data % MAX;
}
void main() {
	int data = 0;
	for (int i = 0; i < 3; i++) {
		printf("������ �� : ");
		scanf_s("%d", &data);
		arData[h(data)] = data;
	}
	for (int i = 0; i < MAX; i++) {
		printf("%d : %d\n", i, arData[i]);
	}
	int findData = 0;
	printf("ã�� �� : ");
	scanf_s("%d", &findData);
	printf("%d�� %d��°�� �ֽ��ϴ�.\n", findData, h(findData));
}



