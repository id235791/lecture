package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ProductController;
import model.dto.ProductDTO;

public class RemoveProductView {
	public RemoveProductView() {
		Scanner sc = new Scanner(System.in);
		ProductController controller = new ProductController();
		//내가 올린 상품 목록 띄우기
		ArrayList<ProductDTO> list = controller.getList(1, 1);
		if(list == null) {
			System.out.println("등록된 상품이 없습니다!");
		}
		else {
			System.out.println("========내가 올린 상품 목록========");
			for(ProductDTO product : list) {
				System.out.printf("%d. %s : %d원(재고 : %d개)\n", product.getProdnum(),
						product.getProdname(), product.getProdprice(),product.getProdamount());
			}
			System.out.println("============================");
			
			System.out.print("삭제할 상품 번호 : ");
			int prodnum = sc.nextInt();
			//입력받은 상품 번호로 상품을 삭제해 달라고 컨트롤러로 요청
			if(controller.deleteProduct(prodnum)) {
				System.out.println(prodnum+"번 상품 삭제 완료!");
			}
			else {
				System.out.println("상품 삭제 실패 / 다음에 다시 시도해주세요.");
			}
		}
	}
}






