#include<stdio.h>
void main() {
	/*
	int arData[5] = { 10,20,30,40,50 };
	int* ptr = arData;
	printf("%d\n", sizeof(arData));	//�迭�� ����� ����� �� ũ��
	printf("%d\n", sizeof(ptr));	//�����ͺ����� ������ 4����Ʈ
	*/

	//10ĭ¥�� �迭 ���� 1~10���� for���� �̿��ؼ� �ְ�
	//�ٽ� ����ϱ� int arData[10];
	int arData[10];
	for (int i = 0; i < 10; i++) {
		arData[i] = i + 1;
	}
	for (int i = 0; i < 10; i++) {
		printf("%d\n", *(arData+i));
	}


	//10ĭ¥�� �迭 ���� 10~1���� for���� �̿��ؼ� �ְ�
	//�ٽ� ����ϱ�
	for (int i = 0; i < 10; i++) {
		arData[i] = 10 - i;
	}
	for (int i = 0; i < 10; i++) {
		printf("%d\n", arData[i]);
	}

	//�ټ����� ���� �Է¹޾Ƽ� ���� ���ϱ�
	//1��° ���� : 
	//2��° ���� : 
	int arData2[10];
	int len = sizeof(arData2) / sizeof(int);
	for (int i = 0; i < len; i++) {
		printf("%d��° ���� : ",i+1);
		scanf_s("%d", &arData2[i]);
	}
	int sum = 0;
	for (int i = 0; i < len; i++) {
		sum += arData2[i];
	}
	printf("���� : %d\n", sum);
	printf("��� : %.2f\n", sum /(double)len);
	
}