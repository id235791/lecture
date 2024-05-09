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
//과일 구조체 만들기
typedef struct Fruit {
	//모든 과일들이 가지고 있을만한 속성
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
	printf("내 차 브랜드 : %s\n",mycar.brand);
	printf("엄마 차 브랜드 : %s\n", momcar.brand);
	//printf("%d\n", sizeof(Test));
	
	//각 방이 int타입의 변수
	int arData[10];
	//각 방이 Fruit타입의 구조체변수
	Fruit arFruit[100];
	printf("과일 이름 : ");
	scanf_s("%s", arFruit[0].name, sizeof(arFruit[0].name));
	printf("과일 색깔 : ");
	scanf_s("%s", arFruit[0].color, sizeof(arFruit[0].color));
	printf("가격 : ");
	scanf_s("%d", &arFruit[0].price);

	printf("%s\n%s\n%d\n", arFruit[0].name, arFruit[0].color, arFruit[0].price);
	
}