#include<stdio.h>
void main() {
	int age = 0;
	printf("나이를 입력하세요 : ");
	scanf_s("%d",&age);
	printf("당신의 나이는 %d 살입니다.\n",age);
	char name[100];
	printf("이름을 입력하세요 : ");
	//문자열은 이름 그대로 넘겨주기
	scanf_s("%s",name,100);
	printf("%s\n", name);
	char score = ' ';
	printf("성적을 입력하세요 : ");
	//문자 입력받는것 앞에 다른 입력이 있다면, %c 앞에 공백 하나 추가하기
	scanf_s(" %c", &score,1);
	printf("성적 : %c\n", score);

	double height = 0.0;
	printf("키를 입력하세요 : ");
	//double 타입은 입력시에는 %lf를 지켜주어야 한다.(출력때는 상관없음)
	scanf_s("%lf", &height);
	printf("키 : %fcm\n", height);
}