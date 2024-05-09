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
}










