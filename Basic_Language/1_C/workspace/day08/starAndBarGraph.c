#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<Windows.h>
//100 �� �ݺ��ؼ� ���� ���(�л� ������ �ش� �������� ũ�ų� ������)
//90  ��
//..
//0   ��������
void main()
{
	int i, j;
	int stu;
	int* stuAr;
	printf("�л� ���� �Է��ϼ��� : ");
	scanf_s("%d", &stu);
	stuAr = (int*)calloc(stu, sizeof(int));
	for (i = 0;i < stu;i++)
	{
		printf("%d��° �л��� ������ �Է��ϼ��� : ", i + 1);
		scanf_s("%d", &stuAr[i]);
	}
	system("cls");
	for (i = 10;i > 0;i--)
	{
		printf("%3d��", i * 10);
		for (j = 0;j < stu;j++)
		{
			if (stuAr[j] / 10 == i)
			{
				(stuAr[j] % 10 >= 5) ? printf("���ڦ�") : printf("������");
			}
			else if (stuAr[j] / 10 > i)
			{
				printf("������");
			}
			else
			{
				stuAr[j] < 10 && stuAr[j] != 0 && i == 1 ? printf("���١�"):printf("������");
			}
			Sleep(20);
		}
		printf("\n");
	}
	printf("%3d��", 0);
	for (i = 0;i < stu;i++)
	{
		if (stuAr[i] >= 10)	printf("������");
		else printf("������");
	}
	printf("\n����  ");
	for (i = 0;i < stu;i++)
	{
		printf("%3d�� ", stuAr[i]);
	}
	printf("\n\n");
	free(stuAr);
}