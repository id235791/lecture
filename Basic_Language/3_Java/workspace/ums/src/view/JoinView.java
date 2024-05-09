package view;

import java.util.Scanner;

import controller.UserController;
import model.dto.UserDTO;

public class JoinView {
	public JoinView() {
		Scanner sc = new Scanner(System.in);
		UserController controller = new UserController();
		System.out.println("======회원가입 페이지 입니다======");
		System.out.print("아이디 : ");
		String userid = sc.next();
		//위에서 입력받은 userid 값을 controller에 넘겨주며 체크 요청
		if(controller.checkId(userid)) {
			System.out.print("비밀번호 : ");
			String userpw = sc.next();
			System.out.print("비밀번호 확인 : ");
			String userpw_re = sc.next();
			//비밀번호 체크는 백 과의 소통이 필요 없고, 입력받은 정보로 모두 해결 가능
			//controller의 메소드 호출이 없음
			if(userpw.equals(userpw_re)) {
				System.out.print("이름 : ");
				String username = sc.next();
				System.out.print("나이 : ");
				int userage = sc.nextInt();
				System.out.print("핸드폰 번호 : ");
				String userphone = sc.next();
				System.out.print("주소 : ");
				sc = new Scanner(System.in);
				String useraddr = sc.nextLine();
				
				//입력받은 정보들을 하나의 객체(DTO)로 포장
				UserDTO user = new UserDTO(userid, userpw, username, userage, userphone, useraddr);
				
				//정보를 담고 있는 DTO를 컨트롤러에 넘겨주며 가입 처리 요청
				if(controller.join(user)) {
					System.out.println("회원가입 성공!");
				}
				else {
					System.out.println("회원가입 실패 / 다음에 다시 시도해 주세요~");
				}
			}
			else {
				System.out.println("비밀번호 확인을 다시 해주세요!");
			}
		}
		else {
			System.out.println("중복된 아이디가 있습니다!");
		}
	}
}




