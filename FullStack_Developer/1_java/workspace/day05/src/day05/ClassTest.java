package day05;

public class ClassTest {
	public static void main(String[] args) {
//		Car mycar = new Car();
////		System.out.println(mycar);
//		mycar.model = "Ferrari";
//		mycar.color = "Red";
//		mycar.price = 70000;
//		System.out.println(mycar.model);
		
		Car mycar = new Car("Ferrari","Red",70000);
		System.out.println(mycar.model);
		Car momcar = new Car("K7", "White", 4000);
		System.out.println(momcar.model);
		Car dadcar = new Car("Genesis G80", "Black", 8000);
		
		mycar.engineStart();
		momcar.engineStart();
		dadcar.engineStart();
	}
}
class Car{
	//속성, 성질들은 변수로 선언
	String carnum;
	String model;
	String color;
	int price;
	
	Car(String model, String color, int price){
		this.model = model;
		this.color = color;
		this.price = price;
	}
	
	//기능, 행위들은 메소드로 선언
	void engineStart() {
		System.out.println(this.model+" 시동 켜기");
	}
	
	void engineStop() {
		//전역변수 model이 선언된 것이 없으므로 this.은 생략 가능
		System.out.println(model+" 시동 끄기");
	}
}











