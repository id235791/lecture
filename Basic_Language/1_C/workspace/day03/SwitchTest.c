//SwitchTest.c
#include<stdio.h>
void main() {
	int choice = 0;
	printf("���� �� ���α׷��� �� �ƴѰ���?\n");
	printf("1. C���\n2. ���̽�\n3. ���վ�\n4. JAVA\n");
	scanf_s("%d", &choice);
	switch (choice) {
	case 3:
		printf("�����Դϴ�!\n");
		break;
	case 1:case 2:case 4:
		printf("�����Դϴ�!\n");
		break;
	default:
		printf("�߸� �Է��ϼ̽��ϴ�!\n");
	}
}