package multi_inh;

//여러 인터페이스를 상속받을 때에는 , 로 구분하여 작성해준다.
public class D extends C implements A,B {
	
	//다중상속을 할 때 f() 메소드가 서로 충돌을 하게 되고(메소드의 모호성)
	//모호성을 해결하기 위해 직접적으로 재정의를 통해 해결을 해야한다.
	//다만, C 클래스를 상속받은 경우에는 C 클래스의 f()가
	//내용이 완벽하게 완성되어 있는 메소드이므로 모호성이 자동으로 해결된다.
	//(C 클래스의 f()가 우선시된다.)
	@Override
	public void f() {
		System.out.println("D 클래스의 f()");
	}
	
	void doSomething() {
		//인터페이스의 default 메소드를 이용하여 마치 다중상속처럼 사용
		A.super.f();
		B.super.f();
		super.f();
	}
}
