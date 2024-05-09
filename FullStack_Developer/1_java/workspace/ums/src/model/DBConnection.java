package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class DBConnection {
	private String file;
	String[] colname;
	
	/**
	 * @param file : 연결하고자 하는 테이블 텍스트 파일의 경로
	 */
	public DBConnection(String file) {
		this.file = file;
		try {
			BufferedReader br =  new BufferedReader(new FileReader(file));
			colname = br.readLine().split("\t");
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}
	}
	
	/**
	 * @param 연결되어 있는 테이블 텍스트 파일에 추가하고자 하는 문자열 타입의 데이터
	 * @return 추가 성공시 true / 실패시 false
	 */
	public boolean insert(String data) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
			bw.write(data+"\r\n");
			bw.close();
			return true;
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return false;
	}
	/**
	 * @param key : 수정하고자 하는 행을 찾기위한 key, 수정하고자 하는 행의 고유한 값(PK)
	 * @param col : 수정하고자 하는 열의 번호
	 * @param newData : 수정을 위한 새로운 데이터
	 * @return 수정 성공시 true / 실패시 false
	 */
	public boolean update(String key,int col,String newData) {
		String result = "";
		boolean check = false;
		try {
			BufferedReader br =  new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line ==null) {
					break;
				}
				String[] datas=line.split("\t");
				if(datas[0].equals(key)) {
					result+=datas[0];
					for (int i = 1; i < datas.length; i++) {
						if(i==col) {
							result+="\t"+newData;
							check = true;
						}else {
							result+="\t"+datas[i];
						}
					}
					result+="\r\n";
				}else {
					result+=line+"\r\n";
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		if(check) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				System.out.println("======오류 발생 : DB 연결 실패======");
				System.out.println(e);
				System.out.println("===============================");
			}
		}
		return check;
	}
	/**
	 * @param key : 수정하고자 하는 행을 찾기위한 key, 수정하고자 하는 행의 고유한 값(PK)
	 * @param col : 수정하고자 하는 열의 이름
	 * @param newData : 수정을 위한 새로운 데이터
	 * @return 수정 성공시 true / 실패시 false
	 */
	public boolean update(String key,String col,String newData) {
		String result = "";
		int col_num = 0;
		boolean check = false;
		try {
			BufferedReader br =  new BufferedReader(new FileReader(file));
			br.readLine();
			for (int i = 0; i < colname.length; i++) {
				if(colname[i].equals(col)) {
					col_num = i;
				}
			}
			while(true) {
				String line = br.readLine();
				if(line ==null) {
					break;
				}
				String[] datas=line.split("\t");
				if(datas[0].equals(key)) {
					result+=datas[0];
					for (int i = 1; i < datas.length; i++) {
						if(i==col_num) {
							result+="\t"+newData;
							check = true;
						}else {
							result+="\t"+datas[i];
						}
					}
					result+="\r\n";
				}else {
					result+=line+"\r\n";
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		if(check) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				System.out.println("======오류 발생 : DB 연결 실패======");
				System.out.println(e);
				System.out.println("===============================");
			}
		}
		return check;
	}
	/**
	 * @param key : 삭제하고자 하는 행을 찾기위한 key, 삭제하고자 하는 행의 고유한 값(PK)
	 */
	public boolean delete(String key) {
		String result = "";
		boolean check = false;
		try {
			BufferedReader br =  new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line ==null) {
					break;
				}
				String[] datas=line.split("\t");
				if(datas[0].equals(key)) {
					check = true;				
				}else {
					result+=line+"\r\n";
				}
			}
			
		}catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		if(check) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				System.out.println("======오류 발생 : DB 연결 실패======");
				System.out.println(e);
				System.out.println("===============================");
			}
		}
		return check;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 모든 데이터를 검색합니다.
	 * 각 행, 각 열에 있는 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select() {
		ArrayList<String[]> resultSet = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				ArrayList<String> temp = new ArrayList<String>();
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				for (int i = 0; i < datas.length; i++) {
					temp.add(datas[i]);
				}
				String[] temp3 = new String[temp.size()];
				for (int i = 0; i < temp3.length; i++) {
					temp3[i] = temp.get(i);
				}
				resultSet.add(temp3);
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 모든 데이터를 검색합니다.
	 * 각 행, 각 열에 있는 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 col번 열 기준으로 오름차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색된 결과의 정렬 기준 / 열 번호
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				for (int i = 0; i < datas.length; i++) {
					temp.add(datas[i]);
				}
				String[] temp3 = new String[temp.size()];
				for (int i = 0; i < temp3.length; i++) {
					temp3[i] = temp.get(i);
				}
				resultSet.add(temp3);
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				if(isDigit(resultSet.get(0)[col])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(Integer.parseInt(resultSet.get(i)[col]) > Integer.parseInt(resultSet.get(j)[col])) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(resultSet.get(i)[col].charAt(0) > resultSet.get(j)[col].charAt(0)) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 모든 데이터를 검색합니다.
	 * 각 행, 각 열에 있는 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 col번 열 기준으로 오름차순 혹은 내림차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색된 결과의 정렬 기준 / 열 번호
	 * @param asc : true(오름차순) / false(내림차순)
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,boolean asc) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		asc = true;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				for (int i = 0; i < datas.length; i++) {
					temp.add(datas[i]);
				}
				String[] temp3 = new String[temp.size()];
				for (int i = 0; i < temp3.length; i++) {
					temp3[i] = temp.get(i);
				}
				resultSet.add(temp3);
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				if(isDigit(resultSet.get(0)[col])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(Integer.parseInt(resultSet.get(i)[col]) > Integer.parseInt(resultSet.get(j)[col])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(Integer.parseInt(resultSet.get(i)[col]) < Integer.parseInt(resultSet.get(j)[col])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(resultSet.get(i)[col].charAt(0) > resultSet.get(j)[col].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else{
								if(resultSet.get(i)[col].charAt(0) < resultSet.get(j)[col].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 모든 데이터를 검색합니다.
	 * 각 행, 각 열에 있는 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 col 이름의 열 기준으로 오름차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색된 결과의 정렬 기준 / 열 이름
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(String col) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				for (int i = 0; i < datas.length; i++) {
					temp.add(datas[i]);
				}
				String[] temp3 = new String[temp.size()];
				for (int i = 0; i < temp3.length; i++) {
					temp3[i] = temp.get(i);
				}
				resultSet.add(temp3);
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				int colnum = 0;
				for (int i = 0; i < colname.length; i++) {
					if(colname[i].equals(col)) {
						colnum = i;
					}
				}
				if(isDigit(resultSet.get(0)[colnum])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(Integer.parseInt(resultSet.get(i)[colnum]) > Integer.parseInt(resultSet.get(j)[colnum])) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(resultSet.get(i)[colnum].charAt(0) > resultSet.get(j)[colnum].charAt(0)) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 모든 데이터를 검색합니다.
	 * 각 행, 각 열에 있는 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 col 이름의 열 기준으로 오름차순 혹은 내림차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색된 결과의 정렬 기준 / 열 이름
	 * @param asc : true(오름차순) / false(내림차순)
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(String col, boolean asc) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				for (int i = 0; i < datas.length; i++) {
					temp.add(datas[i]);
				}
				String[] temp3 = new String[temp.size()];
				for (int i = 0; i < temp3.length; i++) {
					temp3[i] = temp.get(i);
				}
				resultSet.add(temp3);
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				int colnum = 0;
				for (int i = 0; i < colname.length; i++) {
					if(colname[i].equals(col)) {
						colnum = i;
					}
				}
				if(isDigit(resultSet.get(0)[colnum])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(Integer.parseInt(resultSet.get(i)[colnum]) > Integer.parseInt(resultSet.get(j)[colnum])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(Integer.parseInt(resultSet.get(i)[colnum]) < Integer.parseInt(resultSet.get(j)[colnum])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(resultSet.get(i)[colnum].charAt(0) > resultSet.get(j)[colnum].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(resultSet.get(i)[colnum].charAt(0) < resultSet.get(j)[colnum].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 모든 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select(int col,String data) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 모든 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col번 열 기준으로 오름차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param order_col : 정렬 기준이 되는 열 / 열 번호
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,int order_col) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				if(isDigit(resultSet.get(0)[order_col])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(Integer.parseInt(resultSet.get(i)[order_col])
									> Integer.parseInt(resultSet.get(j)[order_col])) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(resultSet.get(i)[order_col].charAt(0)
									> resultSet.get(j)[order_col].charAt(0)) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 모든 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col번 열 기준으로 오름차순 혹은 내림차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param order_col : 정렬 기준이 되는 열 / 열 번호
	 * @param asc : true(오름차순) / false(내림차순)
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,int order_col,boolean asc) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				if(isDigit(resultSet.get(0)[order_col])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(Integer.parseInt(resultSet.get(i)[order_col])
										> Integer.parseInt(resultSet.get(j)[order_col])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(Integer.parseInt(resultSet.get(i)[order_col])
										< Integer.parseInt(resultSet.get(j)[order_col])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(resultSet.get(i)[order_col].charAt(0)
										> resultSet.get(j)[order_col].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(resultSet.get(i)[order_col].charAt(0)
										< resultSet.get(j)[order_col].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 모든 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col 이름의 열 기준으로 오름차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param order_col : 정렬 기준이 되는 열 / 열 이름
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,String order_col) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		ArrayList<String> temp = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				int colnum = 0;
				for (int i = 0; i < colname.length; i++) {
					if(colname[i].equals(order_col)) {
						colnum = i;
					}
				}
				if(isDigit(resultSet.get(0)[colnum])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(Integer.parseInt(resultSet.get(i)[colnum])
									> Integer.parseInt(resultSet.get(j)[colnum])) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(resultSet.get(i)[colnum].charAt(0)
									> resultSet.get(j)[colnum].charAt(0)) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 모든 데이터들을 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col 이름의 열 기준으로 오름차순 혹은 내림차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param order_col : 정렬 기준이 되는 열 / 열 이름
	 * @param asc : true(오름차순) / false(내림차순)
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,String order_col,boolean asc) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				int colnum = 0;
				for (int i = 0; i < colname.length; i++) {
					if(colname[i].equals(order_col)) {
						colnum = i;
					}
				}
				if(isDigit(resultSet.get(0)[colnum])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(Integer.parseInt(resultSet.get(i)[colnum])
										> Integer.parseInt(resultSet.get(j)[colnum])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(Integer.parseInt(resultSet.get(i)[colnum])
										< Integer.parseInt(resultSet.get(j)[colnum])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(resultSet.get(i)[colnum].charAt(0)
										> resultSet.get(j)[colnum].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(resultSet.get(i)[colnum].charAt(0)
										< resultSet.get(j)[colnum].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 데이터들 중 cols_name_to_search에 넘겨준 열에 해당하는 데이터들을
	 * 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param cols_name_to_search : 검색하고자 하는 열 이름들을 ","로 연결한 문자열
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select(int col,String data,String cols_name_to_search) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		String[] columns = cols_name_to_search.split(",");
		ArrayList<Integer> col_nums = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < colname.length; j++) {
					if(columns[i].equals(colname[j])) {
						col_nums.add(j);
					}
				}
			}
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			
			int len = resultSet.size();
			for (int i = 0; i < len; i++) {
				String[] temp2 = resultSet.get(i);
				ArrayList<String> temp3 = new ArrayList<String>();
				for (int j = 0; j < col_nums.size(); j++) {
					temp3.add(temp2[col_nums.get(j)]);
				}
				String[] temp4 = new String[temp3.size()];
				for (int j = 0; j < temp4.length; j++) {
					temp4[j] = temp3.get(j);
				}
				
				resultSet.set(i, temp4);
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 데이터들 중 cols_name_to_search에 넘겨준 열에 해당하는 데이터들을
	 * 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 오름차순 혹은 내림차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param cols_name_to_search : 검색하고자 하는 열 이름들을 ","로 연결한 문자열
	 * @param asc : true(오름차순) / false(내림차순)
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select(int col,String data,String cols_name_to_search,boolean asc) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		String[] columns = cols_name_to_search.split(",");
		ArrayList<Integer> col_nums = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < colname.length; j++) {
					if(columns[i].equals(colname[j])) {
						col_nums.add(j);
					}
				}
			}
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			
			int len = resultSet.size();
			for (int i = 0; i < len; i++) {
				String[] temp2 = resultSet.get(i);
				ArrayList<String> temp3 = new ArrayList<String>();
				for (int j = 0; j < col_nums.size(); j++) {
					temp3.add(temp2[col_nums.get(j)]);
				}
				String[] temp4 = new String[temp3.size()];
				for (int j = 0; j < temp4.length; j++) {
					temp4[j] = temp3.get(j);
				}
				
				resultSet.set(i, temp4);
			}
			if(!asc) {
				Collections.reverse(resultSet);
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}	
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 데이터들 중 cols_name_to_search에 넘겨준 열에 해당하는 데이터들을
	 * 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col번 열 기준으로 오름차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param cols_name_to_search : 검색하고자 하는 열 이름들을 ","로 연결한 문자열
	 * @param order_col : 정렬 기준이 되는 열 / 열 번호
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,String cols_name_to_search,int order_col) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		String[] columns = cols_name_to_search.split(",");
		ArrayList<Integer> col_nums = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String[] temp = br.readLine().split("\t");
			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < colname.length; j++) {
					if(columns[i].equals(colname[j])) {
						col_nums.add(j);
					}
				}
			}
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			for (int i = 0; i < len; i++) {
				String[] temp2 = resultSet.get(i);
				ArrayList<String> temp3 = new ArrayList<String>();
				for (int j = 0; j < col_nums.size(); j++) {
					temp3.add(temp2[col_nums.get(j)]);
				}
				String[] temp4 = new String[temp3.size()];
				for (int j = 0; j < temp4.length; j++) {
					temp4[j] = temp3.get(j);
				}
				
				resultSet.set(i, temp4);
			}
			String[] temp2;
			if(len != 0) {
				if(isDigit(resultSet.get(0)[order_col])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(Integer.parseInt(resultSet.get(i)[order_col])
									> Integer.parseInt(resultSet.get(j)[order_col])) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(resultSet.get(i)[order_col].charAt(0)
									> resultSet.get(j)[order_col].charAt(0)) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 데이터들 중 cols_name_to_search에 넘겨준 열에 해당하는 데이터들을
	 * 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col번 열 기준으로 오름차순 혹은 내림차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param cols_name_to_search : 검색하고자 하는 열 이름들을 ","로 연결한 문자열
	 * @param order_col : 정렬 기준이 되는 열 / 열 번호
	 * @param asc : true(오름차순) / false(내림차순)
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,String cols_name_to_search,int order_col,boolean asc) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		String[] columns = cols_name_to_search.split(",");
		ArrayList<Integer> col_nums = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String[] temp = br.readLine().split("\t");
			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < colname.length; j++) {
					if(columns[i].equals(colname[j])) {
						col_nums.add(j);
					}
				}
			}
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			for (int i = 0; i < len; i++) {
				String[] temp2 = resultSet.get(i);
				ArrayList<String> temp3 = new ArrayList<String>();
				for (int j = 0; j < col_nums.size(); j++) {
					temp3.add(temp2[col_nums.get(j)]);
				}
				String[] temp4 = new String[temp3.size()];
				for (int j = 0; j < temp4.length; j++) {
					temp4[j] = temp3.get(j);
				}
				
				resultSet.set(i, temp4);
			}
			String[] temp2;
			if(len != 0) {
				if(isDigit(resultSet.get(0)[order_col])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(Integer.parseInt(resultSet.get(i)[order_col])
										> Integer.parseInt(resultSet.get(j)[order_col])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(Integer.parseInt(resultSet.get(i)[order_col])
										< Integer.parseInt(resultSet.get(j)[order_col])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(resultSet.get(i)[order_col].charAt(0)
										> resultSet.get(j)[order_col].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(resultSet.get(i)[order_col].charAt(0)
										< resultSet.get(j)[order_col].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 데이터들 중 cols_name_to_search에 넘겨준 열에 해당하는 데이터들을
	 * 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col 이름의 열 기준으로 오름차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param cols_name_to_search : 검색하고자 하는 열 이름들을 ","로 연결한 문자열
	 * @param order_col : 정렬 기준이 되는 열 / 열 이름
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,String cols_name_to_search,String order_col) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		String[] columns = cols_name_to_search.split(",");
		ArrayList<Integer> col_nums = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String[] temp = br.readLine().split("\t");
			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < colname.length; j++) {
					if(columns[i].equals(colname[j])) {
						col_nums.add(j);
					}
				}
			}
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				int colnum = 0;
				for (int i = 0; i < colname.length; i++) {
					if(colname[i].equals(order_col)) {
						colnum = i;
					}
				}
				if(isDigit(resultSet.get(0)[colnum])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(Integer.parseInt(resultSet.get(i)[colnum])
									> Integer.parseInt(resultSet.get(j)[colnum])) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(resultSet.get(i)[colnum].charAt(0)
									> resultSet.get(j)[colnum].charAt(0)) {
								temp2 = resultSet.get(i);
								resultSet.set(i, resultSet.get(j));
								resultSet.set(j, temp2);
							}
						}
					}
				}
			}
			for (int i = 0; i < len; i++) {
				temp2 = resultSet.get(i);
				ArrayList<String> temp3 = new ArrayList<String>();
				for (int j = 0; j < col_nums.size(); j++) {
					temp3.add(temp2[col_nums.get(j)]);
				}
				String[] temp4 = new String[temp3.size()];
				for (int j = 0; j < temp4.length; j++) {
					temp4[j] = temp3.get(j);
				}
				
				resultSet.set(i, temp4);
			}

		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일에서 데이터를 검색합니다.
	 * 각 행의 col번 열을 확인하고, 그 곳에 data가 있다면 검색 결과에 해당하는 행입니다.
	 * 기준에 맞는 각 행에 있는 데이터들 중 cols_name_to_search에 넘겨준 열에 해당하는 데이터들을
	 * 배열로 모은 후 그 배열들이 담겨있는 ArrayList를 돌려줍니다.
	 * 돌려주기 전 order_col 이름의 열 기준으로 오름차순 혹은 내림차순 정렬을 해서 돌려줍니다.
	 * 
	 * @param col : 검색할 기준이 되는 열 / 열 번호
	 * @param data : 검색할 기준이 되는 데이터 
	 * @param cols_name_to_search : 검색하고자 하는 열 이름들을 ","로 연결한 문자열
	 * @param order_col : 정렬 기준이 되는 열 / 열 이름
	 * @param asc : true(오름차순) / false(내림차순)
	 * @return 검색된 데이터 
	 */
	public ArrayList<String[]> select_order_by(int col,String data,String cols_name_to_search,String order_col,boolean asc) {
		ArrayList<String[]> resultSet = new ArrayList<>();
		String[] columns = cols_name_to_search.split(",");
		ArrayList<Integer> col_nums = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			for (int i = 0; i < columns.length; i++) {
				for (int j = 0; j < colname.length; j++) {
					if(columns[i].equals(colname[j])) {
						col_nums.add(j);
					}
				}
			}
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				String[] datas = line.split("\t");
				if(datas[col].equals(data)) {
					resultSet.add(datas);
				}
			}
			int len = resultSet.size();
			String[] temp2;
			if(len != 0) {
				int colnum = 0;
				for (int i = 0; i < colname.length; i++) {
					if(colname[i].equals(order_col)) {
						colnum = i;
					}
				}
				if(isDigit(resultSet.get(0)[colnum])) {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(Integer.parseInt(resultSet.get(i)[colnum])
										> Integer.parseInt(resultSet.get(j)[colnum])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(Integer.parseInt(resultSet.get(i)[colnum])
										< Integer.parseInt(resultSet.get(j)[colnum])) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
				else {
					for (int i = 0; i < len-1; i++) {
						for (int j = i; j < len; j++) {
							if(asc) {
								if(resultSet.get(i)[colnum].charAt(0)
										> resultSet.get(j)[colnum].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
							else {
								if(resultSet.get(i)[colnum].charAt(0)
										< resultSet.get(j)[colnum].charAt(0)) {
									temp2 = resultSet.get(i);
									resultSet.set(i, resultSet.get(j));
									resultSet.set(j, temp2);
								}
							}
						}
					}
				}
			}
			for (int i = 0; i < len; i++) {
				temp2 = resultSet.get(i);
				ArrayList<String> temp3 = new ArrayList<String>();
				for (int j = 0; j < col_nums.size(); j++) {
					temp3.add(temp2[col_nums.get(j)]);
				}
				String[] temp4 = new String[temp3.size()];
				for (int j = 0; j < temp4.length; j++) {
					temp4[j] = temp3.get(j);
				}
				
				resultSet.set(i, temp4);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("======오류 발생 : 존재하지 않는 기준 컬럼======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return resultSet;
	}
	/**
	 * 연결되어 있는 테이블 텍스트 파일의 가장 마지막 행의 고유값(PK)을 돌려줍니다.
	 * 고유값이란 각 행을 대표하는 값이며 중복되지 않고 가장 앞에 존재합니다.
	 * @return 가장 마지막 행의 0번 열 값(고유값, PK)
	 */
	public String lastPK() {
		String pk = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			br.readLine();
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				pk=line.split("\t")[0];
			}
		} catch (FileNotFoundException e) {
			System.out.println("======오류 발생 : DB 파일 오류======");
			System.out.println(e);
			System.out.println("===============================");
		} catch (IOException e) {
			System.out.println("======오류 발생 : DB 연결 실패======");
			System.out.println(e);
			System.out.println("===============================");
		}
		return pk;
	}
	/**
	 * @param msg : 검사하고자 하는 msg
	 * @return true(msg가 숫자로 이루어진 문자열) / false(msg에 숫자가 아닌것이 포함되어 있음)
	 */
	private boolean isDigit(String msg) {
		for (int i = 0; i < msg.length(); i++) {
			char ch = msg.charAt(i);
			if(ch < '0' || ch > '9') {
				return false;
			}
		}
		return true;
	}
}









