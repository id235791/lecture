package pattern.adapter;

public class Corporal implements Soldier{

	public void eat() {
		System.out.println("맛있는 반찬만 먹고 PX에 가서 더 맛있는걸로 배를 채운다.");
	}
	public void sleep() {
		System.out.println("제 집마냥 편안하게 자다 심심하면 이등병을 깨워서 데리고 나간다.");
	}
	public void work() {
		System.out.println("손가락만 움직이는것 같지만 능숙하게 일을 다 마무리한다.");
	}
	public void hello() {
		System.out.println("충! 성!");
	}
	
}




