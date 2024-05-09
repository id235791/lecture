package zoo;

public class Ground {
	public static void main(String[] args) {
		Animal[] cage = {
				new UpaRupa(),
				new Ocapi(),
				new Armadillo()
		};
		
		Thread[] t = {
				new Thread((Runnable)cage[0]),
				new Thread((Runnable)cage[1]),
				new Thread((Runnable)cage[2])
		};
		
		//포유류만 동시에 울게 하기
		for (int i = 0; i < t.length; i++) {
			if(cage[i] instanceof Mammal) {
				t[i].start();
			}
		}
	}
}






