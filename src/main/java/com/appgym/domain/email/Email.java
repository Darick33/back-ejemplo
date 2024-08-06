package com.appgym.domain.email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Email {
    private static String emailFrom = "systemgym0001@gmail.com";
    private static String passwordFrom = "vswetusjdoxtufvs";
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public Email(String emailTo, String subject, String content) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.content = content;
        mProperties = new Properties();

        this.createEmail();
    }

    public void createEmail() {
        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user",emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);


        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");


        } catch (AddressException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            return true;

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}