package fileio;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReaderTest {
	public static void main(String[] args) throws Exception{
		FileReader fr = new FileReader("test.txt");
		System.out.println("파일 준비 완료!");
		
		BufferedReader br = new BufferedReader(fr);
		System.out.println("버퍼 준비 완료!");
		
		while(true) {
			String line = br.readLine();
			if(line == null) { break; }
			System.out.println(line);
		}
		br.close();
	}
}










