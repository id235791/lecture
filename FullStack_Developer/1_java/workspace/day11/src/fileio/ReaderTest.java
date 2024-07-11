package fileio;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReaderTest {
	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("test.txt");
		System.out.println("파일 준비 완료!");
		BufferedReader br = new BufferedReader(fr);
		System.out.println("버퍼 비스무리한 애 준비 완료");
		
//		String data1 = br.readLine();
//		System.out.println(data1);
//		String data2 = br.readLine();
//		System.out.println(data2);
		
		while(true) {
			String line = br.readLine();
			if(line == null) {
				break;
			}
			System.out.println(line);
		}
		br.close();
	}
}














