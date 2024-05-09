package wrapper;

public class WrapperTest2 {
	public static void main(String[] args) {
		f(10);
		f((Integer)10);
		
		Object obj = 10;
		f(obj);
		f((Integer)obj);
		f(((Integer)obj).intValue());
	}
	static void f(int data) {
		System.out.println("int 타입으로 호출한 메소드 : "+data);
	}
	static void f(Integer obj) {
		System.out.println("Integer 타입으로 호출한 메소드 : "+obj);
	}
//	Object 타입의 매개변수가 선언되어 있다
//	1. 객체 하나 넘겨라~
//	2. 아무 타입이나 넘겨라~
	static void f(Object obj) {
		System.out.println("Object 타입으로 호출한 메소드 : "+obj);
	}
}









