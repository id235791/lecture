//DarrTask.c
#include<stdio.h>
#include<stdlib.h>
void main() {
	//����ڿ��� �Է¹��� ������ ���� ���� �Է¹��� ��
	//�� ������ŭ ������ �Է¹ް� ����, ��� ������ֱ�
	int size = 0;
	printf("�Է��Ͻ� ������ ���� : ");
	scanf_s("%d", &size);
	int* arr = NULL;
	arr = (int*)calloc(size, sizeof(int));
	for (int i = 0; i < size; i++) {
		printf("%d��° ���� : ", i + 1);
		//scanf_s("%d", arr + i);
		scanf_s("%d", &arr[i]);
	}
	int tot = 0;
	for (int i = 0; i < size; i++) {
		printf("%d : %d\n", i + 1, arr[i]);
		tot += arr[i];
	}
	printf("���� : %d\n��� : %.3f\n", tot, tot / (double)size);
	//������ �Է¹��� ������ �� �ִ밪, �ּҰ� �����ִ� �Լ� �����
	int max=arr[0], min=arr[0];
	for (int i = 1; i < size; i++) {
		if (max < arr[i]) {
			max = arr[i];
		}
		if (min > arr[i]) {
			min = arr[i];
		}
	}
	//����ڿ��� ������ �Է¹޾Ƽ� �׸�ŭ �ڿ������� �����ֱ�
	int delsize = 0;
	printf("���� ���� ���� : ");
	scanf_s("%d", &delsize);
	arr = (int*)realloc(arr, (size - delsize) * sizeof(int));
	for (int i = 0; i < size; i++) {
		printf("%d\n", arr[i]);
	}
	free(arr);
	//��, ���� �Է¹޾Ƽ� ���� [0][0]���� 'A'~'Z' ���� ä���ֱ�
	//5 6
	//'A' 'B' 'C' 'D' 'E' 'F'
	//'G' 'H' ..
}