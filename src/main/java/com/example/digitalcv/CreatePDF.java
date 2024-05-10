package com.example.digitalcv;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;


import com.itextpdf.text.Image;
import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@WebServlet (name = "CreatePDF", value = "/CV")
public class CreatePDF extends HttpServlet
{
   //private static String PASSWORD = "123456";
   private static String OWNER_PASS = "java";


   private static final String SECRET_KEY = "my_secret_key";

    //byte [] secret = "Dinkey".getBytes();
    //long movingFactor = 1234567890L;
    //int codeDigits = 6;
    //boolean addChecksum = true;
    //int truncationOffset = 0;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("Application/pdf");
        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("f");
        String lastName = request.getParameter("l");
        String Adress = request.getParameter("a");
        String mobile =request.getParameter("m");
        String email = request.getParameter("to");
        String github = request.getParameter("g");
        String profile = request.getParameter("p");
        String personbrev = request.getParameter("per");
        String utbildning = request.getParameter("u");
        String arebts = request.getParameter("arb");
        String program = request.getParameter("prog");
        String totp = TOTP.generateTOTP(SECRET_KEY);
        request.setAttribute("totp" , totp);


        //send mail
        String to = request.getParameter("to");
        final String usrname = "davidmarzban90@gmail.com";
        final String pass = "slzoyfrxkrodqeyx";
        // using host as localhost
        String host = "smtp.gmail.com";
        int port = 587;
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
            message.setText("Password is :" + totp);
            Transport.send(message);
        } catch (MessagingException me) {
            throw new RuntimeException(me);
        }




        Document doc = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try
        {

            //String hotp = HOTP.generateOTP(secret, movingFactor, codeDigits, addChecksum, truncationOffset);
            //request.setAttribute("hotp", hotp);

            PdfWriter writer = PdfWriter.getInstance(doc, outputStream);
            writer.setEncryption(totp.getBytes(), OWNER_PASS.getBytes(), PdfWriter.ALLOW_ASSEMBLY, PdfWriter.ENCRYPTION_AES_128);
            doc.open();

            String imageFile = "C:\\Users\\davii\\OneDrive\\Skrivbord\\Projekt\\DigitalCV\\digitalcv\\src\\main\\webapp\\Images\\cv.jpg";
            Font font0 = FontFactory.getFont(FontFactory.HELVETICA,28);
            font0.setColor(BaseColor.BLACK);
            Image image = Image.getInstance(imageFile);
            image.scaleToFit(30,25);
            Paragraph p = new Paragraph("    DigitalCV", font0);
            p.add(image);
            doc.add(p);

            //first and last name
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 30);
            font.setColor(BaseColor.BLUE);
            Paragraph firsname = new Paragraph(firstName + "  " + lastName, font);
            firsname.setIndentationLeft(150);
            doc.add(firsname);



            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            float[] pi = {50f, 50f};
            PdfPTable table = new PdfPTable(pi);
            //table.setWidthPercentage(100);
            table.addCell("Adrees ");
            table.addCell(Adress);

            table.addCell("Mobil");
            table.addCell(mobile);
            table.addCell("Email");
            table.addCell(email);
            table.addCell("GitHub-Länk");
            table.addCell(github);
            doc.add(table);


            Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 15);
            font1.setColor(BaseColor.BLUE);
            doc.add(new Paragraph("Profil" , font1));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(profile));


            doc.add(new Paragraph("  "));
            Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 15);
            font2.setColor(BaseColor.BLUE);
            doc.add(new Paragraph("Personsbrev", font2));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(personbrev));

            doc.add(new Paragraph("  "));
            Font font3 = FontFactory.getFont(FontFactory.HELVETICA, 15);
            font3.setColor(BaseColor.BLUE);
            doc.add(new Paragraph("Utbildning", font3));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(utbildning));


            doc.add(new Paragraph("  "));
            Font font4 = FontFactory.getFont(FontFactory.HELVETICA, 15);
            font4.setColor(BaseColor.BLUE);
            doc.add(new Paragraph("Arbetslivserfarenhet" , font4));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(arebts));

            doc.newPage();
            doc.add(new Paragraph("  "));
            Font font5 = FontFactory.getFont(FontFactory.HELVETICA, 15);
            font5.setColor(BaseColor.BLUE);
            doc.add(new Paragraph("Programfärdighet" , font5));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(program));


            doc.close();
            writer.close();

            response.setContentLength(outputStream.size());
            response.getOutputStream().write(outputStream.toByteArray());


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
