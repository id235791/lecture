package obj;

public class StrTest {
	public static void main(String[] args) {
		String msg1 = "Hello";
		String msg2 = "Hello";
		String msg3 = new String("Hello");
		
		System.out.println(msg1 == msg2);
		System.out.println(msg1 == msg3);
	}
}
