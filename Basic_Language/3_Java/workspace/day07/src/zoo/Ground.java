package zoo;

public class Ground {
	public static void main(String[] args) {
		BlueWhale blueWhale = new BlueWhale("윤도현", "남성", 51);
		Camel camel = new Camel("캐멀슬러그", "암컷", 26);
		GangSa gangSa = new GangSa("정다솔", "남성", 22);

//		Animal obj = new Animal() {
//			
//			@Override
//			void makeSomeNoise() {
//				// TODO Auto-generated method stub
//				
//			}
//		};
		
		while(true) {
			blueWhale.makeSomeNoise();
			camel.makeSomeNoise();
			gangSa.makeSomeNoise();
			
			//프로그램 진행 1초 멈추기
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
