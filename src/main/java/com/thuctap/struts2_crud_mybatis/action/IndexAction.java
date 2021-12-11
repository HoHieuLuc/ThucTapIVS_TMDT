package com.thuctap.struts2_crud_mybatis.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.*;

@InterceptorRef(value = "loginStack")
public class IndexAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String test = "Hello World";

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Action(value = "/welcome", results = {
        @Result(name = "success", location = "/WEB-INF/jsp/welcome.jsp")
    })
    public String welcome() {
        return SUCCESS;
    }
}
