package club.j3studios.a_login;

import club.j3studios.system.utils.Tools;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ServiceMail {
    
    public ModelMessage sendMain(String toEmail, String code) {
        new Tools().debug("Send Email to: " + toEmail);
        new Tools().debug("Send Code: " + code);
        
        ModelMessage ms = new ModelMessage(false, "");
        String from = "contactojosn3r@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        String username = "contactojosn3r@gmail.com";
        String password = "wlcxppfvinsdpqzc";    //  Your email password here
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Código de verificación J3Studios");
            
            String msg = "<center><h1>Hola!</h1></center>\n" +
            "\n" +
            "<center><p>¡Gracias por registrarte en J3Studios! Estamos emocionados de tenerte como parte de nuestra comunidad.</p></center>\n" +
            "\n" +
            "<center><p>Para completar tu registro, debes primero ingresar el código de confirmación que te enviamos por correo electrónico. El código es:</p></center>\n" +
            "\n" +
            "<center><h1>" + code + "</h1></center>\n" +
            "\n" +
            "<center><p>Una vez que hayas ingresado el código de confirmación, podrás acceder a tu cuenta de J3Studios y comenzar a usar nuestros servicios.</p></center>\n" +
            "\n" +
            "<center><p>Si tienes alguna pregunta, no dudes en contactarnos.</p></center>\n" +
            "\n" +
            "<center><p>Gracias,</p></center>\n" +
            "\n" +
            "<center><p>El equipo de J3Studios</p></center>";
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            
            Transport.send(message);
            ms.setSuccess(true);
        } catch (MessagingException e) {
            if (e.getMessage().equals("Invalid Addresses")) {
                new Tools().debug("Correo inválido");
            } else {
                new Tools().debug("Error sendMail!");
                new Tools().debug(e.getMessage());
            }
        }
        new Tools().debug("Email enviado correctamente!");
        return ms;
    }
}