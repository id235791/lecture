//Tree.c
#include<stdio.h>
#include<math.h>
char arr[100];
int idx = 1;
void insert(char data) {
	arr[idx++] = data;
}
void print() {
	int num = 1;
	for (int i = 1; i < idx; i++) {
		if (i == pow(2, num)) {
			printf("\n");
			num++;
		}
		printf("%c ",arr[i]);
	}
}
void main() {
	for (int i = 0; i < 26; i++) {
		insert(i + 65);
	}
	print();
}