package zoo;

public class Sparrow extends Animal{

	public Sparrow(String name, String gender, int age) {
		super(name, gender, age);
	}
	
	@Override
	void makeSomeNoise() {
		System.out.println("참참참");
	}
	
}
