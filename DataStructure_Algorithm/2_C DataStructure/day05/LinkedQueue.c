#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct Node{
	char data[100];
	struct Node* next;
}Node;
int size = sizeof(Node);
Node* create(char* data) {
	Node* newNode = (Node*)calloc(1, size);
	strcpy_s(newNode->data, sizeof(newNode->data), data);
	return newNode;
}
Node* curr;
Node* front=NULL;
Node* rear=NULL;
int isEmpty() {
	return front == NULL;
}
void enQueue(char* data) {
	Node* newNode = create(data);
	if (front == NULL) {
		front = newNode;
	}
	else {
		rear->next = newNode;
	}
	rear = newNode;
}
char* deQueue() {
	char* data = NULL;
	if (!isEmpty()) {
		data = front->data;
		front = front->next;
	}
	else {
		data = "Queue Is Empty!";
	}
	return data;
}
void main() {
	enQueue("A");
	enQueue("B");
	enQueue("C");
	enQueue("D");
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	enQueue("A");
	enQueue("B");
	enQueue("C");
	enQueue("D");
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
	printf("%s\n", deQueue());
}