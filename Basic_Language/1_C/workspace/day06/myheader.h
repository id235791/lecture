//myheader.h
#include<stdio.h>
#include<string.h>
//1~10 까지 출력하는 함수
void print1to10() {
	for (int i = 1; i <= 10; i++) {
		printf("%d ", i);
	}
	printf("\n");
}
//1~10 까지의 합 구해주는 함수
int sum1to10() {
	int tot = 0;
	for (int i = 1; i <= 10; i++) {
		tot += i;
	}
	return tot;
}
//1~n 까지의 합 구해주는 함수
int sum1toN(int n) {
	int tot = 0;
	for (int i = 1; i <= n; i++) {
		tot += i;
	}
	return tot;
}


// 문자열은 char* 
//어떤 문자열을 n번만큼 출력하는 함수
void printMsg(char* msg, int n) {
	for (int i = 0; i < n; i++) {
		printf("%s\n", msg);
	}
}
//두 정수의 나눗셈 결과를 구해주는 함수
double div(int num1, int num2) {
	return num1 / (double)num2;
}
//n부터 m까지의 합 구해주는 함수
int sumNtoM(int n, int m) {
	int tot = 0;
	for (int i = n; i <= m; i++) {
		tot += i;
	}
	return tot;
}

//문자열의 소문자는 대문자로, 대문자는 소문자로
//그 외에는 그대로 바꿔서 돌려주는 함수(길이는 100자 제한)
char* change(char* msg) {
	//배열 선언시 {값,} 로 선언하면 첫번째 방만 정해준 값이 들어가고
	//그 외에는 초기값으로 채워진다.
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

