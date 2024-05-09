package pattern.factory;

public class Factory {
	public static Object getInstance(String name) {
		switch(name) {
		case "TV":
			return new TV("TV");
		case "Computer":
			return new Computer();
		case "Speaker":
			return new Speaker();
		}
		return null;
	}
}
