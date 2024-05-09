package pattern.adapter;

public class Sergeant extends SoldierAdapter{
	@Override
	public void eat() {
		System.out.println("메뉴를 물어보고 누워있는다.");
	}
	
	@Override
	public void sleep() {
		System.out.println("밥 시간 빼고 항상 누워있는다.");
	}
}
