package com.example.myapplication;

public class DanhGia {
    private int id;
    private String fullname;
    private String noidung;

    public DanhGia(int id, String fullname, String noidung) {
        this.id = id;
        this.fullname = fullname;
        this.noidung = noidung;
    }
    public DanhGia(){}

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

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
