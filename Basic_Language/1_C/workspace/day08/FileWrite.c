#include<stdio.h>
//fopen()�� ������ �Լ��̱� ������ ������ ������ pragma�� ��ó����ɾ�� �������ش�.
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
	//fclose(����������); : �� ����� ���������� �ݳ�
	fprintf(fp,"%s\n","�׵��� ����ϼ̽��ϴ�.");
	fclose(fp);
	*/
	fp = fopen("test.txt", "a");
	fprintf(fp, "%s\n", "GoodBye C!!!!");
	fclose(fp);
}