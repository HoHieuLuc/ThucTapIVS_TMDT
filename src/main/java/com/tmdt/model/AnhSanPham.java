package com.tmdt.model;

public class AnhSanPham {
    private int maAnh;
    private String maSanPham;
    private String anh;

    public int getMaAnh() {
        return maAnh;
    }

    public void setMaAnh(int maAnh) {
        this.maAnh = maAnh;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public AnhSanPham(String maSanPham, String anh) {
        this.maSanPham = maSanPham;
        this.anh = anh;
    }
}
