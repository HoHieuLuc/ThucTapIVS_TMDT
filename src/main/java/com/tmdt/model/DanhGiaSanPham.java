package com.tmdt.model;
import java.util.Date;

public class DanhGiaSanPham {

    private int maDanhGia;
    private int maKhachHang;
    private int soSao;
    private String noiDung;
    private String maSanPham;
    private Date ngayTao; 
    private Date ngaySua;

    public DanhGiaSanPham() {
    }
    
    public DanhGiaSanPham(String maSanPham, int maKhachHang,  String noiDung, int soSao, Date ngayTao, Date ngaySua) {
        this.maSanPham = maSanPham;
        this.maKhachHang = maKhachHang;
        this.noiDung = noiDung;
        this.soSao = soSao;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
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
    public Date getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    public Date getNgaySua() {
        return ngaySua;
    }
    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }
}
