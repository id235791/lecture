#include<stdio.h>
int dataList[] = { 10, 8, 1, 4, 9, 3, 5, 6, 2, 7 };
int size = 10;
void main() {
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size - 1 - i; j++) {
			if (dataList[j] > dataList[j + 1]) {
				int temp = dataList[j];
				dataList[j] = dataList[j+1];
				dataList[j + 1] = temp;
			}
		}
	}
	for (int i = 0; i < size; i++) {
		printf("%d ", dataList[i]);
	}
}