package view;

import java.util.Scanner;

import model.Session;
import model.dto.UserDTO;

public class MainView {
	public MainView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======메인 화면======");
		UserDTO loginUser = (UserDTO)Session.getData("loginUser");
		while(true) {
			if(loginUser == null) {
				System.out.println("로그인 후 이용하세요");
				break;
			}
			System.out.println("1. 상품 추가\n2. 상품 수정\n3. 상품 삭제\n4. 내 상품 보기\n"
					+ "5. 상품 검색\n6. 내 정보 수정\n7. 로그아웃");
			int choice = sc.nextInt();
			if(choice == 7) {
				System.out.println(loginUser.getUsername()+"님 다음에 또 오세요~");
				Session.setData("loginUser", null);
				break;
			}
			switch(choice) {
			case 1:
				new AddProductView();
				break;
			case 2:
				new ModifyProductView();
				break;
			case 3:
				new RemoveProductView();
				break;
			case 4:
				new MyListView();
				break;
			case 5:
				new SearchView();
				break;
			case 6:
				new MyInfoView();
				//MyInfoView는 세션정보가 수정되었을 가능성이 있다.(회원수정, 회원탈퇴)
				//지역변수 loginUser는 정보가 수정되기 전의 데이터들을 담고 있는 상태이다.
				//지역변수의 객체도 새로운 세션의 객체로 바꿔주어야 한다.
				loginUser = (UserDTO)Session.getData("loginUser");
				break;
			}
		}
	}
}











