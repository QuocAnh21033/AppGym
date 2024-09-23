package com.example.myapplication;

public class CaNhan {
    private int id;
    private String fullname;
    private String email;

    private String username;
    private String password;

    public CaNhan(int id,String fullname, String email, String username, String password) {
        this.id=id;
        this.fullname = fullname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public CaNhan(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
