//Casting.c
#include<stdio.h>
void main() {
	//�ƽ�Ű �ڵ� : �� ���ڵ鸶�� �����Ǵ� ���ڰ����� �ִ�.
	//'A' : 65
	//'a' : 97
	//'0' : 48
	/*
	printf("%d\n", 'A' + 5);
	printf("%f\n", 105 + 0.0);
	*/
	
	int num1 = 0, num2 = 0;
	printf("ù��° ���� : ");
	scanf_s("%d", &num1);
	printf("�ι�° ���� : ");
	scanf_s("%d", &num2);

	//% : ������ ������(a%b - a�� b�� ���� ������)
	printf("�� : %d\n", num1 / num2);
	printf("������ : %d\n", num1 % num2);
	printf("������ ��� : %f\n",(double)num1/num2);
	
}