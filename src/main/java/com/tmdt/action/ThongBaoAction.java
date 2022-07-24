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

@InterceptorRef("authStack")
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

    //TODO: Lấy danh sách tất cả thông báo hoặc danh sách thông báo chưa đọc
    @Action(value = "/api/v1/thongbao/{status}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String getThongBao() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        Integer idNguoiNhan = (Integer) session.getAttribute("accountID");

        List<Map<String, Object>> listThongBao;
        // mặc định là toàn bộ thông báo, 0 là thông báo chưa đọc, -999 là 5 thông báo
        // gần
        // đây,999 là đếm số thông báo chưa đọc
        switch (status) {
            case 0:
                listThongBao = thongBaoMapper.getAllThongBaoChuaDocs(idNguoiNhan);
                break;
            case 999:
                int soThongBaoChuaDoc = thongBaoMapper.demSoThongBaoChuaDoc(idNguoiNhan);
                jsonRes.put("chua_doc", soThongBaoChuaDoc);
                return JsonResponse.createJsonResponse(jsonRes, 200, response);
            case -999:
                listThongBao = thongBaoMapper.getRecentlyThongBao(idNguoiNhan);
                break;
            default:
                listThongBao = thongBaoMapper.getAllThongBao(idNguoiNhan);
                break;
        }
        jsonRes.put("thong_baos", listThongBao);
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);

    }

    //TODO: Đánh dấu thông báo đó đã đọc '
    @Action(value = "/api/v1/thongbao/seen/{id}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String updateThongBaoStatus() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);
        Integer idNguoiNhan = (Integer) session.getAttribute("accountID");
        Map<String, Object> jsonRes = new HashMap<String, Object>();
        switch (id) {
            // -9999 là đánh dấu toàn bộ đã đọc
            case -9999:
                thongBaoMapper.danhDauAllDaDoc(idNguoiNhan);
                jsonRes.put("message", "Đánh dấu toàn bộ thông báo đã đọc thành công");
                break;

            default:
                // Đánh dấu đã đọc cho một thông báo cụ thể nào đó
                int validate = thongBaoMapper.danhDauDaDoc(id, idNguoiNhan);
                if (validate == 0) {
                    return CustomError.createCustomError("Thông báo không tồn tại", 404, response);
                } else {
                    jsonRes.put("message", "Đánh dấu đã đọc thông báo thành công");
                }
                break;
        }
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }

    //TODO: xóa thông báo
    @Action(value = "/api/v1/thongbao/delete/{id}", results = {
            @Result(name = SUCCESS, location = "/index.html")
    })
    public String deleteThongBao() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ThongBaoMapper thongBaoMapper = sqlSession.getMapper(ThongBaoMapper.class);
        Integer idNguoiNhan = (Integer) session.getAttribute("accountID");
        Map<String, Object> jsonRes = new HashMap<String, Object>();

        // xóa thông báo cụ thể nào đó
        int validate = thongBaoMapper.deleteThongBao(id, idNguoiNhan);
        if (validate == 0) {
            return CustomError.createCustomError("Thông báo không tồn tại", 404, response);
        } else {
            jsonRes.put("message", "Xóa thông báo thành công");
        }
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(jsonRes, 200, response);
    }
}
