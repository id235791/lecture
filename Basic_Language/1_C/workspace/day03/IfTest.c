//IfTest.c
#include<stdio.h>
void main() {
	int choice = 0;
	printf("���� �� ���α׷��� �� �ƴѰ���?\n");
	printf("1. C���\n2. ���̽�\n3. ���վ�\n4. JAVA\n");
	scanf_s("%d", &choice);
	if (choice == 3) {
		printf("�����Դϴ�!\n");
	}
	else if (choice == 1 || choice == 2 || choice == 4) {
		printf("�����Դϴ�!\n");
	}
	else {
		printf("�߸� �Է��ϼ̽��ϴ�!\n");
	}

}






