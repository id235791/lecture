#include<stdio.h>
void main() {
	//1���� 100������ 3�� 5�� ����� ����ϱ�
	for (int i = 1; i <= 100; i++) {
		if (i%3==0 && i%5==0) {
			printf("%d\n", i);
		}
	}
	//!(i % 3 == 0 && i % 5 == 0)
	//  i % 3 != 0 || i % 5 != 0
	
	//A���� F���� ����ϱ�
	for (int i = 65; i <= 70; i++) {
		printf("%c\n", i);
	}
	//A(65+0)		a(97) 
	//C(65+2)		b(97+1)
	//E(65+4)		d(97+3)
	//AbCdEf......z ����ϱ�
	for (int i = 0; i < 26; i++) {
		/*
		if (i % 2 == 0) {
			printf("%c", 65 + i);
		}
		else {
			printf("%c", 97 + i);
		}
		*/
		printf("%c", i % 2 == 0 ? 65 + i : 97 + i);
	}

}