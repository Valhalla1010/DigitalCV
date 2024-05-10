package com.example.digitalcv;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

import static com.example.digitalcv.TOTP.SECRET_KEY;
 @WebServlet(name = "SendEmail", value = "/my")
 public class SendEmail extends HttpServlet {

        private static String generateOTP() {

            return TOTP.generateTOTP(SECRET_KEY);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) {


            String totp = generateOTP();
            String to = request.getParameter("to");

            final String usrname = "davidmarzban90@gmail.com";
            final String pass = "slzoyfrxkrodqeyx";
            // using host as localhost
            String host = "smtp.gmail.com";
            int port = 587;

            // Getting system properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");


            Session session = Session.getInstance(props, new Authenticator() {
                protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new jakarta.mail.PasswordAuthentication(usrname, pass);
                }
            });
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(usrname));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("password");
                message.setText("paswword is :" + totp);

                Transport.send(message);
                System.out.println("message sent to Email");
            } catch (MessagingException me) {
                throw new RuntimeException(me);
            }

        }
    }

        /*public static void main(String [] args)
    {
        String recipient = "daviiid.m@hotmail.com";

        // email ID of  Sender.
        String sender = "davidmarzban90@gmail.com";

        final String usrname = "davidmarzban90@gmail.com";
        final String pass = "slzoyfrxkrodqeyx";
        // using host as localhost
        String host = "smtp.gmail.com";
        int port = 587;

        // Getting system properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usrname, pass);
            }
        });
        try
        {

            String one_time = generateOTP();
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("password");
            message.setText("paswword is :" + one_time);
            Transport.send(message);
            System.out.println("message sent to Email");
        }
        catch (MessagingException mex)
        {
            throw new RuntimeException(mex);
        }
    }*/

