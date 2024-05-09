package school;

import java.util.Scanner;

public class School {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name = null;
		int ban = 0;
		int bun = 0;
		
		while(true) {
			System.out.println("1. 학생 추가\n2. 학생 목록\n3. 학생 삭제\n4. 학생 수정\n"
					+ "5. 성적 입력\n6. 석차 보기\n7. 종료");
			int choice = sc.nextInt();
			if(choice == 7) {
				//7. 종료
				System.out.println("안녕히가세요!");
				break;
			}
			switch(choice) {
			case 1:
				System.out.print("이름 : ");
				name = sc.next();
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				Manager m = new Manager();
				m.insert(name,ban,bun);
				
				System.out.println(name+" 학생 등록 완료!");
				break;
			case 2:
				m = new Manager();
				System.out.println(m.selectAll());
				break;
			case 3:
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				m = new Manager();
				int idx = m.selectIdx(ban, bun);
				
				if(idx == -1) {
					System.out.println("일치하는 학생을 찾을 수 없습니다!");
				}
				else {
					m.delete(idx);
					System.out.println("학생 삭제 완료!");
				}
				break;
			case 4:
				//4. 학생 수정
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				m = new Manager();
				Student t = m.select(ban, bun);
				if(t != null) {
					System.out.print("새로운 이름 : ");
					t.name = sc.next();
					System.out.println("수정 성공!");
				}
				else {
					System.out.println("일치하는 학생을 찾을 수 없습니다!");
				}
				break;
			case 5:
				//5. 성적 입력
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				m = new Manager();
				t = m.select(ban, bun);
				if(t != null) {
					System.out.print("국어점수 : ");
					t.kor = sc.nextInt();
					System.out.print("영어점수 : ");
					t.eng = sc.nextInt();
					System.out.print("수학점수 : ");
					t.math = sc.nextInt();
					t.tot = t.kor + t.eng + t.math;
					System.out.println("성적 입력 완료!");
				}
				else {
					System.out.println("일치하는 학생을 찾을 수 없습니다!");
				}
				break;
			case 6:
				//6. 석차 보기
				m = new Manager();
				m.getRanking();
				break;
			}
		}
	}
}









