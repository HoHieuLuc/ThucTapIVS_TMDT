package com.thuctap.struts2_crud_mybatis.model;

import java.sql.Date;

public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
    private Date dateCreated;
    private Date dateExpired;

    public Account() {
    }

    public Account(String username, String password, String email, Date dateCreated, Date dateExpired) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
        this.dateExpired = dateExpired;
    }

    @Override
    public String toString() {
        return "UserAdmin [dateCreated=" + dateCreated + ", dateExpired=" + dateExpired + ", email=" + email
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date date_created) {
        this.dateCreated = date_created;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date date_expired) {
        this.dateExpired = date_expired;
    }

}