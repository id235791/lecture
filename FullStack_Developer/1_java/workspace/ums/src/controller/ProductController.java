package controller;

import java.util.ArrayList;

import model.Session;
import model.dao.ProductDAO;
import model.dto.ProductDTO;
import model.dto.UserDTO;

public class ProductController {
	public boolean addProduct(ProductDTO product) {
		//매개변수로 받은 product객체는 가상의 번호와 가상의 유저아이디를 가지고 있는 상태
		//가상의 번호 대신 실제로 새로운 번호, 가상의 유저아이디 대신 현재 로그인된 아이디로 바꿔주고
		//데이터베이스에 해당 정보들 삽입
		ProductDAO pdao = new ProductDAO();
		//새로운 번호 받아오기
		int newNum = pdao.getNewNum();
		//로그인된 아이디 받아오기
		String userid = ((UserDTO)Session.getData("loginUser")).getUserid();
		
		//기존에 있었던 상품에 새로운 받아온 정보들로 세팅
		product.setProdnum(newNum);
		product.setUserid(userid);
		
		//객체를 데이터 로직 이용해서 데이터베이스에 삽입
		return pdao.insertProduct(product);
	}

	public ArrayList<ProductDTO> getList(int choice, int asc) {
		ProductDAO pdao = new ProductDAO();
		String userid = ((UserDTO)Session.getData("loginUser")).getUserid();
		//내 리스트를 띄우는데 추가로 필요한 현재 로그인된 아이디까지 데이터 로직으로 함께 넘기기
		return pdao.getList(userid,choice,asc);
	}

	public boolean deleteProduct(int prodnum) {
		ProductDAO pdao = new ProductDAO();
		return pdao.deleteByProductNum(prodnum);
	}

	public boolean modifyProduct(int prodnum, int choice, String newData) {
		//choice : 1(가격수정 - 2열), 2(수량수정 - 3열), 3(설명수정 - 4열)
		ProductDAO pdao = new ProductDAO();
		return pdao.updateProduct(prodnum,choice,newData);
	}
}











