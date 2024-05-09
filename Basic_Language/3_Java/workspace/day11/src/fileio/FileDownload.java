package fileio;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileDownload {
	public static void main(String[] args) throws Exception{
		String path = "https://dimg.donga.com/wps/NEWS/IMAGE/2023/08/09/120625983.2.jpg";
		
		URL url = new URL(path);
		System.out.println("URL 오픈");
		InputStream is = url.openStream();
		System.out.println("InputStream 오픈");
		
		OutputStream os = new FileOutputStream("copy.jpg");
		System.out.println("OutputStream 오픈");
		
		while(true) {
			int data = is.read();
			if(data == -1) {
				break;
			}
			os.write(data);
		}
		
		is.close();
		os.close();
	}
}







