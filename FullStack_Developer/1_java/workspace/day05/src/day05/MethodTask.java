package day05;

public class MethodTask {
	public static void main(String[] args) {
		MethodTask v = new MethodTask();
		System.out.println(v.changeCase("hEllo~123"));
		System.out.println(v.changeToHangle(1024));
	}
	//	문자열의 소문자는 대문자로, 대문자는 소문자로 바꿔주는 메소드(hEllo~123 -> HeLLO~123)
	String changeCase(String msg) {
		String result = "";
		for (int i = 0; i < msg.length(); i++) {
			char ch = msg.charAt(i);
			if(ch >= 'a' && ch <= 'z') {
				result += (char)(ch-32);
			}
			else if(ch >= 'A' && ch <= 'Z') {
				result += (char)(ch+32);
			}
			else {
				result += ch;
			}
		}
		return result;
	}
	//	정수를 한글로 바꿔주는 메소드 (1024 -> 일공이사)
	String changeToHangle(int num) {
		//num : 1024
		String hangle = "공일이삼사오육칠팔구";
		//hangle.charAt(0) : '공' / hangle.charAt(6) : '육'
		String result = "";
		String data = num+"";	// "1024"
		
		for (int i = 0; i < data.length(); i++) {
			char ch = data.charAt(i); // '1' '0' '2' '4'
//			hangle.charAt('a') --> hangle.charAt(97)
//			hangle.charAt('1') --> hangle.charAt(49)
			//(int)'1' --> 49
			//'1'(49) --> 1
			int idx = ch - 48;	//1 0 2 4
			result += hangle.charAt(idx);//'일' '공' '이' '사'
		}
		
		return result;
	}
}












