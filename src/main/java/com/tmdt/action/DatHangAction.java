package com.tmdt.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.DatHangMapper;
import mybatis.mapper.GioHangMapper;

public class DatHangAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    
    // Lấy mã khách hàng từ session
    Integer maKhachHang = (Integer) session.getAttribute("maNguoiDung");

    @Action(value = "/api/v1/dathang", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String datHang() throws IOException {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);

        //Thêm đơn đặt hàng mới
        try {
            datHangMapper.themDonDHMoi(maKhachHang);
            // Khi sai mã sản phẩm ở câu querry thứ 2
            // Thì câu querry thứ nhất không commit được dữ liệu vào database, dù nó chạy được
            sqlSession.commit();
        } catch (PersistenceException e) {
            //System.out.println(e.getMessage());
            if (e.getMessage().contains("foreign")){
                return CustomError.createCustomError("Thêm đơn đặt hàng thất bại",401,response);
            }
        } finally {
            sqlSession.close();
        }
        return SUCCESS;
    }

}
