//DarrTask.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	//사용자에게 입력받을 정수의 개수 먼저 입력받은 후
	//그 개수만큼 정수들 입력받고 총합, 평균 출력해주기
	int size = 0;
	printf("입력하실 정수의 개수 : ");
	scanf_s("%d", &size);
	int* arr = NULL;
	arr = (int*)calloc(size, sizeof(int));
	for (int i = 0; i < size; i++) {
		printf("%d번째 정수 : ", i + 1);
		//scanf_s("%d", arr + i);
		scanf_s("%d", &arr[i]);
	}
	int tot = 0;
	for (int i = 0; i < size; i++) {
		printf("%d : %d\n", i + 1, arr[i]);
		tot += arr[i];
	}
	printf("총합 : %d\n평균 : %.3f\n", tot, tot / (double)size);
	//위에서 입력받은 정수들 중 최대값, 최소값 구해주는 함수 만들기
	int max=arr[0], min=arr[0];
	for (int i = 1; i < size; i++) {
		if (max < arr[i]) {
			max = arr[i];
		}
		if (min > arr[i]) {
			min = arr[i];
		}
	}
	//사용자에게 정수를 입력받아서 그만큼 뒤에서부터 지워주기
	int delsize = 0;
	printf("지울 값의 개수 : ");
	scanf_s("%d", &delsize);
	arr = (int*)realloc(arr, (size - delsize) * sizeof(int));
	for (int i = 0; i < size; i++) {
		printf("%d\n", arr[i]);
	}
	free(arr);
	//행, 열을 입력받아서 가장 [0][0]부터 'A'~'Z' 까지 채워넣기
	//5 6
	//'A' 'B' 'C' 'D' 'E' 'F'
	//'G' 'H' ..
}