package road;

public class Road {
	public static void main(String[] args) {
//		익명 클래스는 앞에 호출하고 있는 생성자의 클래스를 상속받고 있는
//		이름 없는 자식 클래스이다. 하나의 객체만을 위한 일회용 클래스이다.
//		즉, 그 앞에 호출하는 생성자를 통해 만드는 객체 딱 하나만을 위한 클래스이다.
//		그 때 만들어지는 객체는 익명 클래스 안에서 구현한 메소드의 내용을 가지게 된다.
//		이렇게 만들어지는 객체를 익명구현객체 라고 한다.
		Car tank = new Car("M1","Green",2000000000) {
			int bomb = 40;
			
			@Override
			void engineStop() {
				System.out.println(model + " 열쇠로 시동 끄기");
			}
			
			@Override
			void engineStart() {
				System.out.println(model + " 열쇠로 시동 켜기");
				shoot();
			}
			
//			새로운 필드를 구현하게 되면 자식 클래스에서 내용을 추가하는 것이나 마찬가지이고
//			만들어지는 객체는 해당 익명클래스 타입의 객체가 업캐스팅 된 상태나 마찬가지이다.
//			따라서 추가된 내용들을 사용하려면 만들어진 객체를 이 익명클래스 타입으로
//			다운캐스팅을 해야만 사용 가능하다. 하지만 익명클래스는 일회용으로 이름이 존재하지
//			않기 때문에 다운캐스팅이 불가능하다. 그러므로 익명클래스에서 추가된 내용은
//			이 영역 내부에서만 사용 가능하다. (외부에서 만들어진 객체에 . 을 찍고 접근이 불가능하다)
			void shoot() {
				System.out.println("강력한 포탄 쏘기!");
				bomb--;
			}
		};
		
		tank.engineStart();
//		tank.shoot();
		
		
	}
}




