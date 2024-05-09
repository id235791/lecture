package obj;

import java.util.Scanner;

public class ObjectTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//동위 객체
		TestData obj1 = new TestData(10, 0.0);
		TestData obj2 = new TestData(10, 0.0);
		
//		System.out.println(obj1.toString());
//		
//		System.out.println(obj1.hashCode());
//		System.out.println(obj2.hashCode());
		
		System.out.print("문자열 : ");
		String msg1 = sc.next();//Hello 입력
		String msg2 = "Hello";
		
		//msg1과 msg2의 주소값 비교로 인해 false(동일 객체가 아니라는 뜻)
		System.out.println(msg1 == msg2);
		//String 클래스는 동위 객체를 동일 객체로 보기 위해 equals() 재정의
		System.out.println(msg1.equals(msg2));
		
		System.out.println(obj1 == obj2);
		//Object 클래스의 equals()는 동위 객체를 동일 객체로 보지 않는다.
		//개발을 하다보면 필요에 의해 동위 객체를 동일 객체로 봐야하는 경우가 생기기 때문에
		//우리가 만든 클래스(특히 데이터를 포장하는 클래스)에서는 재정의를 할 수 있어야 한다.
		System.out.println(obj1.equals(obj2));
	}
}






