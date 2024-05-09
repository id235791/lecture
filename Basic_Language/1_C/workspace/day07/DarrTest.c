//DarrTest.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	int row = 2;
	int col = 3;
	//int arr[row][col];
	//2차원 배열의 이름으로 사용할 이중포인터 선언
	int** arr=NULL;
	//이중포인터에 행 들의 주소값을 담을 배열의 주소값 넣기
	//소배열들의 주소를 모아둔 배열의 주소값을 arr에 넣어준다.
	arr = (int**)calloc(row, sizeof(int*));
	//for문을 행의 개수만큼 돌면서 각 행들을 생성해준다.(소배열 생성)
	//열의 개수만큼 동적할당을 진행해 준다.
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