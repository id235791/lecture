#include<stdio.h>
#include<string.h>
void main() {
	char msg[6] = "Hello";
	//msg = "Korea"; //�Ұ���
	strcpy_s(msg,sizeof(msg),"Korea");
	printf("%s\n", msg);

	//printf("%d\n", strcmp(msg, "Korea"));
	//strcmpy�� �� ���ڿ��� ������ 0, �ٸ��ٸ� 1 or -1
	if (!strcmp(msg,"Korea")) {
		printf("�� ���ڿ��� �����ϴ�.\n");
	}
	printf("���ڿ��� ���� : %d\n",strlen(msg));
}