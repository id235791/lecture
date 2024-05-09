package zoo;

public class BlueWhale extends Animal{

	public BlueWhale(String name, String gender, int age) {
		super(name, gender, age);
	}

	@Override
	void makeSomeNoise() {
		System.out.println("너 가는 길이~~ 너무 지치고~~ 힘들 때~~~");
	}
	
}
