package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MailSender {
        final String username = "andrei.ivanov.ext@extendaretail.com";
        final String password = "qfzaagzbkdvageoo";


    private static final Properties MAIL_PROPERTIES = new Properties(){{
        put("mail.smtp.host", "smtp.gmail.com");
        put("mail.smtp.port", "465");
        put("mail.smtp.auth", "true");
        put("mail.smtp.socketFactory.port", "465");
        put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        put("mail.smtp.ssl.trust", "smtp.gmail.com");
    }};

        public void sendMailMessage(String mailSubject,String mailContent){
             Session session = Session.getInstance(MAIL_PROPERTIES,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);}
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("andrei.ivanov.ext@extendaretail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("andrei.ivanov.ext@extendaretail.com"));
            message.setSubject(mailSubject);
            message.setText(mailContent);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
