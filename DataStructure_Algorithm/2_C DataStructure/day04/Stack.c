#include<stdio.h>
#include<stdlib.h>
#define MAX 5
int* stackArray[MAX];
int top = 0;
int isFull() {
	return top == MAX;
}
int isEmpty() {
	return top == 0;
}
void push(int data) {
	if (!isFull()) {
		stackArray[top] = data;
		top++;
	}
	else {
		printf("Stack Is Full!\n");
	}
}
int pop() {
	if (!isEmpty()) {
		int temp = stackArray[--top];
		stackArray[top] = 0;
		return temp;
	}
	else {
		printf("Stack Is Empty!\n");
		return -1;
	}
}
void main() {
	int choice = 0,data = 0;
	while (1) {
		for (int i = 0; i < 5; i++) {
			printf("%d ", stackArray[i]);
		}
		printf("\n");
		printf("1. 데이터 추가\n2. 데이터 삭제\n3. 나가기\n");
		scanf_s("%d", &choice);
		if (choice == 1) {
			printf("추가할 데이터 : ");
			scanf_s("%d", &data);
			push(data);
		}
		else if (choice == 2) {
			data = pop();
			data!=-1?printf("%d\n",data):"";
		}
		else {
			break;
		}
	}
}