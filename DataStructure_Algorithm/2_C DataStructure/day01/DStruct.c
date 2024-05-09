//DStruct.c
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct User {
	char userid[100];
	char userpw[100];
	char username[100];
	int userage;
}User;
typedef struct Test {
	int intData;
	double doubleData;
	char chData;
}Test;
void join(char* userid, char* userpw, char* username, int userage) {
	User* newUser = (User*)calloc(1,sizeof(User));
	//strcpy_s(복사할위치,위치의크기,복사할값)
	//(*newUser).userid = userid;
	//newUser->userid : (*newUser).userid
	strcpy_s(newUser->userid, sizeof(newUser->userid), userid);
	strcpy_s(newUser->userpw, sizeof(newUser->userpw), userpw);
	strcpy_s(newUser->username, sizeof(newUser->username), username);
	newUser->userage = userage;
	return newUser;
}
void main() {
	char userid[100];
	char userpw[100];
	char username[100];
	int userage;
	/*
	printf("아이디 : ");
	scanf_s("%s", userid, sizeof(userid));//apple
	printf("비밀번호 : ");
	scanf_s("%s", userpw, sizeof(userpw));
	printf("이름 : ");
	scanf_s("%s", username, sizeof(username));
	printf("나이 : ");
	scanf_s("%d", &userage);
	
	join(userid, userpw, username, userage);
	*/
	//10, -27.3, 'A'
	Test* data = (Test*)calloc(1, sizeof(Test));
	data->intData = 10;
	data->doubleData = -27.3;
	data->chData = 'A';
}