package fileio;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriterTest {
	public static void main(String[] args) throws Exception {
//		절대경로
//		FileWriter fw = new FileWriter("D:\\ttt.txt");
		
//		상대경로(현재 프로젝트 경로)
//		FileWriter fw = new FileWriter("test.txt");
		
//		없는 폴더(a)를 만들어 내지는 못한다.
//		파일명 앞에 실제로 존재하는 폴더명을 썼을 경우에는 "현재프로젝트경로/폴더" 안에 그 파일을 생성한다.
//		FileWriter fw = new FileWriter("a/test.txt");
		
		//1. 덮어쓰기 모드
//		FileWriter fw = new FileWriter("test.txt");
		
		//2. 이어쓰기 모드
//		FileWriter fw = new FileWriter("test.txt",true);
		FileWriter fw = new FileWriter("C:\\a.txt");

		System.out.println("파일 준비 완료!");
//		fw.write(97);
//		fw.close();
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println("버퍼 준비 완료");
		bw.write("Hello!\r\n");
		bw.write("Java!\r\n");
		bw.close();
		System.out.println("파일 작성 완료!");
	}
}









