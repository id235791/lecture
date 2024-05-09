package day01;

public class VariableTest {
	public static void main(String[] args) {
		int age = 10;
		String name = "김사과";
		float height = 180.24f;
		double weight = 84.11;
		char score = 'B';
		
		//공간으로써 사용
		age = 20;
		
		//값으로써 사용
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age+"살");
		//%.2f : 소수점 둘째자리까지 출력
		System.out.printf("키 : %.2fcm\n",height);
		System.out.print("몸무게 : "+weight+"kg\n");
		System.out.println("성적 : "+score);
	}
}
