//Recursive.c
#include<stdio.h>
//1~n������ �� ���ϴ� �Լ�
int f(int n) {
	int tot = 0;
	for (int i = 1; i <= n; i++) {
		tot += i;
	}
	return tot;
}
//���ȣ��(�ڱ� �ڽ��� ȣ���ϴ� ���) ���
int r(int n) {
	if (n == 1) {
		return 1;
	}
	return n + r(n - 1);
}
void main() {
	r(10);
	//10+r(9)
	//10+9+r(8)
	//10+9+8+r(7) ...
	//10+9+8+7+6+5+4+3+2+r(1)
	//10+9+8+7+6+5+4+3+2+1
}