//LinkedTree.c
//배열 대신에 연결리스트 사용
//출력만 트리형태로
//추가, 삭제, 출력
#include<stdio.h>
#include<stdlib.h>
#include<math.h>
typedef struct Node {
	char data;
	struct Node* next;
}Node;
int idx = 1;
Node* root;
Node* curr;
Node* create(char data) {
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	newNode->data = data;
	return newNode;
}
void insert(char data) {
	Node* newNode = create(data);
	if (root == NULL) {
		root = newNode;
	}
	else {
		curr = root;
		while (1) {
			if (curr->next == NULL) {
				break;
			}
			curr = curr->next;
		}
		curr->next = newNode;
	}
}
void print() {
	curr = root;
	int num = 1;
	int cnt = 1;
	while (1) {
		if (curr == NULL) {
			break;
		}
		if (cnt == pow(2, num)) {
			printf("\n");
			num++;
		}
		printf("%c ", curr->data);
		curr = curr->next;
		cnt++;
	}
}
void drop(char data) {
	Node* delNode;
	curr = root;
	if (curr->data == data) {
		root = root->next;
		free(curr);
	}
	else {
		while (1) {
			if (curr == NULL) {
				break;
			}
			if (curr->next->data == data) {
				delNode = curr->next;
				curr->next = curr->next->next;
				free(delNode);
				break;
			}
			curr = curr->next;
		}
	}
}
void main() {

}