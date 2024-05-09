package fms;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
	public static void main(String[] args) throws Exception {
		index();
	}
	static void index() throws Exception {
		Scanner sc = new Scanner(System.in);
		Controller controller = new Controller();
		
		ArrayList<Model> list = null;
		while(true) {
			System.out.println("1. 데이터 추가\n2. 데이터 수정\n3. 데이터 삭제\n4. 데이터 전체 조회\n5. 나가기");
			int choice = sc.nextInt();
			
			if(choice == 5) {
				break;
			}
			switch(choice) {
			case 1:
				addView();
				break;
			case 2:
				updateView();
				break;
			case 3:
				deleteView();
				break;
			case 4:
				list = controller.selectAll();
//				for(Model model : list) {
//					System.out.println(model.data);
//				}
				for (int i = 0; i < list.size(); i++) {
					System.out.println(i+"번째 데이터 : "+list.get(i).data);
				}
				break;
			}
		}
	}
	static void addView() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("추가할 데이터 : ");
		String data = sc.nextLine();
		
		Model model = new Model();
		model.data = data;
		
		Controller controller = new Controller();
		controller.insert(model);
	}
	static void deleteView() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 데이터 : ");
		String data = sc.nextLine();
		
		Controller controller = new Controller();
		controller.delete(data);
	}
	static void updateView() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 데이터 : ");
		String oldData = sc.nextLine();
		System.out.print("새로운 데이터 : ");
		String newData = sc.nextLine();
		
		Controller controller = new Controller();
		controller.update(oldData, newData);
	}
}





