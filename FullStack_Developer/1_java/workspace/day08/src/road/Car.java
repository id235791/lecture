package road;

public abstract class Car {
	String model;
	String color;
	int price;
	
	public Car(String model, String color, int price) {
		this.model = model;
		this.color = color;
		this.price = price;
	}
	
	abstract void engineStart();
	abstract void engineStop();
}
