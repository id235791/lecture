package generic;

public class GMethodTest {
	<T> void f(T data) {
		if(data instanceof Integer) {
			System.out.println("정수로 호출");
		}
		else if(data instanceof String) {
			System.out.println("문자열로 호출");
		}
		else if(data instanceof Boolean) {
			System.out.println("논리형으로 호출");
		}
	}
}
