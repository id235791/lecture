package multi;

//여러 인터페이스를 상속받을 때에는 , 로 구분하여 작성해준다.
public class D extends C implements A,B{
	@Override
	public void f() {
		System.out.println("D 클래스의 f()");
	}
	void doSomething() {
		//인터페이스의 default 메소드를 이용하면 마치 다중상속처럼 사용 가능하다.
		//다중상속을 위해서는 위에 있는 f() 메소드 선언처럼 서로 충돌하는 메소드의 모호성을
		//해결하는 것이 필수이다.
		A.super.f();
		B.super.f();
		super.f();
	}
}
