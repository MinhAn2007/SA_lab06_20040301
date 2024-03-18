package org.example.sa_lab06_2004031.utils;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;
import java.util.logging.Logger;
import jakarta.mail.*;

public class MailingService {
    private static final Logger logger = Logger.getLogger(MailingService.class.getName());

    private static Session init() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        //prop.put("mail.smtp.host", "smtp.gmail.com");//in case of using gmail as mail host
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        return Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONSTANTS.HOST_MAIL_IDENTITY, CONSTANTS.HOST_MAIL_SECRET);
            }
        });
    }

    public static void sendTextMail(String receiver, String content) throws Exception {
        Session session = init();
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("vovanhaiqn@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        message.setSubject("Order information");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
        logger.info("message was sent to client");
    }

    public static void sendMailWithAttach(String receiver, String content, String filePath) throws Exception {

    }

   /* public static void main(String[] args) throws Exception{
        sendTextMail("vovanhai@gmail.com","Test mail");
    }*/
}
