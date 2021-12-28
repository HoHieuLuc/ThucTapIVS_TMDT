package com.tmdt.action;

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

    // route giao diện xem chi tiết sản phẩm
    @Action(value = "/sanpham/*", results = {
            @Result(name = SUCCESS, location = "/WEB-INF/jsp/product/index.jsp")
    })
    public String viewChiTietSanPham() {
        return SUCCESS;
    }

    /* route cho store */
    @Action(value = "/store/*", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/store/index.jsp")
    })
    public String storePage() {
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
    @Action(value = "/user/sanpham/*", results = {
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
