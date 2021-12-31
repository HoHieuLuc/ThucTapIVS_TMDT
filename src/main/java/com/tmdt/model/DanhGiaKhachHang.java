package com.tmdt.model;

public class DanhGiaKhachHang {
    private int maDanhGia;
    private int maKHDanhGia;
    private int maKHDuocDanhGia;
    private int soSao;

    public DanhGiaKhachHang() {
    }

    public DanhGiaKhachHang(int maKHDanhGia, int maKHDuocDanhGia, int soSao) {
        this.maKHDanhGia = maKHDanhGia;
        this.maKHDuocDanhGia = maKHDuocDanhGia;
        this.soSao = soSao;
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public int getMaKHDanhGia() {
        return maKHDanhGia;
    }

    public void setMaKHDanhGia(int maKHDanhGia) {
        this.maKHDanhGia = maKHDanhGia;
    }

    public int getMaKHDuocDanhGia() {
        return maKHDuocDanhGia;
    }

    public void setMaKHDuocDanhGia(int maKHDuocDanhGia) {
        this.maKHDuocDanhGia = maKHDuocDanhGia;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }


    
    

    

    
    
}
