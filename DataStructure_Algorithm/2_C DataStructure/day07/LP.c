#include<stdio.h>
#define MAX 7
#define move 2
int arr[MAX];
int h(int x) {
	return x % MAX;
}
void main() {
	int num = 0;
	int idx = 0;
	for (int i = 0; i < MAX; i++) {
		printf("추가할 데이터 : ");
		scanf_s("%d",&num);
		idx = h(num);
		while (1) {
			if (arr[idx] == 0) {
				arr[idx] = num;
				for (int j = 0; j < MAX; j++) {
					printf("%d : %d\n", j, arr[j]);
				}
				break;
			}
			else {
				idx = (idx + move) % MAX;
			}
		}
	}
}