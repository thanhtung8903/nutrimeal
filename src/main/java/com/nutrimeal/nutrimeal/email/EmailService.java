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
                    "<html lang=\"en\">" +
                    "<head>" +
                    "    <meta charset=\"UTF-8\">" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                    "    <title>Đặt lại mật khẩu</title>" +
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
                    "        }" +
                    "        .email-header {" +
                    "            text-align: center;" +
                    "            margin-bottom: 20px;" +
                    "        }" +
                    "        .email-header h2 {" +
                    "            margin: 0;" +
                    "            color: #333333;" +
                    "        }" +
                    "        .email-body {" +
                    "            margin-bottom: 20px;" +
                    "        }" +
                    "        .email-body p {" +
                    "            color: #666666;" +
                    "            line-height: 1.6;" +
                    "        }" +
                    "        .email-footer {" +
                    "            text-align: center;" +
                    "            margin-top: 20px;" +
                    "        }" +
                    "        .reset-button {" +
                    "            display: inline-block;" +
                    "            padding: 10px 20px;" +
                    "            background-color: #007bff;" +
                    "            color: #ffffff;" +
                    "            text-decoration: none;" +
                    "            border-radius: 4px;" +
                    "        }" +
                    "        .reset-button:hover {" +
                    "            background-color: #0056b3;" +
                    "        }" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "    <div class=\"email-container\">" +
                    "        <div class=\"email-header\">" +
                    "            <h2>Đặt lại mật khẩu</h2>" +
                    "        </div>" +
                    "        <div class=\"email-body\">" +
                    "            <p>Xin chào,</p>" +
                    "            <p>Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình. Vui lòng nhấp vào nút bên dưới để đặt lại mật khẩu của bạn:</p>" +
                    "            <div class=\"email-footer\">" +
                    "                <a href=\"http://localhost:8080/forget/" + userId + "/" + token + "\" class=\"reset-button\">Đặt lại mật khẩu</a>" +
                    "            </div>" +
                    "            <p>Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>" +
                    "            <p>Trân trọng,</p>" +
                    "            <p>Đội ngũ hỗ trợ</p>" +
                    "        </div>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            helper.setText(emailContent, true);
            helper.setTo(to);
            helper.setSubject("Reset Password");
            helper.setFrom("MS_w7us8S@trial-3zxk54vyyqp4jy6v.mlsender.net");
            mailSender.send(mimeMessage);
            LOGGER.info("email sent to {}", to);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}