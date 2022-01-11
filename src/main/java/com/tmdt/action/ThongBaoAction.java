package com.tmdt.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;
import com.tmdt.utilities.JsonResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.ThongBaoMapper;



public class ThongBaoAction extends ActionSupport {
    private int status;
    private int id;
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // Lấy danh sách tất cả thông báo hoặc danh sách thông báo chưa đọc
    @Action(value = "/api/v1/thongbao/{status}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getThongBao() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        Integer idNguoiNhan = (Integer) session.getAttribute("accountID");

        // Kiểm tra đăng Nhập
        if (idNguoiNhan == null) {
            return CustomError.createCustomError("Bạn chưa đăng nhập",403, response);
        }

        List<Map<String, Object>> listThongBao;
        switch (status) {
            case 0:
                listThongBao = thongBaoMapper.getAllThongBaoChuaDocs(idNguoiNhan);
                break;
            case 999:
                int soThongBaoChuaDoc = thongBaoMapper.demSoThongBaoChuaDoc(idNguoiNhan);
                jsonRes.put("chua_doc", soThongBaoChuaDoc);
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            default:
                listThongBao = thongBaoMapper.getAllThongBao(idNguoiNhan);
                break;
        }
        jsonRes.put("thong_baos", listThongBao);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);

    }

    // Đánh dấu thông báo đó đã đọc '
    @Action(value = "/api/v1/thongbao/seen/{id}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String updateThongBaoStatus() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);
        Integer idNguoiNhan = (Integer) session.getAttribute("accountID");

             // Kiểm tra đăng Nhập
             if (idNguoiNhan == null) {
                return CustomError.createCustomError("Bạn chưa đăng nhập",403, response);
            }

        Map<String, Object> jsonRes = new HashMap<String, Object>();

        thongBaoMapper.danhDauAllDaDoc(idNguoiNhan);
        sqlSession.commit();
        sqlSession.close();

        jsonRes.put("message", "Đánh dấu toàn bộ thông báo đã đọc thành công");
        return JsonResponse.createJsonResponse(jsonRes, 200, response);

    }
}
