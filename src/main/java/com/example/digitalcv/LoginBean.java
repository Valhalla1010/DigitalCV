package com.example.digitalcv;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

@Named
@RequestScoped
public class LoginBean {
    @Inject
    private Database database;
    //@NotNull(message = "fel name")
    private String username;

    //@NotNull(message = "fel pass")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login(){
        boolean a = database.check(username, password);
        if(a){
            return "profil1.xhtml";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fel Lösenord eller namn", "prova igen"));
            return null;
        }
    }
}
