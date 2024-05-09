package obj;

public class TestData {
	int intData;
	double doubleData;
	
	public TestData(int intData, double doubleData) {
		this.intData = intData;
		this.doubleData = doubleData;
	}
	
	@Override
	public String toString() {
		//TestData(intData=10, doubleData=0.0)
		return String.format("TestData(intData=%d, doubleData=%f)",
				intData, doubleData);
		
	}
	
	@Override
	public int hashCode() {
		return (int)doubleData*3+intData;
	}
	
	@Override
	public boolean equals(Object obj) {
//		obj 매개변수는 Object 타입으로 밖에서(사용할 때) 넘겨준 객체들을 업 캐스팅 해서 받는다.
//		따라서 내부 필드를 서로 비교하려면 업 캐스팅 되며 접근이 제한되었던 필드들을 다시 사용 가능하도록
//		다운 캐스팅을 해준 후 그 객체로 서로 비교해야 한다. 다운 캐스팅은 TestData 타입의 객체가
//		업 캐스팅 되었을 때만 가능하다. 따라서 instanceof 로 타입 비교부터 먼저 해준다.
		
		if(obj == null) { return false; }
		//1. 타입 비교
		if(obj instanceof TestData) {
			//2. 다운 캐스팅
			TestData target = (TestData)obj;
			
			//3. 조건 비교
			if(this.intData == target.intData) {
				if(this.doubleData == target.doubleData) {
					return true;
				}
			}
		}
		return false;
	}
}










