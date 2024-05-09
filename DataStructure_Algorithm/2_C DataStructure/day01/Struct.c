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
	struct Fruit apple = {.name="청주사과",.color="빨강",.price=2000};
	printf("이름 : %s\n", apple.name);

	Car mycar;
	mycar.price = 5000;

}