#include<stdio.h>
#include<string.h>
void main() {
	char msg[6] = "Hello";
	//msg = "Korea"; //불가능
	strcpy_s(msg,sizeof(msg),"Korea");
	printf("%s\n", msg);

	//printf("%d\n", strcmp(msg, "Korea"));
	//strcmpy는 두 문자열이 같으면 0, 다르다면 1 or -1
	if (!strcmp(msg,"Korea")) {
		printf("두 문자열이 같습니다.\n");
	}
	printf("문자열의 길이 : %d\n",strlen(msg));
}