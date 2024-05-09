package obj;

public class MathTest {
	public static void main(String[] args) {
//		Math.ceil(실수) : 소수점 첫째자리에서 올림
		System.out.println(Math.ceil(19.11));
		System.out.println(Math.ceil(-19.11));
		
//		Math.floor(실수) : 소수점 첫째자리에서 내림
		System.out.println(Math.floor(19.11));
		System.out.println(Math.floor(-19.11));
		
//		Math.round(실수) : 소수점 첫째자리에서 반올림
		System.out.println(Math.round(19.499999999));
		System.out.println(Math.round(19.5));
		
//		특정한 자리에서 하고 싶은 경우
//		11.484 --> 11.5
		
//		1. 10의 (반올림 할 자리수-1) 제곱 을 곱해준다.
//			11.484 * 10^1 --> 114.84
//		2. 반올림 진행
//			Math.round(114.84) --> 115.0
//		3. 곱했던 수 나누기
//			115.0 / 10^1 --> 11.5
	}
}





