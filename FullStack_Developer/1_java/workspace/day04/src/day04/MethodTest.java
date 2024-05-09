package day04;

import java.util.Date;
import java.util.Scanner;

public class MethodTest {
	
	
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		sc.next();
//		Alt + Shift + R : 선언한 곳과 사용한 곳 함께 이름 변경
		MethodTest v = new MethodTest();
		System.out.println(v.f(3));
	
	}
	int f(int x){
		return 2*x+1;
	}
	//Hello 라는 문자열을 출력하는 메소드
	void printHello(){
		System.out.println("Hello");
	}
	
	//내 이름("정다솔")을 10번 출력하는 메소드
	void printName(){
		for (int i = 0; i < 10; i++) {
			System.out.println("정다솔");
		}
	}
	
//	Ctrl + M : 현재 보고있는 창 최대화
	//착한 일을 하면 선물을 돌려주는 메소드
	//메소드 이름이 get~~이다 : ~~를 받아오기 위해 호출하는 메소드(보통 return값이 있다는 뜻)
	String getGift(String action){
		String result = "";
		if(action.equals("착한일")) {
//			return "선물";
			result = "선물";
		}
		else {
//			return "똥";
			result = "똥";
		}
		return result;
	}
	
	//현재 시간정보를 알려주는 메소드
	//넘겨주는 값과 상관없이 결과가 달라진다면, 보통 매개변수가 필요하지 않다.
	Date getTime(){
		return new Date();
	}
	
	//구구단의 한 단을 출력해주는 메소드
	void printDan(int dan){
		for (int i = 1; i <= 9; i++) {
			System.out.printf("%d X %d = %d\n",dan,i,dan*i);
		}
	}
}











