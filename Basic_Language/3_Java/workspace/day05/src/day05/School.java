package day05;

import java.util.Scanner;

public class School {
	public static void main(String[] args) {
		//1. 학생 추가
		//2. 학생 목록
		//3. 학생 삭제
		//4. 학생 수정
		//5. 성적 입력
		//6. 석차 보기
		//7. 종료
		Scanner sc = new Scanner(System.in);
		String name = null;
		int ban = 0;
		int bun = 0;
		
		//등록된 학생들의 정보를 객체화 해서 저장할 배열, 정보(데이터)를 객체단위로 저장
		//배열의 각 방 하나하나가 Student 타입의 객체
		Student[] list = new Student[1000];
		//현재까지 등록된 학생의 수 == 새로 등록될 학생 객체가 들어갈 인덱스 번호
		int cnt = 0;
		boolean flag = false;
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
				//1. 학생 추가
				System.out.print("이름 : ");
				name = sc.next();
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				//여기까지 왔으면 학생을 등록하기 위한 모든 정보를 입력 받았으므로
				//학생 객체를 새로 생성해서(new Student(name,ban,bun))
				//모든 학생 객체들을 저장해주는 list 배열에 저장해준다.(list[] = ) 
				//새로 등록되는 학생이 들어갈 방 번호는 cnt이고 새롭게 학생이 등록되었기 때문에
				//저장 후에는 cnt를 하나 증가시켜주어야 한다.(cnt++)
				list[cnt++] = new Student(name, ban, bun);
				
				System.out.println(name+" 학생 등록 완료!");
				break;
			case 2:
				//2. 학생 목록
				//for문을 현재 등록된 학생 수만큼 반복하면서
				//list[i] : list 배열 전체 순회(학생 객체를 하나씩 꺼내온다)
				for (int i = 0; i < cnt; i++) {
					//꺼내오는 객체 내부의 필드를 사용하려면 너무 코드가 길어지기 때문에(list[i].ban)
					//t라는 임시 변수를 만들어서 그 주소값을 그대로 담아준다.
					Student t = list[i];
					//t에 담겨있는 꺼내온 객체(순회중인 객체)의 필드를 이용해서 출력
					System.out.printf("%d반 %d번 : %s(총점 : %d점)\n", t.ban, t.bun, t.name, t.tot);
				}
				break;
			case 3:
				//3. 학생 삭제
				break;
			case 4:
				//4. 학생 수정
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				//flag 기법
				flag = false;
				//for문 전체 순회하며
				for (int i = 0; i < cnt; i++) {
					//저장된 학생 객체 꺼내오기(t)
					Student t = list[i];
					//t의 ban, t의 bun(저장된 학생의 반, 번호와)/ 입력받은 반 번호(ban, bun)를 비교
					if(t.ban == ban) {
						if(t.bun == bun) {
							//일치하는 정보를 가진 학생 객체를 찾았다는 뜻
							//아래에서 이름 수정
							System.out.print("새로운 이름 : ");
//							name = sc.next();
//							t.name = name;
							//꺼내온 객체의 name에 새로운 값 입력받아서 덮어씌우기
							t.name = sc.next();
							//수정되었다는 의미로 깃발들기
							flag = true;
							break;							
						}
					}
				}
				//위의 for문에서 "flag=true;" 코드를 만나지 않았다면
				//flag 값은 66번줄에서 넣어둔 false 일 것이다.
				//위의 for문 안의 if문에 들어갔다면 true값으로 바뀌었을 것임으로,
				//if문에 들렸었다면 아래에서도 if문에 들어가고 안들렸더라면 else문으로 가게 된다.
				//깃발이 들렸는지
				if(flag) {
					System.out.println("수정 성공!");
				}
				//깃발이 안들렸는지
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
				
				flag = false;
				//또회
				for (int i = 0; i < cnt; i++) {
					//또객체
					Student t = list[i];
					if(t.ban == ban) {
						if(t.bun == bun) {
							//찾은 객체의 kor, eng, math 변수에 점수 입력받기
							System.out.print("국어점수 : ");
							t.kor = sc.nextInt();
							System.out.print("영어점수 : ");
							t.eng = sc.nextInt();
							System.out.print("수학점수 : ");
							t.math = sc.nextInt();
							//입력받은 값으로 총점 구해서 찾은 객체(t)의 tot에 넣어주기
							t.tot = t.kor + t.eng + t.math;
							//깃발들고
							flag = true;
							//탈출
							break;
						}
					}
				}
				if(flag) {
					System.out.println("성적 입력 완료!");
				}
				else {
					System.out.println("일치하는 학생을 찾을 수 없습니다!");
				}
				break;
			case 6:
				//6. 석차 보기
				break;
			}
		}
	}
}
class Student{
	String name;
	int ban;
	int bun;
	
	int kor;
	int eng;
	int math;
	int tot;
	
	Student(String name, int ban, int bun){
		this.name = name;
		this.ban = ban;
		this.bun = bun;
	}
	
}











