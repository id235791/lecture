#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct Node {
	char data[100];
	struct Node* next;
	struct Node* prev;
}Node;
Node* head;
Node* tail;
int count=0;
Node* curr;
int size = sizeof(Node);
Node* create(char* data) {
	Node* newNode = (Node*)calloc(1, size);
	strcpy_s(newNode->data, sizeof(newNode->data), data);
	return newNode;
}
Node* findNode(int idx) {
	if (idx <= count / 2) {
		curr = head;
		for (int i = 0; i < idx + 1; i++) {
			curr = curr->next;
		}
	}
	else {
		curr = tail;
		for (int i = 0; i < count - idx; i++) {
			curr = curr->prev;
		}
	}
	return curr;
}
void append(char* data){
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	strcpy_s(newNode->data, sizeof(newNode->data), data);
	newNode->next = tail;
	newNode->prev = tail->prev;
	tail->prev->next = newNode;
	tail->prev = newNode;
	count++;
}
void insert(int idx, char* data) {
	Node* newNode = (Node*)malloc(1, sizeof(Node));
	strcpy_s(newNode->data, sizeof(newNode->data), data);
	if (idx <= count / 2) {
		curr = findNode(idx - 1);
		newNode->next = curr->next;
		newNode->prev = curr;
		curr->next = newNode;
		newNode->next->prev = newNode;
	}
	else {
		curr = findNode(idx);
		newNode->prev = curr->prev;
		newNode->next = curr;
		curr->prev = newNode;
		newNode->prev->next = newNode;
	}
	count++;
}
void update(int idx, char* data) {
	curr = findNode(idx);
	strcpy_s(curr->data, sizeof(curr->data), data);
}
void drop(int idx) {
	if (idx <= count / 2) {
		curr = findNode(idx - 1);
		curr->next = curr->next->next;
		curr->next->prev = curr;
	}
	else {
		curr->prev = curr->prev->prev;
		curr->prev->next= curr;
	}
	count--;
}
char* get(int idx) {
	return findNode(idx)->data;
}
void show(int order) {
	if (order) {
		curr = head;
		for (int i = 0; i < count+1; i++) {
			printf("%s->", curr->data);
			curr = curr->next;
		}
		printf("%s\n",curr->data);
	}
	else {
		curr = tail;
		for (int i = 0; i < count + 1; i++) {
			printf("%s->", curr->data);
			curr = curr->prev;
		}
		printf("%s\n", curr->data);
	}
}
void main() {
	head = (Node*)calloc(1, sizeof(Node));
	strcpy_s(head->data, sizeof(head->data), "head");
	tail = (Node*)calloc(1, sizeof(Node));
	strcpy_s(tail->data, sizeof(tail->data), "tail");
	head->next = tail;
	tail->prev = head;
	int choice = 0;
	while (1) {
		printf("오름차순 : ");
		show(1);
		printf("내림차순 : ");
		show(0);
		printf("1. 추가하기\n2. 삽입하기\n3. 수정하기\n4. 삭제하기\n5. 조회하기\n6. 종료\n");
		scanf_s("%d", &choice);
		char data[100];
		int idx = 0;
		if (choice == 6) { break; }
		switch (choice) {
		case 1:
			printf("추가할 데이터 : ");
			scanf_s("%s", data,100);
			append(data);
			break;
		case 2:
			printf("추가할 위치 : ");
			scanf_s("%d", &idx);
			printf("추가할 데이터 : ");
			scanf_s("%s", data,100);
			insert(idx, data);
			break;
		case 3:
			printf("수정할 위치 : ");
			scanf_s("%d", &idx);
			printf("새로운 데이터 : ");
			scanf_s("%s", data,100);
			update(idx, data);
			break;
		case 4:
			printf("삭제할 위치 : ");
			scanf_s("%d", &idx);
			drop(idx);
			break;
		case 5:
			printf("조회할 위치 : ");
			scanf_s("%d", &idx);
			printf("%s\n", get(idx));
			break;
		}
	}
}