package multi_inh;

public interface B {
	default void f() {
		System.out.println("B 인터페이스의 f()");
	}
}
