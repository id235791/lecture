package zoo;

public class Cat extends Animal{

	public Cat(String name, String gender, int age) {
		super(name, gender, age);
	}
	
	@Override
	void makeSomeNoise() {
		System.out.println(name+"이(가) 냐옹 우는중...");
	}
	
}
