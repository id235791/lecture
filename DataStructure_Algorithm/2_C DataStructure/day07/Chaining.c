#include<stdio.h>
#include<stdlib.h>
#define MAX 7
int h(int data) {
	return data % MAX;
}
typedef struct Node {
	int data;
	struct Node* next;
}Node;
Node* create(int data) {
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	newNode->data = data;
	return newNode;
}
int count[MAX] = { 0, };
Node* dataList[MAX];
Node* curr;
void insertData(int data) {
	Node* newNode = create(data);
	int idx = h(data);
	printf("INSERT : %d - %d\n", idx, data);
	if (dataList[idx] == NULL) {
		dataList[idx] = newNode;
	}
	else {
		curr = dataList[idx];
		for (int i = 0; i < count[idx] - 1; i++) {
			curr = curr->next;
		}
		curr->next = newNode;
	}
	count[idx]++;
}
void printData() {
	for (int i = 0; i < MAX; i++) {
		curr = dataList[i];
		printf("%d : ", i);
		for (int j = 0; j < count[i] - 1; j++) {
			printf("%d>", curr->data);
			curr = curr->next;
		}
		if (curr != NULL) {
			printf("%d\n", curr->data);
		}
		else {
			printf("\n");
		}
	}
}
void main() {
	for (int i = 0; i < MAX; i++) {
		dataList[i] = NULL;
	}
	for (int i = 0; i < 140; i++) {
		insertData(i);
	}
	printData();
}