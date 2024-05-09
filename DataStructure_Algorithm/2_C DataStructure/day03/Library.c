#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct Book {
	char booknum[5];
	char bookname[100];
	char author[100];
	char company[100];
}Book;

typedef struct Node {
	struct Book* data;
	struct Node* next;
	struct Node* prev;
}Node;


Node* create(char* booknum, char* bookname, char* author, char* company) {
	Node* newNode = (Node*)calloc(1, sizeof(Node));
	Book* newBook = (Book*)calloc(1, sizeof(Book));
	newNode->data = newBook;
	strcpy_s(newBook->booknum, sizeof(newBook->booknum), booknum);
	strcpy_s(newBook->bookname, sizeof(newBook->bookname), bookname);
	strcpy_s(newBook->author, sizeof(newBook->author), author);
	strcpy_s(newBook->company, sizeof(newBook->company), company);
	return newNode;
}
Node* head;
Node* tail;
int count = 0;
Node* curr;
void append(char* booknum, char* bookname, char* author, char* company) {
	Node* newNode = create(booknum, bookname, author, company);
	newNode->next = tail;
	newNode->prev = tail->prev;

	tail->prev->next = newNode;
	tail->prev = newNode;
	count++;
}
void main() {
	char booknum[5];
	char bookname[100];
	char author[100];
	char company[100];
	printf("도서번호 : ");
	scanf_s("%s", booknum, sizeof(booknum));
	printf("도서명 : ");
	scanf_s("%s", bookname, sizeof(bookname));
	printf("저자명 : ");
	scanf_s("%s", author, sizeof(author));
	printf("출판사 : ");
	scanf_s("%s", company, sizeof(company));
	append(booknum, bookname, author, company);
}