package com.tmdt.model;

public class Quyen {
    private String maQuyen, tenQuyen;
    private int capDo;
    
    public String getMaQuyen() {
        return maQuyen;
    }
    public void setMaQuyen(String maQuyen) {
        this.maQuyen = maQuyen;
    }
    public String getTenQuyen() {
        return tenQuyen;
    }
    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }
    public int getCapDo() {
        return capDo;
    }
    public void setCapDo(int capDo) {
        this.capDo = capDo;
    }

    //Các constructor
    public Quyen() {
    }

    public Quyen(String maQuyen, String tenQuyen, int capDo) {
        this.maQuyen = maQuyen;
        this.tenQuyen = tenQuyen;
        this.capDo = capDo;
    }

    

    

}
