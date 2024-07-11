package day05;

import java.util.Scanner;

public class School_NoComment {
	public static void main(String[] args) {
//		CRUD
//		1. 학생 추가
//		2. 학생 목록
//		3. 학생 삭제
//		4. 학생 수정
//		5. 성적 입력
//		6. 석차 보기
//		7. 나가기
		Scanner sc = new Scanner(System.in);
		String name = "";
		int ban = 0;
		int bun = 0;
		int studCnt = 0;
		boolean flag = false;
		
		Student[] list = new Student[1000];
		while(true) {
			System.out.println("1. 학생 추가\n2. 학생 목록\n3. 학생 삭제\n4. 학생 수정\n5. 성적 입력\n6. 석차 보기\n7. 나가기");
			int choice = sc.nextInt();
			if(choice == 7) {
				System.out.println("안녕히가세요...☆");
				break;
			}
			switch(choice) {
			case 1:
				//학생 추가
				System.out.print("이름 : ");
				name = sc.next();
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				list[studCnt] = new Student(name, ban, bun);
				studCnt++;
				
				System.out.println(name+" 학생 등록 완료!");
				break;
			case 2:
				//학생 목록
				System.out.println("=====☆KH고등학교 학생목록☆=====");
				for (int i = 0; i < studCnt; i++) {
					Student temp = list[i];
					//김사과(1반 1번) - 총점 : 0점
//					System.out.printf("%s(%d반 %d번) - 총점 : %d점",temp.name,temp.ban,temp.bun,temp.tot);
					//String.format("서식문자열",값1,값2,...) : 서식대로 문자열을 생성
					//printf와의 차이점 : printf는 단순 콘솔 출력만 가능, format은 데이터를 생성하기 때문에 사용 확장성
					String data = String.format("%s(%d반 %d번) - 총점 : %d점",temp.name,temp.ban,temp.bun,temp.tot);
					System.out.println(data);
				}
				System.out.println("===========================");
				break;
			case 3:
				//학생 삭제
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				for (int i = 0; i < studCnt; i++) {
					Student temp = list[i];
					if(temp.ban == ban) {
						if(temp.bun == bun) {
							for (int j = i; j < studCnt-1; j++) {
								list[j] = list[j+1];
							}
							list[studCnt-1] = null;
							studCnt--;
							break;
						}
					}
				}
				break;
			case 4:
				//학생 수정
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				flag = false;
				for (int i = 0; i < studCnt; i++) {
					Student temp = list[i];
					if(temp.ban == ban) {
						if(temp.bun == bun) {
							flag = true;
							System.out.print("새로운 이름 : ");
							temp.name = sc.next();
							break;
						}
					}
				}
				
				if(flag == true) {
					System.out.println("학생 수정 성공!");
				}
				else {
					System.out.println("일치하는 학생을 찾을 수 없습니다!");
				}
				break;
			case 5:
				//성적 입력
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				flag = false;
				for (int i = 0; i < studCnt; i++) {
					Student temp = list[i];
					if(temp.ban == ban) {
						if(temp.bun == bun) {
							flag = true;
							System.out.print("국어점수 : ");
							temp.kor = sc.nextInt();
							System.out.print("영어점수 : ");
							temp.eng = sc.nextInt();
							System.out.print("수학점수 : ");
							temp.math = sc.nextInt();
							
							temp.tot = temp.kor+temp.eng+temp.math;
							break;
						}
					}
				}
				if(flag == true) {
					System.out.println("성적 입력 성공!");
				}
				else {
					System.out.println("일치하는 학생을 찾을 수 없습니다!");
				}
				break;
			case 6:
				//석차 보기
				break;
			}
		}
		
	}
}
class Student {
	String name;
	int ban;
	int bun;
	
	int kor;
	int eng;
	int math;
	int tot;
	
	public Student(String name, int ban, int bun) {
		this.name = name;
		this.ban = ban;
		this.bun = bun;
	}
}











