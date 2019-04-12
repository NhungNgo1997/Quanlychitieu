package com.ngokimnhung.quanlychitieu.Adapter;

import java.io.Serializable;

public class Xuly implements Serializable {
    public int id_loaithu;
    public String tenloaithu;


    public Xuly(int id_loaithu, String tenloaithu) {
        this.id_loaithu = id_loaithu;
        this.tenloaithu = tenloaithu;
    }

    public int getId_loaithu() {
        return id_loaithu;
    }

    public Xuly setId_loaithu(int id_loaithu) {
        this.id_loaithu = id_loaithu;
        return this;
    }

    public String getTenloaithu() {
        return tenloaithu;
    }

    public Xuly setTenloaithu(String tenloaithu) {
        this.tenloaithu = tenloaithu;
        return this;
    }
}
