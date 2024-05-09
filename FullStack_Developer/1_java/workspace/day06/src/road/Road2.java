package road;

public class Road2 {
	public static void main(String[] args) {
		Car mycar = new Car("Ferrari","Red",70000);
		Car momcar = new Car("Sportage","Gray",5000);
		
		momcar.engineStart();
		
		momcar.wheel--;
		momcar.price-=1000;
		
		System.out.println(momcar.wheel);
		System.out.println(momcar.price);
		
		System.out.println(mycar.wheel);
		System.out.println(mycar.price);
		
		System.out.println(Car.wheel);
		
	}
}







