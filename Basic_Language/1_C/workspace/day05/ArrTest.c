//ArrTest.c
#include<stdio.h>
void main() {
	int arrData[2][3] = {
		{10,20,30},
		{40,50,60}
	};
	//printf("%d\n", arrData[0]);
	//printf("%d\n", arrData[1]);

	/*
	printf("%d\n", arrData[0][0]);
	printf("%d\n", arrData[0][1]);
	printf("%d\n", arrData[0][2]);

	printf("%d\n", arrData[1][0]);
	printf("%d\n", arrData[1][1]);
	printf("%d\n", arrData[1][2]);
	
	
	for (int i = 0; i < 6; i++) {
		printf("%d\n", arrData[i / 3][i % 3]);
	}
	*/
	/*
	printf("%d\n", arrData[0][0]);
	printf("%d\n", arrData[0][1]);
	printf("%d\n", arrData[0][2]);
	*/
	/*
	for (int j = 0; j < 3; j++) {
		printf("%d\n", arrData[0][j]);
	}
	*/
	/*
	printf("%d\n", arrData[1][0]);
	printf("%d\n", arrData[1][1]);
	printf("%d\n", arrData[1][2]);
	*/
	/*
	for (int j = 0; j < 3; j++) {
		printf("%d\n", arrData[1][j]);
	}
	*/
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 3; j++) {
			printf("%d\n", arrData[i][j]);
		}
	}
}