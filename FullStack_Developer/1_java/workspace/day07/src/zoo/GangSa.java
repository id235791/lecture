package zoo;

public class GangSa extends Animal{

	public GangSa(String name, String gender, int age) {
		super(name, gender, age);
	}
	
	@Override
	void makeSomeNoise() {
		System.out.println("여기까지 이해 되셨죠?");
	}

}
