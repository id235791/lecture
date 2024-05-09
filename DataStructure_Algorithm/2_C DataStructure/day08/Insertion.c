#include<stdio.h>
int dataList[] = { 10, 8, 1, 4, 9, 3, 5, 6, 2, 7 };
int size = 10;
void main() {
	for (int i = 1; i < size; i++) {
		int temp = dataList[i];
		int idx = -1;
		for (int j = i - 1; j >= 0; j--) {
			if (temp < dataList[j]) {
				dataList[j + 1] = dataList[j];
			}
			else {
				idx = j;
				break;
			}
		}
		dataList[idx + 1] = temp;
	}
	for (int i = 0; i < size; i++) {
		printf("%d ", dataList[i]);
	}
}