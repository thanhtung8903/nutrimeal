package com.nutrimeal.nutrimeal.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void forgetPassword(String to, String userId, String token) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String emailContent = "<!DOCTYPE html>" +
                    "<html lang=\"vi\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "    <title>Quên mật khẩu</title>" +
                    "    <style>" +
                    "        body {" +
                    "            font-family: Arial, sans-serif;" +
                    "            background-color: #f4f4f4;" +
                    "            margin: 0;" +
                    "            padding: 0;" +
                    "        }" +
                    "        .email-container {" +
                    "            max-width: 600px;" +
                    "            margin: 50px auto;" +
                    "            padding: 20px;" +
                    "            background-color: #ffffff;" +
                    "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);" +
                    "            border-radius: 8px;" +
                    "            text-align: center;" +
                    "        }" +
                    "        .email-header {" +
                    "            background-color: #2C3E50;" +
                    "            padding: 20px;" +
                    "            color: #ffffff;" +
                    "            border-top-left-radius: 8px;" +
                    "            border-top-right-radius: 8px;" +
                    "        }" +
                    "        .email-header h2 {" +
                    "            margin: 0;" +
                    "            font-size: 24px;" +
                    "        }" +
                    "        .email-body {" +
                    "            padding: 20px;" +
                    "            color: #333333;" +
                    "            text-align: left;" +
                    "        }" +
                    "        .email-body p {" +
                    "            margin: 0 0 15px;" +
                    "            line-height: 1.6;" +
                    "        }" +
                    "        .email-footer {" +
                    "            text-align: center;" +
                    "            margin-top: 20px;" +
                    "        }" +
                    "        .reset-button {" +
                    "            display: inline-block;" +
                    "            padding: 10px 20px;" +
                    "            background-color: #2C3E50;" +
                    "            color: #ffffff;" +
                    "            text-decoration: none;" +
                    "            border-radius: 4px;" +
                    "            margin: 20px 0;" +
                    "        }" +
                    "        .reset-button:hover {" +
                    "            background-color: #1A252F;" +
                    "        }" +
                    "        .note {" +
                    "            color: #E74C3C;" +
                    "            font-size: 12px;" +
                    "        }" +
                    "        .footer {" +
                    "            background-color: #2C3E50;" +
                    "            padding: 10px;" +
                    "            color: white;" +
                    "            border-bottom-left-radius: 8px;" +
                    "            border-bottom-right-radius: 8px;" +
                    "            text-align: center;" +
                    "            font-size: 14px;" +
                    "        }" +
                    "        .footer a {" +
                    "            color: #ffffff;" +
                    "            text-decoration: none;" +
                    "            margin: 0 5px;" +
                    "        }" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "    <div class=\"email-container\">" +
                    "        <div class=\"email-header\">" +
                    "            <h2>QUÊN MẬT KHẨU</h2>" +
                    "        </div>" +
                    "        <div class=\"email-body\">" +
                    "            <p>Xin chào,</p>" +
                    "            <p>Chúng tôi nhận được yêu cầu đặt lại mật khẩu của bạn, vui lòng bấm vào nút bên dưới để được chuyển hướng đến trang khôi phục</p>" +
                    "            <div class=\"email-footer\">" +
                    "                <a style=\"color: white\" + href=\"http://localhost:8080/forget/" + userId + "/" + token + "\" class=\"reset-button\">Đặt lại mật khẩu</a>" +
                    "            </div>" +
                    "            <p class=\"note\">*Lưu ý: Liên kết chỉ có hiệu lực trong vòng 1 tiếng</p>" +
                    "            <p>Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>" +
                    "        </div>" +
                    "        <div class=\"footer\">" +
                    "            NutriMeal<br>" +
                    "            66 Hai Ba Trung, Hoan Kiem, Ha Noi<br>" +
                    "            0333311102<br>" +
                    "            <br>NutriMeal © All Rights Reserved" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(emailContent, true);
            helper.setTo(to);
            helper.setSubject("Quên mật khẩu");
            helper.setFrom("MS_w7us8S@trial-3zxk54vyyqp4jy6v.mlsender.net");
            mailSender.send(mimeMessage);
            LOGGER.info("email sent to {}", to);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

}