package school;

public class Manager {
	static Student[] list = new Student[1000];
	static int studCnt = 0;
	
	void insert(String name, int ban, int bun) {
		//추가하는 코드
		list[studCnt] = new Student(name, ban, bun);
		studCnt++;
	}
	
	String selectAll() {
//		각 학생들을 순회하며
//		김사과(1반 1번) - 총점 : 0점
//		....
//		위와 같은 형태의 문자열들을 다 연결해서 문자열 데이터로 생성 후 리턴
		String result = "";
		for (int i = 0; i < studCnt; i++) {
			Student temp = list[i];
			result += String.format("%s(%d반 %d번) - 총점 : %d점\n",temp.name,temp.ban,temp.bun,temp.tot);
		}
		return result;
	}
	
	int selectIdx(int ban, int bun) {
		for (int i = 0; i < studCnt; i++) {
			Student temp = list[i];
			if(temp.ban == ban) {
				if(temp.bun == bun) {
					return i;
				}
			}
		}
		return -1;
	}
	
	Student select(int ban, int bun) {
		for (int i = 0; i < studCnt; i++) {
			Student temp = list[i];
			if(temp.ban == ban) {
				if(temp.bun == bun) {
					return temp;
				}
			}
		}
		return null;
	}
	

	public void delete(int idx) {
		for (int i = idx; i < studCnt-1; i++) {
			list[i] = list[i+1];
		}
		list[studCnt-1] = null;
		studCnt--;
	}

	
}











