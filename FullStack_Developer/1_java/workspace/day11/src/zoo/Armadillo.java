package zoo;

public class Armadillo extends Animal implements Runnable,Mammal {
	@Override
	void makeSomeNoise() {
		for (int i = 0; i < 10; i++) {
			System.out.println("구른다");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	@Override
	public void run() {
		makeSomeNoise();
	}
}
