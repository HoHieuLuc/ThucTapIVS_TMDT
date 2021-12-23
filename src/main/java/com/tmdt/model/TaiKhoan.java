package com.tmdt.model;

import java.util.Date;

public class TaiKhoan {
    private int id,gioiTinh,soLanCanhCao,status;
    private String userName,password,email,soDienThoai,maQuyen,avatar;
    private Date ngayTao,ngaySinh;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getGioiTinh() {
        return gioiTinh;
    }
    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public int getSoLanCanhCao() {
        return soLanCanhCao;
    }
    public void setSoLanCanhCao(int soLanCanhCao) {
        this.soLanCanhCao = soLanCanhCao;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public String getMaQuyen() {
        return maQuyen;
    }
    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
    public Date getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    public Date getNgaySinh() {
        return ngaySinh;
    }
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    // Các constructor
    public TaiKhoan() {
    }
    public TaiKhoan(int gioiTinh, int soLanCanhCao, int status, String userName, String password, String email,
            String soDienThoai, String maQuyen, String avatar, Date ngayTao, Date ngaySinh) {
        this.gioiTinh = gioiTinh;
        this.soLanCanhCao = soLanCanhCao;
        this.status = status;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.maQuyen = maQuyen;
        this.avatar = avatar;
        this.ngayTao = ngayTao;
        this.ngaySinh = ngaySinh;
    }
    

   
}
