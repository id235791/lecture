package school;

public class Manager {
	static Student[] list = new Student[1000];
	static int cnt = 0;
	
	void insert(String name, int ban, int bun) {
		//추가하는 코드
		list[cnt++] = new Student(name, ban, bun);
	}
	
	String selectAll() {
//		각 학생을 순회하며 "%d반 %d번 : %s(총점 : %d점)\n" 형태의 문자열들을
//		다 연결해서 문자열 데이터로 생성 후 리턴
		String result = "";
		for (int i = 0; i < cnt; i++) {
			Student t = list[i];
//			String.format("서식문자열",값1,값2,...) : 해당하는 형태(서식)의 문자열 생성
			result += String.format("%d반 %d번 : %s(총점 : %d점)\n",t.ban,t.bun,t.name,t.tot);
		}
		return result;
	}
	
	Student select(int ban, int bun) {
		for (int i = 0; i < cnt; i++) {
			Student t = list[i];
			if(t.ban == ban) {
				if(t.bun == bun) {
					return t;
				}
			}
		}
		return null;
	}
	int selectIdx(int ban, int bun) {
		for (int i = 0; i < cnt; i++) {
			Student t = list[i];
			if(t.ban == ban) {
				if(t.bun == bun) {
					return i;
				}
			}
		}
		return -1;
	}
	
	void delete(int idx) {
		for (int i = idx; i < cnt-1; i++) {
			list[i] = list[i+1];
		}
		list[cnt-1] = null;
		cnt--;
	}

	public void getRanking() {
		
	}
}





