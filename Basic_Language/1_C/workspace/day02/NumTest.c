//NumTest.c
#include<stdio.h>
void main() {
	//�� ���� �Է¹ް� �� ���ϱ�
	int num1 = 0, num2 = 0;
	printf("�� ������ �Է��ϼ���(�� : 10,20) : ");
	scanf_s("%d,%d", &num1,&num2);//10,20 | num1 : 10 / num2 : 20
	printf("�� ������ �� : %d\n", num1 + num2);
}