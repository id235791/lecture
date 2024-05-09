#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<Windows.h>
//100 │ 반복해서 별을 출력(학생 점수가 해당 점수보다 크거나 같으면)
//90  │
//..
//0   └───
void main()
{
	int i, j;
	int stu;
	int* stuAr;
	printf("학생 수를 입력하세요 : ");
	scanf_s("%d", &stu);
	stuAr = (int*)calloc(stu, sizeof(int));
	for (i = 0;i < stu;i++)
	{
		printf("%d번째 학생의 점수를 입력하세요 : ",i+1);
		scanf_s("%d", &stuAr[i]);
	}
	system("cls");
	//별그래프
	/*for (i = 0;i < 10;i++)
	{
		printf("%3d│", (100 - i * 10));
		for (j = 0;j < stu;j++)
		{
			if (stuAr[j] < (100 - i * 10))
			{
				printf("　 ");
			}
			else if (stuAr[j] >= (100 - i * 10))
			{
				printf("★ ");
			}
			Sleep(100);
		}
		printf("\n");
	}
	printf("%3d└",0);
	for (i = 0;i < stu;i++)
	{
		printf("──");
	}
	printf("\n    ");
	for (i = 0;i < stu;i++)
	{
		printf("%3d",stuAr[i]);
	}
	printf("\n");*/
	//막대그래프
	for (i = 10;i > 0;i--)
	{
		printf("%3d│", i * 10);
		for (j = 0;j < stu;j++)
		{
			if (stuAr[j] / 10 == i)
			{
				printf("┌┐");
			}
			else if (stuAr[j] / 10 > i)
			{
				printf("││");
			}
			else
			{
				printf("　　");
			}
			Sleep(20);
		}
		printf("\n");
	}
	printf("%3d└", 0);
	for (i = 0;i < stu;i++)
	{
		if (stuAr[i] != 0)	printf("┴┴");
		else printf("──");
	}
	printf("\n　　 ");
	for (i = 0;i < stu;i++)
	{
		printf("%3d ", stuAr[i]);
	}
	printf("\n\n");
	/*
	printf("┌");
	for (i = 0;i < stu-1;i++)
	{
		printf("─┬");
	}
	printf("─┐\n│");
	for (i = 0;i < stu;i++)
	{
		printf("%3d│",stuAr[i]);
	}
	printf("\n└");
	for (i = 0;i < stu - 1;i++)
	{
		printf("─┴");
	}
	printf("─┘\n");*/
	free(stuAr);
}