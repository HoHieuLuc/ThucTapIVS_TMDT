package com.tmdt.model;

public class KhachHang {
    private int maKhachHang;
    private int idTaiKhoan;
    private double tienNo;
    private String ten;
    private String diaChi;
    private String gioiThieu;

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public double getTienNo() {
        return tienNo;
    }

    public void setTienNo(double tienNo) {
        this.tienNo = tienNo;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    // Các constructor
    public KhachHang() {
    }

    public KhachHang(int idTaiKhoan, double tienNo, String ten, String diaChi, String gioiThieu) {
        super();
        this.idTaiKhoan = idTaiKhoan;
        this.tienNo = tienNo;
        this.ten = ten;
        this.diaChi = diaChi;
        this.gioiThieu = gioiThieu;
    }
    // public KhachHang(){

    // }

    // public KhachHang(int id_tai_khoan,double tien_no, String ten, String dia_chi,
    // String gioi_thieu) {
    // super();
    // this.id_tai_khoan = id_tai_khoan;
    // this.tien_no = tien_no;
    // this.ten = ten;
    // this.dia_chi = dia_chi;
    // this.gioi_thieu = gioi_thieu;
    // }

}
