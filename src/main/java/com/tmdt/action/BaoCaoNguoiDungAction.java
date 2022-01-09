package com.tmdt.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.BaoCaoNguoiDungMapper;
import mybatis.mapper.TaiKhoanMapper;

public class BaoCaoNguoiDungAction extends ActionSupport {
    // Respone hay dùng cho AJAX và JSON
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();
    SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    private String userName;
    private String noiDung;
    private int maBaoCao;
    private int status;

    /* Region Getter and setter */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getMaBaoCao() {
        return maBaoCao;
    }

    public void setMaBaoCao(int maBaoCao) {
        this.maBaoCao = maBaoCao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    /* End Region Getter and setter */

    @Action(value = "/api/v1/baocao/{userName}/submit", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String baoCaoNguoiDung() throws IOException {
        System.out.println("Username bị báo cáo là " + userName);
        System.out.println("Nội dung báo cáo là " + noiDung);

        // Lấy id người gửi
        int idNguoiGui = (int) session.getAttribute("accountID");

        // Lấy id người nhận
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        TaiKhoanMapper taiKhoanMapper = sqlSession.getMapper(TaiKhoanMapper.class);
        int idNguoiNhan = taiKhoanMapper.getTaiKhoanIdByUsername(userName);

        // Chặn không cho mình tự báo cáo chính mình
        if (idNguoiGui == idNguoiNhan) {
            sqlSession.close();
            return CustomError.createCustomError("Bạn không thể tự báo cáo chính mình", 403, response);
        }

        // Chặn khi nội dung quá ngắn
        if (noiDung.length() < 20 || noiDung.length() > 255) {
            sqlSession.close();
            return CustomError.createCustomError("Nội dung phải từ 20 ký tự trở lên", 403, response);
        }

        // Tiến hành gửi báo cáo
        baoCaoNguoiDungMapper.guiBaoCaoNguoiDung(idNguoiNhan, idNguoiGui, noiDung);
        sqlSession.commit();
        sqlSession.close();
        return SUCCESS;
    }

    // api/v1/nhanvien/baocao/changestatus?maBaoCao=${ma_bao_cao}&status=${status}
    @Action(value = "/api/v1/nhanvien/baocao/changestatus", results = {
            @Result(name = SUCCESS, location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef(value = "khachHangStack"),
    })
    public String duyetBaoCaoNguoiDung() throws IOException {
        
        //Debug 
        System.out.println("Mã báo cáo: " + maBaoCao);
        System.out.println("Status: " + status);
        // Lấy id người nhận
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaoCaoNguoiDungMapper baoCaoNguoiDungMapper = sqlSession.getMapper(BaoCaoNguoiDungMapper.class);
        TaiKhoanMapper taiKhoanMapper = sqlSession.getMapper(TaiKhoanMapper.class);
        int idNguoiNhan = taiKhoanMapper.getTaiKhoanIdByUsername(userName);

        // Duyệt báo cáo đó
        baoCaoNguoiDungMapper.updateBaoCaoStatus(status, maBaoCao);

        // Tăng số lần cảnh cáo lên 1 và đồng thời gửi thông báo cho người bị vi phạm
        if (status == 1)
        {
            baoCaoNguoiDungMapper.tangSoLanCanhBao(idNguoiNhan);
            return CustomError.createCustomError("Đã duyệt 'vi phạm' cho báo cáo này", 200, response);
        }

        sqlSession.commit();
        sqlSession.close();
        return CustomError.createCustomError("SUCCESS cuối cùng", 200, response);
    }
}
