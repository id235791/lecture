//SwitchTest.c
#include<stdio.h>
void main() {
	int choice = 0;
	printf("다음 중 프로그래밍 언어가 아닌것은?\n");
	printf("1. C언어\n2. 파이썬\n3. 망둥어\n4. JAVA\n");
	scanf_s("%d", &choice);
	switch (choice) {
	case 3:
		printf("정답입니다!\n");
		break;
	case 1:case 2:case 4:
		printf("오답입니다!\n");
		break;
	default:
		printf("잘못 입력하셨습니다!\n");
	}
}