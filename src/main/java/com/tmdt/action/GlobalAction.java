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

    // route tới trang tìm kiếm sản phẩm
    @Action(value = "/search", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/search.jsp")
    })
    public String searchSanPham() {
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
    @Action(value = "/store", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/store/listStore.jsp")
    })
    public String listStorePage() {
        return SUCCESS;
    }

    // Xem danh sách loại sản phẩm
    @Action(value = "/category", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/category/category.jsp")
    })
    public String listLoaiSanPhamPage() {
        return SUCCESS;
    }

    // Xem danh sách sản phẩm đã được lọc theo loại sản phẩm
    // src\main\webapp\WEB-INF\jsp\product-type\index.jsp
    @Action(value = "/category/{params}", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/category/category.jsp")
    })
    public String listSanPhamByProductType() {
        return SUCCESS;
    }

    // Xem danh sách thông báo
    @Action(value = "/thongbao", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/thongbao/index.jsp")
    }, interceptorRefs = { @InterceptorRef("authStack") })
    public String viewThongBaoChuaDoc() {
        return SUCCESS;
    }

    /*
     * =============================================================================
     * =============
     */
    /* route cho nhân viên */

    // Dashboard (Thống kê bên admin)
    @Action(value = "/admin/index", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/index.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String adminPage() {
        return SUCCESS;
    }

    // phê duyệt sản phẩm
    @Action(value = "/admin/kiemduyet/sanpham", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/pages/kiemduyet/sanpham/index.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String pheDuyetSanPham() {
        return "success";
    }

    // Xem chi tiết sản phẩm để kiểm duyệt
    @Action(value = "/admin/kiemduyet/sanpham/{params}", results = {
            @Result(name = "SUCCESS", location = "/WEB-INF/jsp/admin/pages/kiemduyet/sanpham/detail.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String viewChiTietSanPhamAdmin() {
        return "SUCCESS";
    }

    @Action(value = "/admin/kiemduyet/baocao", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/pages/kiemduyet/baocao/index.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String pheDuyetBaoCao() {
        return "success";
    }

    // Xem chi tiết báo cáo để kiểm duyệt
    @Action(value = "/admin/kiemduyet/baocao/{params}", results = {
            @Result(name = "SUCCESS", location = "/WEB-INF/jsp/admin/pages/kiemduyet/baocao/detail.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String viewChiTietBaoCaoAdmin() {
        return "SUCCESS";
    }

    // danh sách loại sản phẩm
    @Action(value = "/admin/loaisanpham", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/pages/loaisanpham/index.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String listLoaiSanPham() {
        return "success";
    }

    // thêm loại sản phẩm
    @Action(value = "/admin/loaisanpham/them", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/admin/pages/loaisanpham/create.jsp")
    }, interceptorRefs = { @InterceptorRef("nhanVienStack") })
    public String addLoaiSanPham() {
        return "success";
    }

    /*
     * =============================================================================
     * ==============
     */
    /* route cho khách hàng */
    // sản phẩm yêu thích
    @Action(value = "/fav", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/fav/index.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack") })
    public String sanPhamYeuThich() {
        return SUCCESS;
    }

    // giỏ hàng
    @Action(value = "/cart", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/cart/index.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack") })
    public String cartPage() {
        return SUCCESS;
    }

    // trang đặt hàng
    @Action(value = "/dathang", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/dathang/index.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack") })
    public String orderPage() {
        return SUCCESS;
    }

    // dashboard (Thống kê bên khách hàng)
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

    // sửa sản phẩm
    @Action(value = "/user/sanpham/{params}/edit", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/sanpham/edit.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack")
    })
    public String userSanPhamEdit() {
        return SUCCESS;
    }

    // Trang theo dõi các đơn đặt hàng được đặt
    @Action(value = "/user/seller/dathang", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/dathang/seller/index.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack")
    })
    public String userDatHang() {
        return SUCCESS;
    }

    @Action(value = "/user/seller/dathang/{params}", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/dathang/seller/detail.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack")
    })
    public String userSellerChiTietDatHang() {
        return SUCCESS;
    }

    // Trang theo dõi các đơn đặt hàng mình đặt
    @Action(value = "/user/buyer/dathang", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/dathang/buyer/index.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack")
    })
    public String userBuyerDatHang() {
        return SUCCESS;
    }

    // Trang xem chi tiết 1 đơn hàng mình đặt
    @Action(value = "/user/buyer/dathang/{params}/{params}", results = {
            @Result(name = "success", location = "/WEB-INF/jsp/user/pages/dathang/buyer/detail.jsp")
    }, interceptorRefs = { @InterceptorRef("khachHangStack")
    })
    public String userBuyerChiTietDatHang() {
        return SUCCESS;
    }
}
