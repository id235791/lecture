//Struct.c
#include<stdio.h>
struct Fruit {
	char name[30];
	char color[30];
	int price;
};
typedef struct Car {
	char brand[100];
	char color[100];
	int price;
}Car;
void main() {
	struct Fruit apple = {.name="û�ֻ��",.color="����",.price=2000};
	printf("�̸� : %s\n", apple.name);

	Car mycar;
	mycar.price = 5000;

}