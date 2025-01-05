package com.sporsalonu;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EpostaGonderici {

    private static final String GONDERICI_EPOSTA = "gonderici@gmail.com"; 
    private static final String GONDERICI_SIFRE = "uygulama_sifresi"; 

    public static void epostaGonder(String aliciEposta, String konu, String mesaj) throws UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GONDERICI_EPOSTA, GONDERICI_SIFRE);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            InternetAddress senderAddress = new InternetAddress(GONDERICI_EPOSTA);
            senderAddress.setPersonal("Spor Salonu");
            mimeMessage.setFrom(senderAddress);
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(aliciEposta));
            mimeMessage.setSubject(konu);
            mimeMessage.setText(mesaj);

            Transport.send(mimeMessage);
            System.out.println("E-posta başarıyla gönderildi: " + aliciEposta);
        } catch (MessagingException e) {
            System.out.println("E-posta gönderiminde hata oluştu: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        epostaGonder("alici@gmail.com", "Test Konusu", "Bu bir test mesajıdır.");
    }
}
