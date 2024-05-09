package road;

public class Road1 {
	public static void main(String[] args) {
		int data0 = 10;
		int data1 = 5;
		int data2 = 8;
		
		int[] arData = {10, 5, 8};
		
//		System.out.println(data0);
//		System.out.println(data1);
//		System.out.println(data2);
		
		
		Car mycar = new Car("Ferrari","Red",70000);
		Car momcar = new Car("K7", "White", 4000);
		Car dadcar = new Car(26000);
		
//		mycar.engineStart();
//		momcar.engineStart();
//		dadcar.engineStart();
		
//		Car[] garage = {mycar, momcar, dadcar};
		
		//선언 방법 1
		Car[] garage = {
				new Car("Ferrari","Red",70000),
				new Car("K7", "White", 4000),
				new Car(26000)
		};
		for (int i = 0; i < garage.length; i++) {
			garage[i].engineStart();
		}
		
		//선언 방법 2
		Car[] cars = new Car[10];
//		비어있는 배열(칸 수 지정 배열)은 각 방이 객체인데 해당 객체의 필드가 생성되지 않았으므로
//		(생성자를 호출하지 않았으므로) 각 방에는 주소값의 초기값인 null이 담겨있다.
//		for (int i = 0; i < cars.length; i++) {
//			cars[i].engineStart();
//		}
		
//		사용하려면 필드를 먼저 생성해서 주소값을 연결해준 후 사용한다.
		cars[0] = new Car();
		cars[0].engineStart();
	}
}





