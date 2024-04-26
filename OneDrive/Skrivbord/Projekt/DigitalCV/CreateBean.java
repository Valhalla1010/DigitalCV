package com.example.digitalcv;

import com.itextpdf.text.DocumentException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@Named
@RequestScoped
public class CreateBean extends HttpServlet {
    private String firstname;
    private String lastname;
    private String image;

    private String adress;
    private String mobile;
    private String email;

    private String github;

    private String profile;
    private String personbrev;

    private String Utbildning;

    private String Arbets;

    private String program;



    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobile() {
        return mobile;
    }

    public String getImage() {
        return image;
    }

    public String getGithub() {
        return github;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return profile;
    }

    public String getPersonbrev() {
        return personbrev;
    }

    public String getArbets() {
        return Arbets;
    }

    public String getUtbildning() {
        return Utbildning;
    }

    public String getProgram() {
        return program;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setPersonbrev(String personbrev) {
        this.personbrev = personbrev;
    }

    public void setArbets(String arbets) {
        Arbets = arbets;
    }

    public void setUtbildning(String utbildning) {
        Utbildning = utbildning;
    }

    public void setProgram(String program) {
        this.program = program;
    }



    public void PDF(){
        CreatePDF.createPDF(firstname, lastname, image, adress, mobile, email, github, profile, personbrev, Utbildning, Arbets, program);

    }

    /*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/PDF");
        PrintWriter out = re
    }*/
}
