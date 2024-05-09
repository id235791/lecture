package exception;

public class ExceptionTest {
	public static void main(String[] args) {
		try {
			System.out.println(10/3);
//			System.err.println("GH");
			System.out.println(10/0);
			System.out.println(10/2);
		}
		catch(ArithmeticException ae) {
			System.out.println("0으로는 나눌 수 없습니다!");
		}
		finally {
//			System.out.println("프로그램 끝!");
		}
		
		System.out.println("프로그램 끝!");
	}
}






