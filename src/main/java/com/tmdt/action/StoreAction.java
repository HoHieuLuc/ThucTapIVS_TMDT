package com.tmdt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

public class StoreAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    @Action(value = "/api/v1/store/{username}/info", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String storeInfo() {
        System.out.println("here");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("username: " + username);
        return SUCCESS;
    }
}
