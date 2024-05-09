#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct Node {
	char data[10];
	struct Node* next;
}Node;
Node* head;
int count = 0;
//추가
void append(char* data) {
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	strcpy_s(newNode->data, sizeof(newNode->data), data);
	Node* curr = head;
	for (int i = 0; i < count; i++) {
		curr = curr->next;
	}
	curr->next = newNode;
	count++;
}
//삽입
void insert(int idx, char* data) {
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	strcpy_s(newNode->data, sizeof(newNode->data), data);
	Node* curr = head;
	for (int i = 0; i < idx; i++) {
		curr = curr->next;
	}
	newNode->next = curr->next;
	curr->next = newNode;
	count++;
}
//수정
void update(int idx,char* data) {
	Node* curr = head;
	for (int i = 0; i < idx + 1; i++) {
		curr = curr->next;
	}
	strcpy_s(curr->data, sizeof(curr->data), data);
}
//삭제
void drop(int idx) {
	Node* curr = head;
	Node* temp;
	for (int i = 0; i < idx; i++) {
		curr = curr->next;
	}
	temp = curr->next;
	curr->next = curr->next->next;
	count--;
	free(temp);
}
//조회
char* get(int idx) {
	Node* curr = head;
	for (int i = 0; i < idx + 1; i++) {
		curr = curr->next;
	}
	return curr->data;
}
//목록
void show() {
	Node* curr = head;
	while (curr->next != NULL) {
		printf("%s ", curr->data);
		curr = curr->next;
	}
	printf("%s\n",curr->data);
}
void main() {
	head = (Node*)calloc(1, sizeof(Node));
	strcpy_s(head->data, sizeof(head->data), "head");
	append("A");
	append("B");
	append("C");
	append("D");
	append("E");
	show();
	insert(2, "F");
	show();
	update(2, "G");
	show();
	drop(2);
	show();
	printf("%s\n", get(2));
	Node* delNode = head;
	while (delNode->next != NULL) {
		Node* temp = delNode;
		delNode = delNode->next;
		free(temp);
	}
	free(delNode);
}