package com.tmdt.khachhang.model;

public class KhachHang {
    private int ma_khach_hang,id_tai_khoan;
    private double tien_no;
    private String  ten, dia_chi,gioi_thieu;

    public KhachHang(){

    }

    public KhachHang(int id_tai_khoan,double tien_no, String ten, String dia_chi, String gioi_thieu) {
        super();
        this.id_tai_khoan = id_tai_khoan;
        this.tien_no = tien_no;
        this.ten = ten;
        this.dia_chi = dia_chi;
        this.gioi_thieu = gioi_thieu;
    }

    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getDia_chi() {
        return dia_chi;
    }
    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
    public String getGioi_thieu() {
        return gioi_thieu;
    }
    public void setGioi_thieu(String gioi_thieu) {
        this.gioi_thieu = gioi_thieu;
    }
    public int getMa_khach_hang() {
        return ma_khach_hang;
    }
    public void setMa_khach_hang(int ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }
    public int getId_tai_khoan() {
        return id_tai_khoan;
    }
    public void setId_tai_khoan(int id_tai_khoan) {
        this.id_tai_khoan = id_tai_khoan;
    }
    public double getTien_no() {
        return tien_no;
    }
    public void setTien_no(double tien_no) {
        this.tien_no = tien_no;
    }
 
}
