package zoo;

public abstract class Animal {
	String name;
	String gender;
	int age;
	
	public Animal(String name, String gender, int age) {
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	//final이 붙은 메소드는 자식에서 절대 재정의가 불가능하다.
	final void init() {
		System.out.println(name+"은(는) 동물입니다~");
	}
	
	void eat() {
		System.out.println(name+"이(가) 냠냠 먹는중...");
	}
	
	void sleep() {
		System.out.println(name+"이(가) 쿨쿨 자는중...");
	}
	
	abstract void makeSomeNoise();
}











