package api;

import java.util.Random;
import java.util.Scanner;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

public class PhoneCheck {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		System.out.print("핸드폰 번호 : ");
		String userphone = sc.next();
		
		String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~!@#$%^";
		int len = source.length();
		String otpMsg = "";
		Random r = new Random();
		
		for (int i = 0; i < 6; i++) {
			int idx = r.nextInt(len);
			otpMsg += source.charAt(idx);
		}
//		System.out.println(otpMsg);
		
		sendMsg(userphone,"아래의 인증번호를 입력하세요\n"+otpMsg);
		
		System.out.print("인증번호 : ");
		String otpMsg2 = sc.next();
		
		if(otpMsg.equals(otpMsg2)) {
			System.out.println("핸드폰 번호가 인증되었습니다!");
			System.out.println("가입을 계속 진행합니다...");
		}
		else {
			System.out.println("핸드폰 인증을 실패했습니다!");
		}
	}
	static void sendMsg(String to, String text) {
		DefaultMessageService dms = NurigoApp.INSTANCE.initialize(
				"{INPUT_YOUR_API_KEY}", "{INPUT_YOUR_API_SECRET_KEY}", "https://api.coolsms.co.kr");
		Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("{INPUT_YOUR_PHONE}");
        message.setTo(to);
        message.setText(text);

        SingleMessageSentResponse response = dms.sendOne(
        		new SingleMessageSendingRequest(message)
        );
	}
}
