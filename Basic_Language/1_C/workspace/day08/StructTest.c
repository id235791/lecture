//StructTest.c
#include<stdio.h>
//�ؾ Ʋ
struct User {
	char name[30];
	char userid[100];
	char userpw[100];
};
void main() {
	/*
	char name[30];
	char userid[100];
	char userpw[100];
	printf("�̸��� �Է��ϼ��� : ");
	scanf_s("%s", name, sizeof(name));
	printf("���̵� �Է��ϼ��� : ");
	scanf_s("%s", userid, sizeof(userid));
	printf("��й�ȣ�� �Է��ϼ��� : ");
	scanf_s("%s", userpw, sizeof(userpw));
	*/
	//.(����������)
	//	A.b : A ���� b / A �� b
				//�ؾ
	struct User user1;
	printf("�̸��� �Է��ϼ��� : ");
	scanf_s("%s", user1.name, sizeof(user1.name));
	printf("���̵� �Է��ϼ��� : ");
	scanf_s("%s", user1.userid, sizeof(user1.userid));
	printf("��й�ȣ�� �Է��ϼ��� : ");
	scanf_s("%s", user1.userpw, sizeof(user1.userpw));
}