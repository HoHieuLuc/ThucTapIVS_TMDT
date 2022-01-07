package com.tmdt.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.*;

@InterceptorRef(value = "nhanVienStack")
public class PheDuyetSanPhamAction extends ActionSupport{
    
    @Action(value = "/admin/phe-duyet-san-pham", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/pages/kiemduyet/sanpham/list.jsp")
    })
    public String view() {
        return "success";
    }
}
