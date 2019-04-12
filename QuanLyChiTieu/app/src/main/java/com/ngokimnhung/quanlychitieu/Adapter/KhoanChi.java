package com.ngokimnhung.quanlychitieu.Adapter;

import java.io.Serializable;

public class KhoanChi implements Serializable {
    private int id_khoanchi;
    private String tenkhoanchi;
    private String username;
    private String loaichi;
    private int sotienchi;
    private String ngaythangchi;

    public KhoanChi(int id_khoanchi, String tenkhoanchi, String username, String loaichi, int sotienchi, String ngaythangchi) {
        this.id_khoanchi = id_khoanchi;
        this.tenkhoanchi = tenkhoanchi;
        this.username = username;
        this.loaichi = loaichi;
        this.sotienchi = sotienchi;
        this.ngaythangchi = ngaythangchi;
    }

    public int getId_khoanchi() {
        return id_khoanchi;
    }

    public KhoanChi setId_khoanchi(int id_khoanchi) {
        this.id_khoanchi = id_khoanchi;
        return this;
    }

    public String getTenkhoanchi() {
        return tenkhoanchi;
    }

    public KhoanChi setTenkhoanchi(String tenkhoanchi) {
        this.tenkhoanchi = tenkhoanchi;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public KhoanChi setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLoaichi() {
        return loaichi;
    }

    public KhoanChi setLoaichi(String loaichi) {
        this.loaichi = loaichi;
        return this;
    }

    public int getSotienchi() {
        return sotienchi;
    }

    public KhoanChi setSotienchi(int sotienchi) {
        this.sotienchi = sotienchi;
        return this;
    }

    public String getNgaythangchi() {
        return ngaythangchi;
    }

    public KhoanChi setNgaythangchi(String ngaythangchi) {
        this.ngaythangchi = ngaythangchi;
        return this;
    }
}
