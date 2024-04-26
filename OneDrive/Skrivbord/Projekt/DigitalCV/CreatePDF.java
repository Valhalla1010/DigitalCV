package com.example.digitalcv;

import java.io.FileOutputStream;


import com.itextpdf.text.*;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Font;


public class CreatePDF
{
    public static void createPDF(String firstName, String lastName, String imageFilePath, String Adress, String mobile
            , String email, String github, String profile, String personbrev, String utbildning, String arebts, String program)
    {
        try
        {

            Document doc = new Document();
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\davii\\OneDrive\\Skrivbord\\exam.pdf"));
            doc.open();

            //first and last name
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 30);
            font.setColor(BaseColor.BLUE);
            Paragraph firsname = new Paragraph(firstName + "  " + lastName, font);
            firsname.setIndentationLeft(150);
            doc.add(firsname);

            //image
            Image image = Image.getInstance("C:\\Users\\davii\\OneDrive\\Skrivbord\\Min_CV\\VID-20230212-WA0023_exported_60~2.jpg");
            image.scaleToFit(100,100);
            doc.add(image);
            doc.add(new Paragraph("  "));


            //table
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            table.addCell("Adrees ");
            table.addCell(Adress);

            table.addCell("Mobil");
            table.addCell(mobile);

            table.addCell("Emiel  ");
            table.addCell(email);

            table.addCell("GitHub Länk");
            table.addCell(github);
            doc.add(table);


            doc.add(new Paragraph(" "));
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


            doc.add(new Paragraph("  "));
            Font font5 = FontFactory.getFont(FontFactory.HELVETICA, 15);
            font5.setColor(BaseColor.BLUE);
            doc.add(new Paragraph("Programfardighet" , font5));
            doc.add(new Paragraph(""));
            doc.add(new Paragraph(program));

            doc.close();
            writer.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}