#include<stdio.h>
int dataList[] = { 10, 8, 1, 4, 9, 3, 5, 6, 2, 7 };
int size = 10;
void main() {
	for (int i = 0; i < size-1; i++) {
		int min = i;
		for (int j = i + 1; j < size; j++) {
			if (dataList[min] >= dataList[j]) {
				min = j;

			}
		}
		int temp = dataList[i];
		dataList[i] = dataList[min];
		dataList[min] = temp;
	}
	for (int i = 0; i < size; i++) {
		printf("%d ", dataList[i]);
	}
}