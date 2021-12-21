package com.tmdt.khachhang.model;

import java.util.Date;

public class TaiKhoan {
    private int id,gioi_tinh,so_lan_canh_cao,status;
    private String username,password,email,so_dien_thoai,ma_quyen;
    private Date ngay_tao,ngay_sinh;
    
    public TaiKhoan(){

    }

    public TaiKhoan(int gioi_tinh, int so_lan_canh_cao, int status, String username, String password, String email,
            String so_dien_thoai, String ma_quyen, java.util.Date ngay_tao, java.util.Date ngay_sinh) {
        super();
        this.gioi_tinh = gioi_tinh;
        this.so_lan_canh_cao = so_lan_canh_cao;
        this.status = status;
        this.username = username;
        this.password = password;
        this.email = email;
        this.so_dien_thoai = so_dien_thoai;
        this.ma_quyen = ma_quyen;
        this.ngay_tao = ngay_tao;
        this.ngay_sinh = ngay_sinh;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getgioi_tinh() {
        return gioi_tinh;
    }
    public void setgioi_tinh(int gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }
    public int getso_lan_canh_cao() {
        return so_lan_canh_cao;
    }
    public void setso_lan_canh_cao(int so_lan_canh_cao) {
        this.so_lan_canh_cao = so_lan_canh_cao;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }
    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }
    public String getma_quyen() {
        return ma_quyen;
    }
    public void setma_quyen(String ma_quyen) {
        this.ma_quyen = ma_quyen;
    }
    public Date getngay_tao() {
        return ngay_tao;
    }
    public void setngay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }
    public Date getngay_sinh() {
        return ngay_sinh;
    }
    public void setngay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }
}
