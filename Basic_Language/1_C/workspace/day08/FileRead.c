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
			//�� �ٸ��� ���Ⱑ ���ԵǾ� �����Ƿ� fscanf_s �� ���⵵ ���������� ��´�.
			//���� ���ڿ� �迭�� �ΰ��� �ʿ��ϴ�.
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