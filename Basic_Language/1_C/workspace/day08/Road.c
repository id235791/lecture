//Road.c
#include<stdio.h>
typedef struct Test {
	char a;
	int b;
	//char c;
	double c;
}Test;
typedef struct Car {
	char brand[100];
	char color[100];
	int price;
}Car;
//���� ����ü �����
typedef struct Fruit {
	//��� ���ϵ��� ������ �������� �Ӽ�
	char name[100];
	char color[100];
	int price;
}Fruit;
void main() {
	/*
	mycar.brand = "Ferrari";
	mycar.color = "Red";
	mycar.price = 65000;
	*/
	Car mycar = {"Ferrari","Red",65000};
	Car momcar = {.price=7000,.brand="K7",.color="White"};
	printf("�� �� �귣�� : %s\n",mycar.brand);
	printf("���� �� �귣�� : %s\n", momcar.brand);
	//printf("%d\n", sizeof(Test));
	
	//�� ���� intŸ���� ����
	int arData[10];
	//�� ���� FruitŸ���� ����ü����
	Fruit arFruit[100];
	printf("���� �̸� : ");
	scanf_s("%s", arFruit[0].name, sizeof(arFruit[0].name));
	printf("���� ���� : ");
	scanf_s("%s", arFruit[0].color, sizeof(arFruit[0].color));
	printf("���� : ");
	scanf_s("%d", &arFruit[0].price);

	printf("%s\n%s\n%d\n", arFruit[0].name, arFruit[0].color, arFruit[0].price);
	
}