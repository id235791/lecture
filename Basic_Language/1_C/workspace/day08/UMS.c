#include<stdio.h>
#include<string.h>
#pragma warning(disable:4996);

void join(char* userid, char* userpw,int age) {
	//파일 입력을 위해 파일포인터 선언
	FILE* fp;
	//회원가입은 기존 정보는 남아있어야 하고, 새로운 정보는 추가해야 한다.
	//"a"모드로 오픈해준다.
	fp = fopen("user.txt", "a");
	//아이디 비밀번호 나이 폼으로 메모장에 써준다.
	fprintf(fp, "%s %s %d\n", userid, userpw, age);

	printf("%s님 회원가입 완료!\n", userid);
	fclose(fp);
}
int login(char* userid,char* userpw) {
	//로그인은 기존정보 읽어서 확인만 하면 되므로 "r"모드로 오픈
	FILE* fp = fopen("user.txt", "r");
	
	//파일 끝인지 아닌지를 확인하기 위한 변수
	int flag = 0;

	while(1) {
		char db_id[20];
		char db_pw[20];
		int db_age;

		//db_id, db_pw,db_age로 각각 파일 한줄씩 읽어서 알맞게 넣어준다.
		flag=fscanf(fp, "%s %s %d", db_id, db_pw, &db_age);

		//읽어온 결과가 파일의 끝을 의미하면 break;
		if (flag == EOF) { break; }

		//읽어온것과 입력한것 비교
		if (!strcmp(db_id, userid)) {
			if (!strcmp(db_pw, userpw)) {
				//성공시 1 return
				fclose(fp);
				return 1;
			}
		}
	}
	//그 외에는 0 return
	fclose(fp);
	return 0;
}
void main() {
	while (1) {
		int choice = 0;
		//메인메뉴 출력
		printf("1. 회원가입\n2. 로그인\n3. 나가기\n");
		scanf_s("%d", &choice);
		if (choice == 3) { 
			printf("안녕히가세요~~");
			break;
		}
		else if (choice == 1) {
			//회원가입
			char userid[20];//아이디 입력받을 공간
			char userpw[20];//비밀번호 입력받을 공간
			int age = 0;//나이 입력받을 공간
			printf("아이디 : ");
			scanf_s("%s", userid,sizeof(userid));
			printf("비밀번호 : ");
			scanf_s("%s", userpw,sizeof(userpw));
			printf("나이 : ");
			scanf_s("%d", &age);
			//입력 마무리 후 join 함수에 아이디,비밀번호,나이값 넘겨주기
			join(userid, userpw, age);
		}
		else if (choice == 2) {
			//로그인	
			char userid[20];
			char userpw[20];
			printf("아이디 : ");
			scanf_s("%s", userid, sizeof(userid));
			printf("비밀번호 : ");
			scanf_s("%s", userpw, sizeof(userpw));
			//로그인 함수에 입력받은 아이디랑 비밀번호 넘겨주기
			if (login(userid, userpw)) {
				//로그인 성공시 메뉴
				printf("로그인 성공!\n");
			}
		}
	}
}
