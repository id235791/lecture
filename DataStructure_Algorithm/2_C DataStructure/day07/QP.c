#include<stdio.h>
#include<string.h>
#define MAX 7
int move = 1;
int arData[MAX] = { 0, };
int h(int data) {
	return data % MAX;
}

void main() {
	int data;
	for (int i = 0; i < 5; i++) {
		printf("µ¥ÀÌÅÍ : ");
		scanf_s("%d", &data);
		int idx = h(data);
		int temp = idx;
		while (1) {
			if (arData[temp] == 0) {
				arData[temp] = data;	
				move = 1;
				for (int j = 0; j < MAX; j++) {
					printf("%d : %d\n", j, arData[j]);
				}
				break;
			}
			else {
				temp = (idx + move * move) % MAX;
				move++;
			}
		}
	}


}



