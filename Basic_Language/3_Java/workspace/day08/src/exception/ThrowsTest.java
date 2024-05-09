package exception;

import java.io.FileReader;

public class ThrowsTest {
	public static void main(String[] args) throws Exception {
		f();
	}
	static void f() throws Exception {
		Thread.sleep(1000);
		new FileReader("");
	}
}
