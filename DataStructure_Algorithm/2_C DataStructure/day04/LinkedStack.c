#include<stdio.h>
#include<stdlib.h>
typedef struct Node {
	char data;
	struct Node* next;
}Node;
int size = sizeof(Node);
Node* create(char data) {
	Node* newNode = (Node*)calloc(1, size);
	newNode->data = data;
	return newNode;
}
Node* head;
Node* curr;
Node* top;
void push(char data) {
	Node* newNode = create(data);
	top->next = newNode;
	top = newNode;
}
char pop() {
	char temp=top->data;
	Node* tmpN = top;
	curr = head;
	while (1) {
		if (curr->next == top) {
			break;
		}
		curr = curr->next;
	}
	top = curr;
	free(tmpN);
	return temp;
}

void main() {
	head = create('h');
	top = head;
	push('A');
	push('B');
	push('C');
	push('D');
	printf("%c\n",pop());
	printf("%c\n",pop());
	printf("%c\n",pop());
	printf("%c\n",pop());
}