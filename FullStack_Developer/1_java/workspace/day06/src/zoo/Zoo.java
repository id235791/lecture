package zoo;

public class Zoo {
	public static void main(String[] args) {
		Animal[] cage = {
			new Dog("뽀삐", "암컷", 5),
			new Cat("키티", "수컷", 4),
			new Eagle("류현진", "남성", 36)
		};
		
//		int ch = 'A';
//		System.out.println((char)ch);
		
		for (int i = 0; i < cage.length; i++) {
			cage[i].makeSomeNoise();
//			if(cage[i]이 Eagle의 업캐스팅 객체이다) {
			if(cage[i] instanceof Eagle) {
				((Eagle)cage[i]).fly();
			}
		}
	}
}




