package api;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

public class SMSTest {
	public static void main(String[] args) {
		DefaultMessageService dms = NurigoApp.INSTANCE.initialize(
				"{INPUT_YOUR_API_KEY}", "{INPUT_YOUR_API_SECRET_KEY}", "https://api.coolsms.co.kr");
		Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("{INPUT_YOUR_PHONE}");
        message.setTo("{INPUT_YOUR_PHONE}");
        message.setText("문자 보내기 테스트! 제발 되길 바랍니다");

        SingleMessageSentResponse response = dms.sendOne(
        		new SingleMessageSendingRequest(message)
        );
        System.out.println(response);
	}
}



