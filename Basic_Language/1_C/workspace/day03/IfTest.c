//IfTest.c
#include<stdio.h>
void main() {
	int choice = 0;
	printf("다음 중 프로그래밍 언어가 아닌것은?\n");
	printf("1. C언어\n2. 파이썬\n3. 망둥어\n4. JAVA\n");
	scanf_s("%d", &choice);
	if (choice == 3) {
		printf("정답입니다!\n");
	}
	else if (choice == 1 || choice == 2 || choice == 4) {
		printf("오답입니다!\n");
	}
	else {
		printf("잘못 입력하셨습니다!\n");
	}

}






