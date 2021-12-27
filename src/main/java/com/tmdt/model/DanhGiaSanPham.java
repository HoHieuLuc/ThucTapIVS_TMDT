package com.tmdt.model;
import java.util.Date;

public class DanhGiaSanPham {

    private int maDanhGia;
    private int maKhachHang;
    private int soSao;
    private String noiDung;
    private String maSanPham;

    public DanhGiaSanPham() {
    }
    
    public DanhGiaSanPham(String maSanPham, int maKhachHang,  String noiDung, int soSao) {
        this.maSanPham = maSanPham;
        this.maKhachHang = maKhachHang;
        this.noiDung = noiDung;
        this.soSao = soSao;
    }


    public int getMaDanhGia() {
        return maDanhGia;
    }
    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }
    public int getMaKhachHang() {
        return maKhachHang;
    }
    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
    public int getSoSao() {
        return soSao;
    }
    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }
    public String getNoiDung() {
        return noiDung;
    }
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    public String getMaSanPham() {
        return maSanPham;
    }
    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
}
