package zoo;

public class Eagle extends Animal{

	public Eagle(String name, String gender, int age) {
		super(name, gender, age);
	}
	
	void fly() {
		System.out.println("펄럭펄럭펄ㄹ거펄럭펄럭");
	}
	
	@Override
	void makeSomeNoise() {
		System.out.println(name+"이(가) 한화를 응원한다");
	}
	
}
