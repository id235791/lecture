//myheader.h
#include<stdio.h>
#include<string.h>
//1~10 ���� ����ϴ� �Լ�
void print1to10() {
	for (int i = 1; i <= 10; i++) {
		printf("%d ", i);
	}
	printf("\n");
}
//1~10 ������ �� �����ִ� �Լ�
int sum1to10() {
	int tot = 0;
	for (int i = 1; i <= 10; i++) {
		tot += i;
	}
	return tot;
}
//1~n ������ �� �����ִ� �Լ�
int sum1toN(int n) {
	int tot = 0;
	for (int i = 1; i <= n; i++) {
		tot += i;
	}
	return tot;
}


// ���ڿ��� char* 
//� ���ڿ��� n����ŭ ����ϴ� �Լ�
void printMsg(char* msg, int n) {
	for (int i = 0; i < n; i++) {
		printf("%s\n", msg);
	}
}
//�� ������ ������ ����� �����ִ� �Լ�
double div(int num1, int num2) {
	return num1 / (double)num2;
}
//n���� m������ �� �����ִ� �Լ�
int sumNtoM(int n, int m) {
	int tot = 0;
	for (int i = n; i <= m; i++) {
		tot += i;
	}
	return tot;
}

//���ڿ��� �ҹ��ڴ� �빮�ڷ�, �빮�ڴ� �ҹ��ڷ�
//�� �ܿ��� �״�� �ٲ㼭 �����ִ� �Լ�(���̴� 100�� ����)
char* change(char* msg) {
	//�迭 ����� {��,} �� �����ϸ� ù��° �游 ������ ���� ����
	//�� �ܿ��� �ʱⰪ���� ä������.
	char result[100];
	int len = strlen(msg);
	for (int i = 0; i < len; i++) {
		char ch = msg[i];
		if (ch >= 'a' && ch <= 'z') {
			result[i] = (char)(ch - 32);
		}
		else if (ch >= 'A' && ch <= 'Z') {
			result[i] = (char)(ch + 32);
		}
		else {
			result[i] = ch;
		}
	}
	result[len] = '\0';
	return result;
}

