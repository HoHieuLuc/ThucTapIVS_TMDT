package com.tmdt.action;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.convention.annotation.*;

public class GlobalAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private String params;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Action(value = "/", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/index.jsp")
    })
    public String homePage() {
        return SUCCESS;
    }

    // route giao diện xem chi tiết sản phẩm
    @Action(value = "/sanpham/{params}", results = {
            @Result(name = SUCCESS, location = "/WEB-INF/jsp/product/index.jsp")
    })
    public String viewChiTietSanPham() {
        return SUCCESS;
    }

    /* route cho store */
    // nếu để /store/{.*} hay /store/{[a-zA-Z0-9]+} hay đủ thứ loại regex
    // thì nó vẫn báo cái warning khá là khó chịu, nên tạo 1 biến params nhét vô
    // luôn
@Action(value = "/store/{params}", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/store/index.jsp")
    })
    public String storePage() {
        return SUCCESS;
    }

    // Xem danh sách các store
    @Action(value = "/listStore",results = {
        @Result(name = "success",location = "/WEB-INF/jsp/store/listStore.jsp")
    })
    public String listStorePage(){
        return SUCCESS;
    }


    //Xem danh sách sản phẩm đã được lọc theo loại sản phẩm
    //src\main\webapp\WEB-INF\jsp\product-type\index.jsp
    @Action(value = "/loaiSanPham/{params}",results = {
        @Result(name = "success",location = "/WEB-INF/jsp/product-type/index.jsp")
    })
    public String listSanPhamByProductType(){
        return SUCCESS;
    }

    /* route cho nhân viên */
    @Action(value = "/admin/index", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/index.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String adminPage() {
        return SUCCESS;
    }

    /* route cho khách hàng */
    // dashboard
    @Action(value = "/user/index", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/index.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack") })
    public String userPage() {
        return SUCCESS;
    }

    // danh sách sản phẩm
    @Action(value = "/user/sanpham", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/sanpham/index.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack") })
    public String userKhoHangPage() {
        return SUCCESS;
    }

    // chi tiết 1 sản phẩm
    @Action(value = "/user/sanpham/{params}", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/sanpham/detail.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack")
    })
    public String userSanPham() {
        return SUCCESS;
    }

    // thêm sản phẩm
    @Action(value = "/user/sanpham/them", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/sanpham/create.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack")
    })
    public String userSanPhamAdd() {
        return SUCCESS;
    }
}
