//ForTask.c
#include<stdio.h>
void main() {
	//�ִ��� for���� �ʱ���� int i=0 ����, �������� i++ ����
	
	//1 ~ 10 ���� ����ϱ�
	/*
	for (int i = 1;i<=10;i++) {
		printf("%d ", i);
	}
	*/
	for (int i = 0; i < 10; i++){	
		printf("%d ", i + 1);
	}
	printf("\n");
	//10 ~ 1 ���� ����ϱ�
	/*
	for (int i = 10;i>0; i--) {
		printf("%d ", i);
	}
	*/
	//0		10
	//1		9
	//2		8
	//i*-1+10
	for (int i = 0; i < 10; i++) {
		printf("%d ", 10 - i);
	}
	printf("\n");
	//�Է� �ϳ� �޾Ƽ� 1���� �� ������ ����ϱ�
	int num = 0;
	printf("���� : ");
	scanf_s("%d", &num);
	for (int i = 0; i < num; i++)
	{
		printf("%d ", i + 1);
	}
	printf("\n");

	//1���� 10������ �� ����ϱ�
	int sum = 0;
	for (int i = 1; i <= 10; i++) {
		sum += i;
	}
	printf("%d\n", sum);

	//n,m �Է¹޾Ƽ� n���� m���� ����ϱ�
	int n = 0,m = 0;
	printf("n : ");
	scanf_s("%d", &n);
	printf("m : ");
	scanf_s("%d", &m);

	for (int i = n; i <= m; i++)
	{
		printf("%d ", i);
	}
	printf("\n");
	//1���� 100������ ¦���� ����ϱ�
	for (int i = 1; i <= 100; i++) {
		if (i % 2 == 0) {
			printf("%d ", i);
		}
	}
	printf("\n");
	//1���� 100������ 3�� 5�� ����� ����ϱ�
	//A���� F���� ����ϱ�
	//A(65+0)		a(97) 
	//C(65+2)		b(97+1)
	//E(65+4)		d(97+3)
	//AbCdEf......z ����ϱ�

	//������ for�� �Ѱ��� �Ἥ ����ϱ�
	//10���� ���� �ϳ� �Է¹޾Ƽ� 2�������� ����ϱ�
}