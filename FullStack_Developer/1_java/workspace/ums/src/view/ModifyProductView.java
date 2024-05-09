package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ProductController;
import model.dto.ProductDTO;

public class ModifyProductView {
	public ModifyProductView() {
		Scanner sc = new Scanner(System.in);
		ProductController controller = new ProductController();
		//상품 목록 띄우기
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
			//"어떤 상품"의 "어떤 정보"를 "무엇"으로 수정할것인지
			System.out.print("수정할 상품 번호 : ");
			int prodnum = sc.nextInt();
			System.out.println("1. 가격 수정\n2. 수량 수정\n3. 설명 수정");
			int choice = sc.nextInt();
			System.out.print("새로운 정보 : ");
			sc = new Scanner(System.in);
			String newData = sc.nextLine();
			
			//위에서 입력받은 세개의 정보를 컨트롤러에 넘겨주며 수정 요청
			if(controller.modifyProduct(prodnum,choice,newData)) {
				System.out.println(prodnum+"번 상품 수정 완료!");
			}
			else {
				System.out.println("상품 수정 실패 / 다음에 다시 시도해주세요.");
			}
		}
	}
}





