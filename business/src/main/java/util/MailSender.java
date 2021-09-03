package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailSender {
        private static final String USER_NAME = "andrei.ivanov.ext@extendaretail.com";
        private static final String PASSWORD = "qfzaagzbkdvageoo";
        private static final String HOST= "smtp.gmail.com";
        private static final String PORT= "465";


    private static final Properties MAIL_PROPERTIES = new Properties(){{
        put("mail.smtp.host", HOST);
        put("mail.smtp.port", PORT);
        put("mail.smtp.auth", "true");
        put("mail.smtp.socketFactory.port", PORT);
        put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        put("mail.smtp.ssl.trust", HOST);
    }};

        public void sendMailMessage(String mailSubject,String mailContent){
             Session session = Session.getInstance(MAIL_PROPERTIES,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_NAME, PASSWORD);}
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(USER_NAME));
            message.setSubject(mailSubject);
            message.setText(mailContent);

            Transport.send(message);

            System.out.println("Message succesfuly sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
