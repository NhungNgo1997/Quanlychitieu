package com.ngokimnhung.quanlychitieu.Adapter;

import java.io.Serializable;

public class KhoanThu implements Serializable {
    private int id_khoanthu;
    private String tenkhoanthu;
    private String username;
    private int sotienthu;
    private String loaithu;
    private String ngaythangthu;

    public KhoanThu(int id_khoanthu, String tenkhoanthu, String username, int sotienthu, String loaithu, String ngaythangthu) {
        this.id_khoanthu = id_khoanthu;
        this.tenkhoanthu = tenkhoanthu;
        this.username = username;
        this.sotienthu = sotienthu;
        this.loaithu = loaithu;
        this.ngaythangthu = ngaythangthu;
    }

    public int getId_khoanthu() {
        return id_khoanthu;
    }

    public KhoanThu setId_khoanthu(int id_khoanthu) {
        this.id_khoanthu = id_khoanthu;
        return this;
    }

    public String getTenkhoanthu() {
        return tenkhoanthu;
    }

    public KhoanThu setTenkhoanthu(String tenkhoanthu) {
        this.tenkhoanthu = tenkhoanthu;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public KhoanThu setUsername(String username) {
        this.username = username;
        return this;
    }

    public int getSotienthu() {
        return sotienthu;
    }

    public KhoanThu setSotienthu(int sotienthu) {
        this.sotienthu = sotienthu;
        return this;
    }

    public String getLoaithu() {
        return loaithu;
    }

    public KhoanThu setLoaithu(String loaithu) {
        this.loaithu = loaithu;
        return this;
    }

    public String getNgaythangthu() {
        return ngaythangthu;
    }

    public KhoanThu setNgaythangthu(String ngaythangthu) {
        this.ngaythangthu = ngaythangthu;
        return this;
    }
}
