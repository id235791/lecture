#include<stdio.h>
void main() {
	int age = 0;
	printf("���̸� �Է��ϼ��� : ");
	scanf_s("%d",&age);
	printf("����� ���̴� %d ���Դϴ�.\n",age);
	char name[100];
	printf("�̸��� �Է��ϼ��� : ");
	//���ڿ��� �̸� �״�� �Ѱ��ֱ�
	scanf_s("%s",name,100);
	printf("%s\n", name);
	char score = ' ';
	printf("������ �Է��ϼ��� : ");
	//���� �Է¹޴°� �տ� �ٸ� �Է��� �ִٸ�, %c �տ� ���� �ϳ� �߰��ϱ�
	scanf_s(" %c", &score,1);
	printf("���� : %c\n", score);

	double height = 0.0;
	printf("Ű�� �Է��ϼ��� : ");
	//double Ÿ���� �Է½ÿ��� %lf�� �����־�� �Ѵ�.(��¶��� �������)
	scanf_s("%lf", &height);
	printf("Ű : %fcm\n", height);
}