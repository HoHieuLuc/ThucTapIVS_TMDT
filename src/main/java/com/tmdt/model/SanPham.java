package com.tmdt.model;

import java.util.Date;

public class SanPham {
    private String maSanPham;
    private int maKhachHang;
    private String tenSanPham;
    private String moTa;
    private int gia;
    private int status;
    private int maLoaiSanPham;
    private int soLuong;
    private Date ngayDang;
    private int soLuongDaBan;

    public SanPham(String tenSanPham, int maKhachHang, String moTa, int gia, int status, int maLoaiSanPham, int soLuong, Date ngayDang, int soLuongDaBan) {
        this.tenSanPham = tenSanPham;
        this.maKhachHang = maKhachHang;
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

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
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
