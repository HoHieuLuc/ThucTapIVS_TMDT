package com.thuctap.struts2_crud_mybatis.model;

public class KhoaStudent {
    private String name, TenKhoa;
    private int idStudent,idKhoa;
    public int getIdStudent() {
        return idStudent;
    }
    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
    public int getIdKhoa() {
        return idKhoa;
    }
    public void setIdKhoa(int idKhoa) {
        this.idKhoa = idKhoa;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTenKhoa() {
        return TenKhoa;
    }
    public void setTenKhoa(String tenKhoa) {
        TenKhoa = tenKhoa;
    }

    

    
   

    
}
