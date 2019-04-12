package com.ngokimnhung.quanlychitieu.Adapter;

import java.io.Serializable;

public class LoaiChi implements Serializable {
    private int id_loaichi;
    private  String tenloaichi;


    public LoaiChi(int id_loaichi, String tenloaichi) {
        this.id_loaichi = id_loaichi;
        this.tenloaichi = tenloaichi;
    }

    public int getId_loaichi() {
        return id_loaichi;
    }

    public LoaiChi setId_loaichi(int id_loaichi) {
        this.id_loaichi = id_loaichi;
        return this;
    }

    public String getTenloaichi() {
        return tenloaichi;
    }

    public LoaiChi setTenloaichi(String tenloaichi) {
        this.tenloaichi = tenloaichi;
        return this;
    }
}
