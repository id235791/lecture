package day04;

public class MethodTask {
	public static void main(String[] args) {
	}
	
//	1 ~ 10 출력하는 메소드
	void print1To10() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
		}
	}
	
//	1 ~ 10 합 구해주는 메소드
	int sum1To10() {
		int sum = 0;
		for (int i = 1; i <= 10; i++) {
			sum += i;
		}
		return sum;
	}
	
//	1 ~ n 합 구해주는 메소드
	int sum1ToN(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		return sum;
	}
	
//	두 정수의 나눗셈 결과를 구해주는 메소드
	double divide(int num1, int num2) {
		return (double)num1/num2;
	}
	
//	어떤 문자열을 n번만큼 출력하는 메소드
	void printMsg(String msg, int n) {
		for (int i = 0; i < n; i++) {
			System.out.println(msg);
		}
	}
	
//	n 부터 m 까지의 합 구해주는 메소드(n이 m보다 작다고 가정)
	int sumNToM(int n, int m) {
		int sum = 0;
		for (int i = n; i <= m; i++) {
			sum += i;
		}
		return sum;
	}
//	문자열을 전부 대문자로 바꿔주는 메소드(hEllo~123 -> HELLO~123)
	String changeToUpperCase(String msg) {
//		msg : "hEllo~123"
		int len = msg.length(); //9
		String result = "";
		for (int i = 0; i < len; i++) {
			char ch = msg.charAt(i); // 'h' 'E' 'l' 'l' 'o' ...
			if(ch >= 'a' && ch <= 'z') {
				//'h' -> 'H'		'l' -> 'L'
				//104 -> 72			108 -> 76
				result += (char)(ch-32);
			}
			else {
				result += ch;
			}
		}
		return result;
	}
	
//	문자열을 거꾸로 바꿔주는 메소드(Hello -> olleH)
	String reverse(String msg) {
		String result = "";
//		for (int i = 0; i < msg.length(); i++) {
//			result = msg.charAt(i) + result;
//		}
		
		for (int i = msg.length()-1; i >= 0; i--) {
			result += msg.charAt(i);
		}
		return result;
	}
//	문자열이 숫자로 이루어져 있는지 검사하는 메소드(숫자가 아닌것이 포함되어 있으면 false)
//	is~~~(has~~~) : ~~이면(~~~를 가지고 있다면) 참 / 아니라면 거짓을 리턴하는 메소드(return 타입이 boolean)
	boolean isDigit(String msg) {
		for (int i = 0; i < msg.length(); i++) {
			char ch = msg.charAt(i);
			if(ch<'0' || ch>'9') {
				return false;
			}
		}
		return true;
	}
	
//	문자열의 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드(hEllo~123 -> HeLLO~123)
//	정수를 한글로 바꿔주는 메소드 (1024 -> 일공이사)
	
}





