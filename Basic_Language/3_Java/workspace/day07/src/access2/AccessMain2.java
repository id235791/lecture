package access2;

//다른 패키지에 있는 클래스를 사용할 때는 소속을 밝혀주어야 한다.
//import를 해야한다.
import access1.AccessTest;

public class AccessMain2 {
	public static void main(String[] args) {
//		access1.AccessTest obj = new access1.AccessTest();
		AccessTest obj = new AccessTest();
//		System.out.println(obj.data1);
		System.out.println(obj.data2);
		System.out.println(obj.getData3());
//		System.out.println(obj.data4);
	}
}
