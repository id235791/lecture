//OperTest.c
#include<stdio.h>
void main() {
	int num = 0;
	printf("���� : ");
	scanf_s("%d", &num);

	//num>0 ? printf("����Դϴ�\n") : printf("�����Դϴ�\n");
	
	printf(num > 0 ? "����Դϴ�\n" : "�����Դϴ�\n");
}