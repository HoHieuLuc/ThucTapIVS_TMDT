package com.tmdt.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.*;

@InterceptorRef(value = "nhanVienStack")
public class PheDuyetBaoCaoAction extends ActionSupport{

    @Action(value = "/admin/phe-duyet-bao-cao", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/pages/kiemduyet/baocao/index.jsp")
    })
    public String view() {
        return "success";
    } 
}
