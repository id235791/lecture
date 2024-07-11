package fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriterTest {
	public static void main(String[] args) throws Exception {
		//D드라이브에 ttt.txt 데이터 쓸거니까 공간 확보해줘!
		//절대경로
//		FileWriter fw = new FileWriter("D:\\ttt.txt");
		
		//상대경로(현재 프로젝트 최상위 경로)
//		FileWriter fw = new FileWriter("test.txt");
		
//		없는 폴더(a)를 만들어 내지는 못한다.
//		파일명 앞에 실제로 존재하는 폴더명을 썼을 경우에는 "현재프로젝트경로/폴더" 안에 해당 파일을 생성한다.
//		FileWriter fw = new FileWriter("a/test.txt");
		
		//1. 덮어쓰기 모드
//		FileWriter fw = new FileWriter("test.txt");
		
		//2. 추가하기 모드
		FileWriter fw = new FileWriter("test.txt", true);
		System.out.println("파일 준비 완료!");
		
//		fw.write(97);
//		fw.flush();
//		fw.close();
		
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println("버퍼 비스무리한 애 준비 완료");
		bw.write("Hello\r\n");
		bw.write("Java!\r\n");
//		bw.flush();
		bw.close();
	}
}













