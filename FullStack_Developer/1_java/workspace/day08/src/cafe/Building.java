package cafe;

public class Building {
	public static void main(String[] args) {
//		int data = 10;
//		f(data);
//		
//		f(10);
		
		Starbucks yeoksam = new Starbucks();
//		Cafe c = new Cafe() {
//			@Override
//			public void sell() {
//				System.out.println("손님에게 덤태기 씌우기");
//			}
//			
//			@Override
//			public void drip() {
//				System.out.println("그래도 최선을 다해 커피 내리기");
//			}
//			
//			@Override
//			public void clean() {
//				System.out.println("대충 눈에 보이는 곳만 닦기");
//			}
//		};
//		yeoksam.openCafe(c);
		yeoksam.openCafe(new Cafe() {
			@Override
			public void sell() {
				System.out.println("손님에게 덤태기 씌우기");
			}
			
			@Override
			public void drip() {
				System.out.println("그래도 최선을 다해 커피 내리기");
			}
			
			@Override
			public void clean() {
				System.out.println("대충 눈에 보이는 곳만 닦기");
			}
		});
	}
}









