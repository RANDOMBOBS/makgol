package com.org.makgol.util.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MailSendUtil {

    private final MailProperties mailProperties;
    private final JavaMailSender mailSender;
    // 난수 발생(여러분들 맘대러)

    public int makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        return checkNum;
    }

    public Boolean sendMail(int randomNumber, String eMail, boolean type) {

        Boolean result = false;
        String setFrom = this.mailProperties.getAccount(); // email-config에 설정한 자신의 이메일 주소를 입력
        String toMail = eMail;
        String title = type ? "회원 가입 인증 이메일 입니다." : "회원님의 임시 비밀번호 입니다.";// 이메일 제목
        String content = type ?
                "홈페이지를 방문해주셔서 감사합니다." +    //html 형식으로 작성 !
                        "<br><br>" +
                        "인증번호는 " + randomNumber + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요." :
                "홈페이지를 방문해주셔서 감사합니다." +    //html 형식으로 작성 !
                        "<br><br>" +
                        "임시 비밀번호는 " + randomNumber + "입니다." +
                        "<br>" +
                        "해당 비밀번호로 로그인후에 변경하시길 권장 드립니다.";
        //이메일 내용 삽입
        try {

            result = mailSend(setFrom, toMail, title, content);

            return result;
        } catch (Exception e) {
            return result;
        }
    }


    //이메일 전송 메소드
    public Boolean mailSend(String setFrom, String toMail, String title, String content) {

        Boolean result = false;
        MimeMessage message = mailSender.createMimeMessage();

        try {
            // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
            message.setFrom(setFrom);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            message.setSubject(title);
            message.setText(content, "UTF-8", "html");
            mailSender.send(message);
            result = true;

            return result;

        } catch (Exception e) {
            e.printStackTrace();

            return result;
        }
    }
}
