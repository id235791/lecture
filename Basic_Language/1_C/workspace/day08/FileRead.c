//FileRead.c
#include<stdio.h>
void main() {
	FILE* fp;
	fopen_s(&fp,"test.txt","r");
	if(fp!=NULL){
		/*
		for (int i = 0; i < 12; i++) {
			char ch = fgetc(fp);
			printf("%c", ch);
		}
		*/
		/*
		while (1) {
			char ch = fgetc(fp);
			if (ch == EOF) {
				break;
			}
			printf("%c", ch);
		}
		*/
		int flag = 0;
		while (1) {
			//각 줄마다 띄어쓰기가 포함되어 있으므로 fscanf_s 는 띄어쓰기도 구분점으로 삼는다.
			//따라서 문자열 배열이 두개가 필요하다.
			char msg1[100];
			char msg2[100];
			flag = fscanf_s(fp, "%s %s\n", msg1,sizeof(msg1),msg2,sizeof(msg2));
			if (flag == EOF) {
				break;
			}
			printf("%s %s\n", msg1,msg2);
		}
		fclose(fp);
	}
}