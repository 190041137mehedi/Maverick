package sample.mailapiplusjdbc;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


public class Myemail {
    public static void main(String[] args) throws  Exception{
        Properties properties=new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.transport.protocol","smtp");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("fahimsadikrashad@gmail.com","abc.abc.2");
            }
        });
        //JavaMail API
        Message message=new MimeMessage(session);
        message.setSubject("Email from server");
        //message.setContent("<h1>email from my cool program</h1>","text/html");

        Address addressTo=new InternetAddress("fahimsadik@iut-dhaka.edu");
        message.setRecipient(Message.RecipientType.TO,addressTo);


        MimeMultipart multipart=new MimeMultipart();

        //MimeBodyPart attachmant=new MimeBodyPart();
        //attachmant.attachFile(new File("static/JavaMailAPI.pdf"));

        MimeBodyPart bodyPart=new MimeBodyPart();
        bodyPart.setContent("<h1>email from the jdk 15</h1>","text/html");

        multipart.addBodyPart(bodyPart);
        //multipart.addBodyPart(attachmant);

        message.setContent(multipart);

        Transport.send(message);
    }
}