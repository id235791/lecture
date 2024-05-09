package pattern.single;

public class Company {
	private static Bridge bridge;

	public static Bridge getBridge() {
		if(bridge == null) {
			bridge = new Bridge();
		}
		return bridge;
	}
}
