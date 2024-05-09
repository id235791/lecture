#include<stdio.h>
#include<stdlib.h>
typedef struct Node {
	char data;
	struct Node* left;
	struct Node* right;
}Node;
Node* root = NULL;
int count = 1;
Node** queue;
Node* create(char data) {
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	newNode->data = data;
	return newNode;
}
void insertNode(char data){
	Node* newNode = create(data);
	if (root == NULL) {
		root = newNode;
	}
	else {
		if (queue[count / 2]->left == NULL) {
			queue[count / 2]->left = newNode;
		}
		else if (queue[count / 2]->right == NULL) {
			queue[count / 2]->right = newNode;
		}
	}
	count++;
	queue = (Node**)realloc(queue, count*sizeof(Node*));
	queue[count - 1] = newNode;
}
void main() {
	queue = (Node**)calloc(1, sizeof(Node*));
	queue[0] = (Node*)calloc(1, sizeof(Node));
	queue[0]->data = 'x';
	insertNode('A');
	insertNode('B');
	insertNode('C');
	insertNode('D');
	printf("%c\n", root->data);
	printf("%c\n", root->left->data);
	printf("%c\n", root->right->data);
	printf("%c\n", root->left->left->data);
}