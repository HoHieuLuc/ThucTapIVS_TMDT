package com.thuctap.struts2_crud_mybatis.model;
import java.time.LocalDate;
import java.sql.Date;

public class UserAdmin {
    private int id;
    private String username;
    private String password;
    private String email;
    private Date date_created;
    private Date date_expired;
    
    
   


    public UserAdmin(String username, String password, String email, Date date_created, Date date_expired) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.date_created = date_created;
        this.date_expired = date_expired;
    }
    @Override
    public String toString() {
        return "UserAdmin [date_created=" + date_created + ", date_expired=" + date_expired + ", email=" + email
                + ", id=" + id + ", password=" + password + ", username=" + username + "]";
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    
}
