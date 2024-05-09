package view;

import java.util.Scanner;

import controller.ProductController;
import model.Session;
import model.dto.ProductDTO;
import model.dto.UserDTO;

public class ProductInfoView {
	public ProductInfoView(int prodnum) {
		if(prodnum > 0) {
			Scanner sc = new Scanner(System.in);
			ProductController controller = new ProductController();
			String loginUser = ((UserDTO)Session.getData("loginUser")).getUserid(); 
			ProductDTO product = controller.getDetail(prodnum);
			while(true) {
				if(product != null) {
					System.out.println("========"+prodnum+"번 상품========");
					System.out.println("상품명 : "+product.getProdname());
					System.out.println("판매자 : "+product.getUserid()+"\t♥ : "+product.getLikecnt());
					System.out.println("가격 : "+product.getProdprice());
					System.out.println("남은 수량 : "+product.getProdamount());
					System.out.println("상세 설명\n☆★☆★☆★☆★☆★☆★\n"+product.getProdinfo()+"\n☆★☆★☆★☆★☆★☆★");
					System.out.println("============================");
					if(product.getUserid().equals(loginUser)) {
						System.out.println("1. 뒤로가기");
						int choice = sc.nextInt();
						if(choice == 1) {
							System.out.println("메인 화면으로 돌아갑니다...");
							break;
						}
					}
					else {
						System.out.println("1. 좋아요 누르기\n2. 판매자와 연락하기\n3. 뒤로가기");
						int choice = sc.nextInt();
						if(choice == 1) {
							//좋아요 누르기
							System.out.println("좋아요~♡");
							controller.updateLikeCnt(prodnum);
							product.setLikecnt(product.getLikecnt()+1);
						}
						else if(choice == 2) {
							//판매자 연락처 보여주기
							String userphone = controller.getSellerPhone(product.getUserid());
							System.out.println(userphone+"으로 연락하세요~");
						}
						else if(choice == 3) {
							System.out.println("메인 화면으로 돌아갑니다...");
							break;
						}
					}
				}
				else {
					System.out.println("상품 번호를 잘못 입력하셨습니다");
				}
			}
		}
		else {
			System.out.println("잘못된 페이지입니다 / 다음에 다시 시도해주세요.");
		}
	}
}







