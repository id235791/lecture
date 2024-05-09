#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define MAX 4
int arr[MAX] = { 0, };
int rear=0,front = 0;
int isEmpty() {
	return rear == front && arr[front] == 0;
}
int isFull() {
	return rear == front && arr[front] != 0;
}
void enQueue(int data) {
	if (!isFull()) {
		printf("enQueue : %d\n", data);
		arr[rear] = data;
		rear++;
		rear %= MAX;
	}
	else {
		printf("Queue Is Full!\n");
	}
}
void deQueue() {
	int temp = 0;
	if (!isEmpty()) {
		temp = arr[front];
		arr[front++] = 0;
		front %= MAX;
		printf("deQueue : %d\n", temp);
	}
	else {
		printf("Queue Is Empty!\n");
	}
}
void main() {
	enQueue(10);
	enQueue(20);
	enQueue(30);
	enQueue(40);
	enQueue(50);
	enQueue(60);
	enQueue(70);
	deQueue();
	deQueue();
	enQueue(10);
	enQueue(20);
	enQueue(30);
	enQueue(40);
	deQueue();
	deQueue();
	deQueue();
	deQueue();


}