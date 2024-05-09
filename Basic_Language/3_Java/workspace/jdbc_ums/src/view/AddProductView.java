package view;

import java.util.Scanner;

import controller.ProductController;
import model.dto.ProductDTO;

public class AddProductView {
	public AddProductView() {
		Scanner sc = new Scanner(System.in);
		ProductController controller = new ProductController();
		System.out.print("상품 이름 : ");
		String prodname = sc.nextLine();
		System.out.print("상품 가격 : ");
		int prodprice = sc.nextInt();
		System.out.print("상품 수량 : ");
		int prodamount = sc.nextInt();
		System.out.print("상품 설명 : ");
		sc = new Scanner(System.in);
		String prodinfo = sc.nextLine();
		
		//가상의 번호(0)와 가상의 유저(null)로 일단 포장
		ProductDTO product = new ProductDTO(0, prodname, prodprice,
				prodamount, prodinfo, 0, null);
		
		//포장된 객체 컨트롤러로 넘기기
		if(controller.addProduct(product)) {
			System.out.println(prodname+" 상품 추가 완료!");
		}
		else {
			System.out.println("상품 추가 실패 / 다음에 다시 시도해주세요.");
		}
	}
}










