//PrintTest.c
//주석 : 해석하고 싶지 않은 문장에 사용한다.
//	1. 소스코드에 설명하는 글을 달 때(개발자들간의 소통)
//	2. 사용하지 않는 코드를 남겨둘 때
//한줄 주석
/*
	여러줄
	주석
*/

//# : 전처리 명령어
#include<stdio.h>//stdio.h : standard input output 헤더파일
//main함수 : 프로그램의 진입점, 모든 프로그램의 시작이되는 곳
void main() {
	printf("Hello C!\n");
	printf("day01\n");
	//"정다솔"
	printf("\"정다솔\"\n");
	printf("%d\n",27);
	printf("%d\n",3+5);
	printf("%d + %d = %d\n",10,20,10+20);
	//%.3f : 소수점 밑으로 세자리까지 처리
	printf("실수 : %.3f\n", 111.111);
	printf("문자 : %c\n", 'A');
	printf("문자열 : %s\n", "Hello");

}