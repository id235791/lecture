//DarrTest.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	int row = 2;
	int col = 3;
	//int arr[row][col];
	//2���� �迭�� �̸����� ����� ���������� ����
	int** arr=NULL;
	//���������Ϳ� �� ���� �ּҰ��� ���� �迭�� �ּҰ� �ֱ�
	//�ҹ迭���� �ּҸ� ��Ƶ� �迭�� �ּҰ��� arr�� �־��ش�.
	arr = (int**)calloc(row, sizeof(int*));
	//for���� ���� ������ŭ ���鼭 �� ����� �������ش�.(�ҹ迭 ����)
	//���� ������ŭ �����Ҵ��� ������ �ش�.
	for (int i = 0; i < row; i++) {
		arr[i] = (int*)calloc(col, sizeof(int));
	}
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			arr[i][j] = i * 30 + (j+1) * 10;
		}
	}
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			printf("%d\n", arr[i][j]);
		}
	}
	for (int i = 0; i < row; i++) {
		free(arr[i]);
	}
	free(arr);
}