#include<stdio.h>
//전처리 명령어를 이용한 상수 선언
#define PI 3.14
int data1 = 10;
static int data2 = 20;
//const 키워드를 이용한 상수 선언
//const double PI = 3.14;
void f3() {
	//int data3 = 0;
	//정적 변수는 한번 선언되면 같은 공간을 계속 사용한다.
	static int data3 = 0;
	data3 += 10;
	printf("%d\n", data3);
}
void main() {
	f3();
	f3();
	printf("%f", PI);
	//PI = 7.11111;
}