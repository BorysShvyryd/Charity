package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.pojo.EmailAuthenticator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Properties;

@Service
public class EmailService {

    private final static String PROPERTIES_FILE = "src/main/resources/email.properties";

    private Message message        = null;
    protected  static  String   SMTP_AUTH_USER = null;
    protected  static  String   SMTP_AUTH_PWD  = null;
    protected  static  String   EMAIL_FROM     = null;
    protected  static  String   SMTP_SERVER    = null;
    protected  static  String   SMTP_Port      = null;

    public boolean SendEmail(String userEmail,
                             String emailTopic,
                             String emailText) {

        boolean resultSend = false;

        try {
            InputStream is = new FileInputStream(PROPERTIES_FILE);
            if (is != null) {
                Reader reader = new InputStreamReader(is, "UTF-8");
                Properties props = new Properties();
                props.load(reader);
                SMTP_SERVER    = props.getProperty ("server" );
                SMTP_Port      = props.getProperty ("port"   );
                EMAIL_FROM     = props.getProperty ("from"   );
                SMTP_AUTH_USER = props.getProperty ("user"   );
                SMTP_AUTH_PWD  = props.getProperty ("pass"   );

                is.close();

                SendEmailCreate(userEmail, emailTopic);

                if (sendMessage(emailText)) {
                    System.out.println("Message sent");
                    resultSend =  true;
                } else {
                    System.out.println("Message not sent");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultSend;
    }

    private void SendEmailCreate(final String emailTo, final String thema)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.host"               , SMTP_SERVER  );
        properties.put("mail.smtp.port"               , SMTP_Port    );
        properties.put("mail.smtp.auth"               , "true"       );
        properties.put("mail.smtp.ssl.enable"         , "true"       );
        properties.put("mail.smtp.socketFactory.port" , SMTP_Port	 );
        properties.put("mail.smtp.ssl.protocols"	  , "TLSv1.2"	 );

        try {
            Authenticator auth = new EmailAuthenticator(SMTP_AUTH_USER, SMTP_AUTH_PWD);
            Session session = Session.getDefaultInstance(properties, auth);
            session.setDebug(false);

            InternetAddress email_from = new InternetAddress(EMAIL_FROM);
            InternetAddress email_to   = new InternetAddress(emailTo   );
            message = new MimeMessage(session);

            message.setFrom(email_from);
            message.setRecipient(Message.RecipientType.TO, email_to);
            message.setSubject(thema);
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean sendMessage (final String text)
    {
        try {

            Multipart mmp = new MimeMultipart();

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(text, "text/plain; charset=utf-8");
            mmp.addBodyPart(bodyPart);

            message.setContent(mmp);

            Transport.send(message);
            return true;
        } catch (MessagingException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
