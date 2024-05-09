package pattern.adapter;

public class Private implements Soldier{

	public void eat() {
		System.out.println("허리를 펴고 각을 잡은 채 90도로 팔을 들고 식사를 한다.");
	}
	public void sleep() {
		System.out.println("엄마 생각하다 잠에 들지만, 야밤에 누가 깨워서 데리고 나간다.");
	}
	public void work() {
		System.out.println("눈에 나지 않도록 온 힘을 다해 일을 하지만 효율이 없다.");
	}
	public void hello() {
		System.out.println("추우우우우웅 서어어어어엉!!!");
	}
	
}
