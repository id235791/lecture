#include<stdio.h>
#include<stdlib.h>
typedef struct Node {
	int data;
	struct Node* left;
	struct Node* right;
}Node;
Node* root = NULL;
int count = 1;
//Node** queue;
Node* curr;
Node* create(int data) {
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	newNode->data = data;
	return newNode;
}
Node* insertNode(int data, Node* node) {
	if (node == NULL) {
		node = create(data);
		count++;
		//queue = (Node**)realloc(queue, count * sizeof(Node*));
		//queue[count - 1] = node;
		if (root == NULL) {
			root = node;
		}
	}
	else {
		if (node->data > data) {
			node->left = insertNode(data,node->left);
		}
		else {
			node->right = insertNode(data, node->right);
		}
	}
	return node;
}
Node* searchNode(int data, Node* node) {
	if (node == NULL) {
		return NULL;
	}
	if (node->data == data) {
		return node;
	}
	else if (node->data > data) {
		return searchNode(data, node->left);
	}
	else {
		return searchNode(data, node->right);
	}
}
Node* findMinNode(Node* node) {
	curr = node;
	while (curr->left != NULL) {
		curr = curr->left;
	}
	return curr;
}
Node* findMaxNode(Node* node) {
	curr = node;
	while (curr->right != NULL) {
		curr = curr->right;
	}
	return curr;
}
Node* deleteNode(int data,Node* node) {
	if (node == NULL) {
		return NULL;
	}
	if (node->data > data) {
		node->left = deleteNode(data, node->left);
	}
	else if (node->data < data) {
		node->right = deleteNode(data, node->right);
	}
	else {
		if (node->right != NULL) {
			Node* temp = findMinNode(node->right);
			node->data = temp->data;
			node->right = deleteNode(temp->data, node->right);
		}
		else if (node->left != NULL) {
			Node* temp = findMaxNode(node->left);
			node->data = temp->data;
			node->left = deleteNode(temp->data, node->left);
		}
		else {
			return NULL;
		}
	}
	return node;
}
void preOrder(Node* node) {
	if (node != NULL) {
		printf("%d ", node->data);
		preOrder(node->left);
		preOrder(node->right);
	}
}
void inOrder(Node* node) {
	if (node != NULL) {
		inOrder(node->left);
		printf("%d ", node->data);
		inOrder(node->right);
	}
}
void postOrder(Node* node) {
	if (node != NULL) {
		postOrder(node->left);
		postOrder(node->right);
		printf("%d ", node->data);
	}
}
void main() {
	//queue = (Node**)calloc(1, sizeof(Node*));
	//queue[0] = create(-1);
	int arr[10] = { 21,7,38,61,15,17,42,81,100,1 };
	for (int i = 0; i < 10; i++) {
		insertNode(arr[i], root);
	}
	printf("%d\n", searchNode(61, root)->data);
	deleteNode(61,root);
	if (searchNode(61, root) != NULL) {

		printf("%d\n", searchNode(61, root)->data);
	}
	else {
		printf("XXXXX\n");
		printf("%d\n", root->right->right->data);
		printf("%d\n", root->right->right->right->data);
	}
	preOrder(root);
	printf("\n");
	inOrder(root);
	printf("\n");
	postOrder(root);
	printf("\n");
}