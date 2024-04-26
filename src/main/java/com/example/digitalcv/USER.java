package com.example.digitalcv;


import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class USER {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username")
    private String Username;


    @Column(name="password")
    private String Password;

    @Column(name="email")
    private String Email;



    public USER(){

    }
    public USER(String username, String password, String email){
        this.Username = username;
        this.Password = password;
        this.Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }
}