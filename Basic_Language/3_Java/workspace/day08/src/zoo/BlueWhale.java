package zoo;

public class BlueWhale extends Animal implements Mammal{

	public BlueWhale(String name, String gender, int age) {
		super(name, gender, age);
	}

	@Override
	void makeSomeNoise() {
		System.out.println("너 가는 길이~~ 너무 지치고~~ 힘들 때~~~");
	}
	
	@Override
	public void breed() {
		System.out.println("물 속에서 새끼 낳기");
	}
	
}
