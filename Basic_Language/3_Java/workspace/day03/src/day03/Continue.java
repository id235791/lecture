package day03;

public class Continue {
	public static void main(String[] args) {
//		for (int i = 1; i <= 10; i++) {
//			if(i == 10) {
//				continue;
//			}
////			11은 출력되지 않음(조건식 검사시 false)
//			System.out.println(i);
//		}
		for (int i = 1; i <= 10; i++) {
			if(i == 4) {
				continue;
			}
			System.out.println(i);
		}
		for (int i = 1; i <= 10; i++) {
			if(i == 4) {
			}
			else {
				System.out.println(i);
			}
		}
	}
}






