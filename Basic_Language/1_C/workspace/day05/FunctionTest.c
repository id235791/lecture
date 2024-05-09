//FunctionTest.c
#include<stdio.h>
//f(x) = 2x+1
int f(int x) {
	return x * 2 + 1;
}
//함수의 정의 : main 아래쪽에 선언한 함수는 main보다 위에 정의를 해서
//알려주어야 한다.
//리턴타입 함수명(자료형,자료형,...);
void printName();
int get8Times(int);
void main() {
	printf("%d\n",f(10));
	printName();
	printf("%d\n",get8Times(5));
	
}
//내이름("정다솔") 10번 출력하는 함수
void printName() {
	for (int i = 0; i < 10; i++) {
		printf("정다솔\n");
	}

}
//넘겨주는 정수의 8배 한 결과값 돌려주는 함수
int get8Times(int num) {
	int result = num * 8;
	return result;
}