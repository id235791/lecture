package zoo;

public class UpaRupa extends Animal implements Runnable{
	@Override
	void makeSomeNoise() {
		for (int i = 0; i < 10; i++) {
			System.out.println("삑삑");
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
