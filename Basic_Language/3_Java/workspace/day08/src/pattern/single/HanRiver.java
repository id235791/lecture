package pattern.single;

public class HanRiver {
	public static void main(String[] args) {
		Bridge bridge1 = new Bridge();
		Bridge bridge2 = new Bridge();
		
		System.out.println(bridge1);
		System.out.println(bridge2);
		
		Bridge bridge3 = Company.getBridge();
		Bridge bridge4 = Company.getBridge();
		
		System.out.println(bridge3);
		System.out.println(bridge4);
	}
}








