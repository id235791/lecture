#include<stdio.h>
//fopen()은 구버전 함수이기 때문에 오류를 무시할 pragma를 전처리명령어로 설정해준다.
#pragma warning(disable:4996)
void main() {
	FILE* fp;
	/*
	fp = fopen("test.txt","w");
	fputc('H',fp);
	fputc('e', fp);
	fputc('l', fp);
	fputc('l', fp);
	fputc('o', fp);
	fputc(' ', fp);
	fputc('C', fp);
	fputc('!', fp);
	fputc('!', fp);
	fputc('!', fp);
	fputc('!', fp);
	fputc('\n', fp);
	//fclose(파일포인터); : 다 사용한 파일포인터 반납
	fprintf(fp,"%s\n","그동안 고생하셨습니다.");
	fclose(fp);
	*/
	fp = fopen("test.txt", "a");
	fprintf(fp, "%s\n", "GoodBye C!!!!");
	fclose(fp);
}