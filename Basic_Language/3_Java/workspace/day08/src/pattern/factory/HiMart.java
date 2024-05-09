package pattern.factory;

public class HiMart {
	public static void main(String[] args) {
//		TV tv = new TV();
		TV tv = (TV)Factory.getInstance("TV");
		Computer computer = (Computer)Factory.getInstance("Computer");
		Speaker speaker = (Speaker)Factory.getInstance("Speaker");
		
		System.out.println(tv);
		System.out.println(computer);
		System.out.println(speaker);
		
		Factory.getInstance("Computer");
		
//		TV[] arTv1 = {
//				new TV(),
//				new TV(),
//				new TV(),
//				new TV(),
//				new TV(),
//				new TV(),
//				new TV(),
//				new TV(),
//				new TV()
//		};
		
		TV[] arTv2 = {
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV"),
				(TV)Factory.getInstance("TV")
		};
	}
}
