package model.dao;

import java.util.ArrayList;

import model.DBConnection;
import model.dto.ProductDTO;

public class ProductDAO {
	DBConnection conn;
	public ProductDAO() {
		conn = new DBConnection("ProductTable.txt");
	}
	public int getNewNum() {
		//가장 마지막 상품 번호 받아오기
		String lastPK = conn.lastPK();
		//받아온게 없다면
		if(lastPK == null) {
			//첫 등록이라 1번
			return 1;
		}
		//받아온게 있다면
		else {
			//가장 마지막 번호 + 1 번
			return Integer.parseInt(lastPK)+1;
		}
	}
	public boolean insertProduct(ProductDTO product) {
		String data = String.format("%d\t%s\t%d\t%d\t%s\t%d\t%s",
				product.getProdnum(),product.getProdname(),product.getProdprice(),
				product.getProdamount(), product.getProdinfo(),0,product.getUserid());
		return conn.insert(data);
	}
	public ArrayList<ProductDTO> getList(String userid, int choice, int asc) {
		//어떤 기준으로 정렬할지 컬럼명이 담기는 변수
		String order_col = null;
		switch(choice) {
		case 1:
			order_col = "prodnum";
			break;
		case 2:
			order_col = "prodname";
			break;
		case 3:
			order_col = "prodprice";
			break;
		case 4:
			order_col = "likecnt";
			break;
		}
		//API 활용해서 기준 컬럼으로 검색(6번 컬럼에서 userid값으로 검색하되 order_col기준으로 정렬)
		ArrayList<String[]> result = conn.select_order_by(6, userid, order_col, asc==1);
		//result : 각 상품정보들이 배열에 담긴 채 List에 추가되어 있는 형태
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		//list : 실제 결과물(ProductDTO 가 List에 추가되어 있는 형태)
		if(result.size() > 0) {
			//검색된 결과에서 한 줄(String[])씩 꺼내오며
			for (String[] line : result) {
				//ProductDTO 객체로 만들어서 실제 결과물(list)에 추가해서
				list.add(new ProductDTO(line));
			}
			//리턴
			return list;
		}
		//검색된 결과가 없으면 null 리턴
		return null;
		
	}
	public boolean deleteByProductNum(int prodnum) {
		return conn.delete(prodnum+"");
	}
	public boolean updateProduct(int prodnum, int choice, String newData) {
		//choice : 1(가격수정), 2(수량수정), 3(설명수정)
		String[] cols = {"","prodprice","prodamount","prodinfo"};
		return conn.update(prodnum+"", cols[choice], newData);
	}
}











