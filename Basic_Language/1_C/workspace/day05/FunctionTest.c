//FunctionTest.c
#include<stdio.h>
//f(x) = 2x+1
int f(int x) {
	return x * 2 + 1;
}
//�Լ��� ���� : main �Ʒ��ʿ� ������ �Լ��� main���� ���� ���Ǹ� �ؼ�
//�˷��־�� �Ѵ�.
//����Ÿ�� �Լ���(�ڷ���,�ڷ���,...);
void printName();
int get8Times(int);
void main() {
	printf("%d\n",f(10));
	printName();
	printf("%d\n",get8Times(5));
	
}
//���̸�("���ټ�") 10�� ����ϴ� �Լ�
void printName() {
	for (int i = 0; i < 10; i++) {
		printf("���ټ�\n");
	}

}
//�Ѱ��ִ� ������ 8�� �� ����� �����ִ� �Լ�
int get8Times(int num) {
	int result = num * 8;
	return result;
}