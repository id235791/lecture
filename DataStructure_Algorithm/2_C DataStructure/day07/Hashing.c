#include<stdio.h>
#define MAX 7
int arData[MAX];
int h(int data) {
	return data % MAX;
}
void main() {
	int data = 0;
	for (int i = 0; i < 3; i++) {
		printf("저장할 값 : ");
		scanf_s("%d", &data);
		arData[h(data)] = data;
	}
	for (int i = 0; i < MAX; i++) {
		printf("%d : %d\n", i, arData[i]);
	}
	int findData = 0;
	printf("찾을 값 : ");
	scanf_s("%d", &findData);
	printf("%d는 %d번째에 있습니다.\n", findData, h(findData));
}



