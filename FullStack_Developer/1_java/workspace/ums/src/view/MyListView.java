package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ProductController;
import model.dto.ProductDTO;

public class MyListView {
	public MyListView() {
		Scanner sc = new Scanner(System.in);
		ProductController controller = new ProductController();
		
		System.out.println("1. 등록 순으로 보기\n2. 이름 순으로 보기\n3. 가격 순으로 보기\n4. 좋아요 순으로 보기");
		int choice = sc.nextInt();
		System.out.println("1. 오름차순\n2. 내림차순");
		int asc = sc.nextInt();
		
		ArrayList<ProductDTO> list = controller.getList(choice, asc);
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
		}
	}
}







