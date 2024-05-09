#include<stdio.h>
int dataList[] = { 10, 8, 1, 4, 9, 3, 5, 6, 2, 7 };
int size = 10;
int partition(int* ar, int left, int right) {
	int pivot = ar[right];
	int idx = left - 1;
	int temp;
	for (int i = left; i < right; i++) {
		if (ar[i] <= pivot) {
			idx++;
			temp = ar[i];
			ar[i] = ar[idx];
			ar[idx] = temp;
			printf("SWAP : ");
			for (int j = 0; j < size; j++) {
				printf("%d ", ar[j]);
			}
			printf("\n");
		}
	}
	temp = ar[idx + 1];
	ar[idx + 1] = ar[right];
	ar[right] = temp;
	return idx + 1;
}
void quickSort(int* ar,int left,int right) {
	if (left < right) {
		int q = partition(ar, left, right);
		quickSort(ar,left,q-1);
		quickSort(ar,q+1,right);
	}
}
void main() {
	quickSort(dataList, 0, size-1);
	for (int i = 0; i < size; i++) {
		printf("%d ",dataList[i]);
	}
	printf("\n");
}