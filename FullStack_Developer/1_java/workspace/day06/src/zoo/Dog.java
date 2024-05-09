package zoo;

public class Dog extends Animal{
	public Dog(String name, String gender, int age) {
		super(name, gender, age);
	}
	
	@Override
	void makeSomeNoise() {
		System.out.println(name+"이(가) 멍멍 짖는중...");
	}
	
}
