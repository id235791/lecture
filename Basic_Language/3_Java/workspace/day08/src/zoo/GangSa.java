package zoo;

public class GangSa extends Animal implements Mammal{

	public GangSa(String name, String gender, int age) {
		super(name, gender, age);
	}
	
	@Override
	void makeSomeNoise() {
		System.out.println("여기까지 이해 되셨죠?");
	}

	@Override
	public void breed() {
		System.out.println("학원에서..?");
	}
}




