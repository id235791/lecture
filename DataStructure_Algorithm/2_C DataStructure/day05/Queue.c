#include<stdio.h>
#include<stdlib.h>
#define MAX 5
int* queueArray[MAX];
int front = 0;
int rear = 0;
int isFull() {
	return rear == MAX;
}
int isEmpty() {
	return front == rear;
}
void enQueue(int data) {
	if (!isFull()) {
		queueArray[rear] = data;
		rear++;
	}
	else {
		printf("Queue Is Full!\n");
	}
}
int deQueue() {
	if (!isEmpty()) {
		int temp = queueArray[front];
		queueArray[front++] = 0;
		return temp;
	}
	else {
		printf("Queue Is Empty!\n");
		return -1;
	}
}
void main() {
	int choice = 0, data = 0;
	while (1) {
		for (int i = 0; i < 5; i++) {
			printf("%d ", queueArray[i]);
		}
		printf("\n");
		printf("1. 데이터 추가\n2. 데이터 삭제\n3. 나가기\n");
		scanf_s("%d", &choice);
		if (choice == 1) {
			printf("추가할 데이터 : ");
			scanf_s("%d", &data);
			enQueue(data);
		}
		else if (choice == 2) {
			data = deQueue();
			data != -1 ? printf("%d\n", data) : "";
		}
		else {
			break;
		}
	}
}