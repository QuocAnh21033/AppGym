package com.example.myapplication;


public class DongTac {
    private int Id;
    private  String Ten;
    private  String Buoc1;
    private  String Buoc2;
    private  String Buoc3;
    private byte[] Hinh;


    public DongTac(int id, String ten, String buoc1, String buoc2, String buoc3, byte[] hinh) {
        Id = id;
        Ten = ten;
        Buoc1 = buoc1;
        Buoc2 = buoc2;
        Buoc3 = buoc3;
        Hinh = hinh;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getBuoc1() {
        return Buoc1;
    }

    public void setBuoc1(String buoc1) {
        Buoc1 = buoc1;
    }

    public String getBuoc2() {
        return Buoc2;
    }

    public void setBuoc2(String buoc2) {
        Buoc2 = buoc2;
    }

    public String getBuoc3() {
        return Buoc3;
    }

    public void setBuoc3(String buoc3) {
        Buoc3 = buoc3;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }


}

