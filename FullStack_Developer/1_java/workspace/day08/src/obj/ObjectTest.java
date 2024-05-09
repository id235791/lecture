package obj;

public class ObjectTest {
	public static void main(String[] args) {
		//동위 객체
		TestData obj1 = new TestData(10, 0.0);
		TestData obj2 = new TestData(10, 0.0);
		
		System.out.println(obj1.toString());
		
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
	}
}
