#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996);

void join(char* userid, char* userpw,int age) {
	//���� �Է��� ���� ���������� ����
	FILE* fp;
	//ȸ�������� ���� ������ �����־�� �ϰ�, ���ο� ������ �߰��ؾ� �Ѵ�.
	//"a"���� �������ش�.
	fp = fopen("user.txt", "a");
	//���̵� ��й�ȣ ���� ������ �޸��忡 ���ش�.
	fprintf(fp, "%s %s %d\n", userid, userpw, age);

	printf("%s�� ȸ������ �Ϸ�!\n", userid);
	fclose(fp);
}
int login(char* userid,char* userpw) {
	//�α����� �������� �о Ȯ�θ� �ϸ� �ǹǷ� "r"���� ����
	FILE* fp = fopen("user.txt", "r");
	
	//���� ������ �ƴ����� Ȯ���ϱ� ���� ����
	int flag = 0;

	while(1) {
		char db_id[20];
		char db_pw[20];
		int db_age;

		//db_id, db_pw,db_age�� ���� ���� ���پ� �о �˸°� �־��ش�.
		flag=fscanf(fp, "%s %s %d", db_id, db_pw, &db_age);

		//�о�� ����� ������ ���� �ǹ��ϸ� break;
		if (flag == EOF) { break; }

		//�о�°Ͱ� �Է��Ѱ� ��
		if (!strcmp(db_id, userid)) {
			if (!strcmp(db_pw, userpw)) {
				//������ 1 return
				fclose(fp);
				return 1;
			}
		}
	}
	//�� �ܿ��� 0 return
	fclose(fp);
	return 0;
}
void main() {
	while (1) {
		int choice = 0;
		//���θ޴� ���
		printf("1. ȸ������\n2. �α���\n3. ������\n");
		scanf_s("%d", &choice);
		if (choice == 3) { 
			printf("�ȳ���������~~");
			break;
		}
		else if (choice == 1) {
			//ȸ������
			char userid[20];//���̵� �Է¹��� ����
			char userpw[20];//��й�ȣ �Է¹��� ����
			int age = 0;//���� �Է¹��� ����
			printf("���̵� : ");
			scanf_s("%s", userid,sizeof(userid));
			printf("��й�ȣ : ");
			scanf_s("%s", userpw,sizeof(userpw));
			printf("���� : ");
			scanf_s("%d", &age);
			//�Է� ������ �� join �Լ��� ���̵�,��й�ȣ,���̰� �Ѱ��ֱ�
			join(userid, userpw, age);
		}
		else if (choice == 2) {
			//�α���	
			char userid[20];
			char userpw[20];
			printf("���̵� : ");
			scanf_s("%s", userid, sizeof(userid));
			printf("��й�ȣ : ");
			scanf_s("%s", userpw, sizeof(userpw));
			//�α��� �Լ��� �Է¹��� ���̵�� ��й�ȣ �Ѱ��ֱ�
			if (login(userid, userpw)) {
				//�α��� ������ �޴�
				printf("�α��� ����!\n");
			}
		}
	}
}
