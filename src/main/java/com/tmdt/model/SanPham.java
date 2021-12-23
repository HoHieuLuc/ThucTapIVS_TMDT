package com.tmdt.model;

import java.util.Date;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private String moTa;
    private String gia;
    private int status;
    private String maLoaiSanPham;
    private int soLuong;
    private Date ngayDang;
    private int soLuongDaBan;

    public SanPham() {
    }

    public SanPham(String tenSanPham, String moTa, String gia, int status, String maLoaiSanPham, int soLuong, Date ngayDang, int soLuongDaBan) {
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.gia = gia;
        this.status = status;
        this.maLoaiSanPham = maLoaiSanPham;
        this.soLuong = soLuong;
        this.ngayDang = ngayDang;
        this.soLuongDaBan = soLuongDaBan;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(String maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public int getSoLuongDaBan() {
        return soLuongDaBan;
    }

    public void setSoLuongDaBan(int soLuongDaBan) {
        this.soLuongDaBan = soLuongDaBan;
    }
}
