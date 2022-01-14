package com.tmdt.model;

public class LoaiSanPham {
    private int maLoaiSanPham;
    private String tenLoaiSanPham;
    private Integer maLoaiCha;
    private String anh;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int maLoaiSanPham, String tenLoaiSanPham, String anh, Integer maLoaiCha) {
        this.maLoaiSanPham = maLoaiSanPham;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.anh = anh;
        this.maLoaiCha = maLoaiCha;
    }

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getAnhLoaiSanPham() {
        return anh;
    }

    public void setAnhLoaiSanPham(String anh) {
        this.anh = anh;
    }

    public Integer getMaLoaiCha() {
        return maLoaiCha;
    }

    public void setMaLoaiCha(Integer maLoaiCha) {
        this.maLoaiCha = maLoaiCha;
    }
}
