package school;

import java.util.Scanner;

public class View {
	void addStudent() {
		String name;
		int ban;
		int bun;
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 : ");
		name = sc.next();
		System.out.print("반 : ");
		ban = sc.nextInt();
		System.out.print("번호 : ");
		bun = sc.nextInt();
		
		Manager manager = new Manager();
		manager.insert(name, ban, bun);
		
		System.out.println(name+" 학생 등록 완료!");
	}
	void showList() {
		System.out.println("=====☆KH고등학교 학생목록☆=====");
		Manager manager = new Manager();
		System.out.println(manager.selectAll());
		System.out.println("===========================");
	}
	void removeStudent() {
		int ban;
		int bun;
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		
		System.out.print("반 : ");
		ban = sc.nextInt();
		System.out.print("번호 : ");
		bun = sc.nextInt();
		
		int idx = manager.selectIdx(ban, bun);
		
		if(idx == -1) {
			System.out.println("일치하는 학생을 찾을 수 없습니다!");
		}
		else {
			//삭제
			manager.delete(idx);
			System.out.println("학생 삭제 완료!");
		}
	}
	void modifyStudent() {
		int ban;
		int bun;
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		System.out.print("반 : ");
		ban = sc.nextInt();
		System.out.print("번호 : ");
		bun = sc.nextInt();
		
		manager = new Manager();
		Student temp = manager.select(ban, bun);
		if(temp == null) {
			System.out.println("일치하는 학생을 찾을 수 없습니다!");
		}
		else {
			//수정 시도
			System.out.print("새로운 이름 : ");
			temp.name = sc.next();
//			name = sc.next();
//			manager.update(temp,name);
			System.out.println("학생 수정 성공!");
		}
	}
	void recordScore() {
		int ban;
		int bun;
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		System.out.print("반 : ");
		ban = sc.nextInt();
		System.out.print("번호 : ");
		bun = sc.nextInt();
		
		manager = new Manager();
		Student temp = manager.select(ban, bun);
		
		if(temp == null) {
			System.out.println("일치하는 학생을 찾을 수 없습니다!");
		}
		else {
			System.out.print("국어점수 : ");
			temp.kor = sc.nextInt();
			System.out.print("영어점수 : ");
			temp.eng = sc.nextInt();
			System.out.print("수학점수 : ");
			temp.math = sc.nextInt();
			temp.tot = temp.kor+temp.eng+temp.math;
			System.out.println("성적 입력 성공!");
		}
	}
}
