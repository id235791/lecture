#include<stdio.h>
//��ó�� ��ɾ �̿��� ��� ����
#define PI 3.14
int data1 = 10;
static int data2 = 20;
//const Ű���带 �̿��� ��� ����
//const double PI = 3.14;
void f3() {
	//int data3 = 0;
	//���� ������ �ѹ� ����Ǹ� ���� ������ ��� ����Ѵ�.
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