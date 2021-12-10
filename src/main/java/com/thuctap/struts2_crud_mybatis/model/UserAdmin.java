package com.thuctap.struts2_crud_mybatis.model;

public class UserAdmin {
    private int id;
    private String username;
    private String password;
    private String email;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public UserAdmin(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
    @Override
    public String toString() {
        return "UserAdmin [email=" + email + ", password=" + password + ", username=" + username + "]";
    }

    
    
    
    
}
