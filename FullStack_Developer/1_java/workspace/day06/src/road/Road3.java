package road;

public class Road3 {
	public static void main(String[] args) {
		SuperCar mycar1 = new SuperCar("Ferrari", "Red", 70000);
		SuperCar mycar2 = new SuperCar("Porsche", "Yellow", 35000, "Turbo");
		Car momcar = new Car("K7","White",4000);
		
//		mycar1.engineStart();
//		mycar1.roofOpen();
//		momcar.engineStart();
		
		Car[] garage = { mycar1, mycar2, momcar };
		Car temp = new SuperCar();
		
//		temp.engineStart();
		for (int i = 0; i < garage.length; i++) {
			garage[i].engineStart();
		}
		
		for (int i = 0; i < garage.length; i++) {
			//0번방, 1번방 에는 업캐스팅 된 객체가 있기 때문에 다운캐스팅 가능
			//2번방 에는 Car타입의 일반적인 객체가 담겨있어서 다운캐스팅 불가능(오류발생)
			SuperCar s = (SuperCar)garage[i]; 
//			((SuperCar)garage[i]).roofOpen();
//			((SuperCar)garage[i]).roofClose();
			s.roofOpen();
			s.roofClose();
		}
		
		
	}
}




