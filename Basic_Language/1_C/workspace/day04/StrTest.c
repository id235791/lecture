#include<stdio.h>
void main() {
	//"Hello"
	char msg[6] = { 'H','e','l','l','o','\0'};
	char msg[6] = "Hello";
	const char* msg2 = "C¾ð¾î";
	//printf("%s\n", msg);

	int data = 0;
	scanf_s("%d", &data);
	scanf_s("%s", msg,5);
}