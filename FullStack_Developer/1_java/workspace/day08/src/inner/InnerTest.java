package inner;

import inner.Out.In;

public class InnerTest {
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		b.f();
		
		Out out = new Out();
//		In in = new In();
//		Out.In in = out.new In();
//		in.f();
		
//		내부 클래스는 보통 직접 객체화를 진행하기 보다 내부 클래스 타입의 객체를
//		리턴해주는 메소드를 호출하여 내부에서 생성된 객체를 받아서 사용한다.
		In in = out.doSomething();
		in.f();
	}
}
class A{
	int data = 10;
}
class B extends A{
	void f() {
		System.out.println(data);
	}
}
class Out{
	int data = 20;
	
	In doSomething() {
		System.out.println("In 타입의 객체를 만들어 줄게요~");
		return new In();
	}
	
	class In{
		void f() {
			System.out.println(data);
		}
	}
}






