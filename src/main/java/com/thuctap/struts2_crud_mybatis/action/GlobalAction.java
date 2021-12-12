package com.thuctap.struts2_crud_mybatis.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.*;

public class GlobalAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    @Action(value = "/", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/index.jsp")
    })
    public String homePage() {
        return SUCCESS;
    }

    @Action(value = "/admin/index", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/index.jsp")
    }, interceptorRefs = { @InterceptorRef("loginStack") })
    public String adminPage() {
        return SUCCESS;
    }
}
