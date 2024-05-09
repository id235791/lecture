//StructTest.c
#include<stdio.h>
//붕어빵 틀
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
	printf("이름을 입력하세요 : ");
	scanf_s("%s", name, sizeof(name));
	printf("아이디를 입력하세요 : ");
	scanf_s("%s", userid, sizeof(userid));
	printf("비밀번호를 입력하세요 : ");
	scanf_s("%s", userpw, sizeof(userpw));
	*/
	//.(하위연산자)
	//	A.b : A 안의 b / A 의 b
				//붕어빵
	struct User user1;
	printf("이름을 입력하세요 : ");
	scanf_s("%s", user1.name, sizeof(user1.name));
	printf("아이디를 입력하세요 : ");
	scanf_s("%s", user1.userid, sizeof(user1.userid));
	printf("비밀번호를 입력하세요 : ");
	scanf_s("%s", user1.userpw, sizeof(user1.userpw));
}