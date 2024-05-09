package day04;

public class StrTest {
	public static void main(String[] args) {
		String msg = "Hello Java!";
		
//		"문자열1".split("문자열2") : "문자열1"을 "문자열2" 기준으로 나누기
//		*(나뉘어진 값들이 담겨있는 String[] 반환)
//		msg.split(" ") : {"Hello","Java!"}
		String[] arr1 = msg.split(" ");//{"Hello","Java!"}
		System.out.println("띄어쓰기 앞 부분 : "+arr1[0]);
		System.out.println("띄어쓰기 뒷 부분 : "+arr1[1]);
//		split()은 나누지 못했다면 전체 문자열 한 개가 담겨있는 한 칸짜리 String[] 반환
		String[] arr2 = msg.split("u");//{"Hello Java!"}
		System.out.println(arr2[0]);
		
//		"문자열1".indexOf("문자열2") : "문자열1" 에서 "문자열2" 가 시작하는 인덱스 반환
		int idx = msg.indexOf("J");//6
		System.out.println("J의 인덱스 : "+idx);
		idx = msg.indexOf("Java");//6
		System.out.println("Java의 시작 인덱스 : "+idx);
		idx = msg.indexOf("☆");//-1
		System.out.println("☆의 인덱스 : "+idx);
		
//		"문자열".charAt(n) : "문자열" 에서 n번째 인덱스 위치의 문자를 반환
		char ch = msg.charAt(6);//'J'
		System.out.println("6번째 글자 : "+ch);
//		ch = msg.charAt(10000);
//		System.out.println("10000번째 글자 : "+ch);
		
//		"문자열1".replace("문자열2", "문자열3") : "문자열1" 에서 "문자열2" 를 찾아 "문자열3" 으로 변환
//		원본("문자열1")은 그대로 유지, 적용된 문자열을 반환
		String newMsg = msg.replace("Hello", "Bye");
		System.out.println("원본 : "+msg);
		System.out.println("적용된 값 : "+newMsg);
		
//		"문자열".substring(n) : "문자열" 을 n부터 끝까지 잘라서 돌려주기
		String subMsg1 = msg.substring(6);
		System.out.println("6번째부터 끝까지 : "+subMsg1);
		
//		"문자열".substring(n,m) : "문자열" 을 n 부터 m 이전까지 잘라서 돌려주기
		String subMsg2 = msg.substring(6,8);
		System.out.println("6번째부터 8 이전까지 : "+subMsg2);
		
//		"문자열".trim() : "문자열" 양 옆에 있는 공백들을 제거해서 돌려주기
		System.out.println("            Java            ".trim());
		
//		"문자열".length() : "문자열" 의 길이를 반환
		int len = msg.length();
		System.out.println("msg의 길이 : "+len);
		
//		실제 원본을 바꾸는 방법
		msg = msg.substring(6);
		System.out.println(msg);
	}
}











