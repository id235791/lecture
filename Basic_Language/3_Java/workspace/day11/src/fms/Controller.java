package fms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Controller {
	public final static String DATABASE = "db.txt";
	
	void insert(Model model) throws Exception {
		FileWriter fw = new FileWriter(DATABASE,true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(model.data+"\r\n");
		
		bw.close();
	}
	
	ArrayList<Model> selectAll() throws Exception {
		FileReader fr = new FileReader(DATABASE);
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<Model> result = new ArrayList<Model>();
		
		while(true) {
			String data = br.readLine();
			if(data == null) { break; }
			Model model = new Model();
			model.data = data;
			
			result.add(model);
		}
		
		return result;
	}

	void delete(String data) throws Exception {
		FileReader fr = new FileReader(DATABASE);
		BufferedReader br = new BufferedReader(fr);
		
		String temp = "";
		while(true) {
			String line = br.readLine();
			if(line == null) { break; }
			
			if(!line.equals(data)) {
				temp+=line+"\r\n";
			}
		}
		
		
		FileWriter fw = new FileWriter(DATABASE);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(temp);
		bw.close();
	}

	void update(String oldData, String newData) throws Exception {
		FileReader fr = new FileReader(DATABASE);
		BufferedReader br = new BufferedReader(fr);
		
		String temp = "";
		while(true) {
			String line = br.readLine();
			if(line == null) { break; }
			
			if(!line.equals(oldData)) {
				temp+=line+"\r\n";
			}
			else {
				temp+=newData+"\r\n";
			}
		}
		
		FileWriter fw = new FileWriter(DATABASE);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(temp);
		bw.close();
	}
}









