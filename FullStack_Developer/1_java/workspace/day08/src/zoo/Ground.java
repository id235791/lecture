package zoo;

public class Ground {
	public static void main(String[] args) {
		Animal[] cage = {
				new BlueWhale("윤도현", "남성", 51),
				new Camel("캐멀슬러그", "암컷", 26),
				new GangSa("정다솔", "남성", 22),
				new Sparrow("잭스패로우", "남성", 19)
		};
		
		for (int i = 0; i < cage.length; i++) {
			if(cage[i] instanceof Mammal) {
				cage[i].makeSomeNoise();
				((Mammal)cage[i]).breed();
			}
		}
	}
}








