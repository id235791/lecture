package day05;

import java.util.Scanner;

public class School_Comment {
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
		boolean flag = false;
		
		//등록된 학생들의 정보를 객체화 해서 저장할 배열, 정보(데이터)를 객체단위로 저장
		//배열의 각 방 하나하나가 Student 타입의 객체
		Student[] list = new Student[1000];
		//현재까지 등록된 학생의 수 == 새로 등록될 학생 객체가 들어갈 방(인덱스) 번호
		int studCnt = 0;
		
		while(true) {
			System.out.println("1. 학생 추가\n2. 학생 목록\n3. 학생 삭제\n"
					+ "4. 학생 수정\n5. 성적 입력\n6. 석차 보기\n7. 나가기");
			int choice = sc.nextInt();
			if(choice == 7) {
				//종료
				System.out.println("안녕히가세요...☆");
				break;
			}
			//위에서 7(종료)을 선택하지 않았다면 그대로 switch문까지 흐름이 내려온다.
			switch(choice) {
			case 1:
				//학생 추가
				System.out.print("이름 : ");
				name = sc.next();
				System.out.print("반 : ");
				ban = sc.nextInt();
				System.out.print("번호 : ");
				bun = sc.nextInt();
				
				//여기까지 왔으면 학생을 등록하기 위한 모든 정보를 입력 받았으므로
				//각 이름, 반, 번호를 저장해준다.
				//이름, 반, 번호를 따로 저장하게 되면 각각 정보들 마다 배열이 필요하므로
				//하나의 객체로 포장해서 저장을 해준다.
				//학생 객체를 새로 생성해서( new Student(name,ban,bun) )
				//모든 학생 객체들을 저장해주는 list 배열에 담아준다.(list[] = )
				//새로 등록되는 학생이 들어갈 방 번호는 현재 학생수인 studCnt와 일치하고
				//새롭게 학생이 등록되었기 때문에 저장 후에는 studCnt를 하나 증가시켜 준다.
				list[studCnt] = new Student(name, ban, bun);
				studCnt++;
				
				System.out.println(name+" 학생 등록 완료!");
				break;
			case 2:
				//학생 목록
				System.out.println("=====☆KH고등학교 학생목록☆=====");
				//for문을 현재 등록된 학생 수 만큼 반복하면서
				//list[i] : list 배열 전체 순회(학생 객체들을 하나씩 꺼내온다.)
				for (int i = 0; i < studCnt; i++) {
					//꺼내오는 객체 내부의 필드를 이용하려면 list[i].ban 과 같은 형태로 사용해야 하므로
					//코드가 너무 길어진다. 따라서 temp라는 임시 변수를 만들어서 그 주소값을 그대로 담아준다.
					Student temp = list[i];
					
					//김사과(1반 1번) - 총점 : 0점
//					System.out.printf("%s(%d반 %d번) - 총점 : %d점",temp.name,temp.ban,temp.bun,temp.tot);
					//String.format("서식문자열",값1,값2,...) : 서식대로 문자열을 생성
					//printf와의 차이점 : printf는 단순 콘솔 출력만 가능, format은 데이터를 생성하기 때문에 사용 확장성

					//temp에 담겨있는 꺼내온 객체(순회중인 객체)의 필드를 이용해서 출력하기 위한 문자열(data) 생성
					String data = String.format("%s(%d반 %d번) - 총점 : %d점",temp.name,temp.ban,temp.bun,temp.tot);
					//출력!
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
				
				//for문으로 전체 순회 하면서
				for (int i = 0; i < studCnt; i++) {
					//학생 객체 하나씩 꺼내오기
					Student temp = list[i];

					//꺼내온 객체의 ban과 입력한 ban이 같은지 비교
					if(temp.ban == ban) {
						//꺼내온 객체의 bun과 입력한 bun이 같은지 비교
						if(temp.bun == bun) {
							//삭제할 객체를 찾은 상황(현재 순회중인 객체가 삭제할 객체라는 뜻)
							//현재 순회중인 객체가 존재하는 방(i) 부터 가장 마지막에서 두번째 방(studCnt-1)까지 순회
							for (int j = i; j < studCnt-1; j++) {
								//순회하는 방 뒤에 있는 주소값으로 덮어씌우기
								//ex) 1번방에는 2번방의 주소값 넣기, 2번방에는 3번방의 주소값 넣기, ...
								//....., studCnt-2번방에는 studCnt-1번방의 주소값 넣기
								list[j] = list[j+1];
							}
							//for문이 끝나고 나왔을 때 가장 마지막 학생객체(studCnt-1번 방에 있던 객체)의 주소값은
							//두 곳에 저장되어 있는 상태이다.(studCnt-2번방, studCnt-1번방)
							//중복을 피하기 위해 가장 마지막에 있는 studCnt-1번방에는 null로 직접 초기화해준다. 
							list[studCnt-1] = null;
							//학생이 삭제되었으므로 전체 학생수는 1 감소
							studCnt--;
							//삭제 처리를 완료했으므로 더 이상 순회는 불필요하므로 탈출
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
				
				//flag기법 : 특정 영역에 들어갔는지를 판단하기 위한 알고리즘
				flag = false;
				//for문을 전체 순회하면서
				for (int i = 0; i < studCnt; i++) {
					//저장된 학생 객체 꺼내오기(temp)
					Student temp = list[i];
					//temp의 반과 번호와 입력받은 반과 번호를 비교
					if(temp.ban == ban) {
						if(temp.bun == bun) {
							//일치하는 정보를 가진 학생 객체를 찾았다는 뜻
							//해당 영역에 들어왔다는 의미로 깃발을 들어준다.(flag값을 true로 바꿔준다.)
							flag = true;
							//수정할 객체(temp)의 이름 수정
							System.out.print("새로운 이름 : ");
							temp.name = sc.next();
							break;
						}
					}
				}
				//위의 for문 내부의 "flag = true;" 코드를 만나지 못했다면
				//flag값은 126번줄에서 넣어둔 false값을 그대로 가지고 있을 것이다.
				//위의 for문 내부의 if문에 들어갔다면 true값으로 바뀌었을 것임으로,
				//if문에 들렸었다면 아래에서도 if문에 들어가고, 안들렸더라면 else문으로 가게 된다.
				//깃발이 들렸는지
				if(flag) {
					System.out.println("학생 수정 성공!");
				}
				//깃발이 안들렸는지
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
							//일치하는 학생 찾기
							flag = true;
							//찾은 학생의 kor, eng, math 필드에 점수 입력받아서 채워넣기
							System.out.print("국어점수 : ");
							temp.kor = sc.nextInt();
							System.out.print("영어점수 : ");
							temp.eng = sc.nextInt();
							System.out.print("수학점수 : ");
							temp.math = sc.nextInt();
							
							//찾은 학생의 tot 에다가 총 점 구해서 넣기
							temp.tot = temp.kor+temp.eng+temp.math;
							break;
						}
					}
				}
				if(flag) {
					System.out.println("성적 입력 성공!");
				}
				else {
					System.out.println("일치하는 학생을 찾을 수 없습니다!");
				}
				break;
			case 6:
				//석차 보기
				//파이팅!
				break;
			}
		}
		
	}
}
//여러 흩어져 있는 정보들을 하나로 뭉쳐서 포장하기 위한 용도의 클래스
//name, ban, bun, .. 을 따로 관리하는 것이 아니라
//Student 타입의 객체 한 개로 한번에 관리

//class Student {
//	String name;
//	int ban;
//	int bun;
//	
//	int kor;
//	int eng;
//	int math;
//	int tot;
//	
//	public Student(String name, int ban, int bun) {
//		this.name = name;
//		this.ban = ban;
//		this.bun = bun;
//	}
//}











