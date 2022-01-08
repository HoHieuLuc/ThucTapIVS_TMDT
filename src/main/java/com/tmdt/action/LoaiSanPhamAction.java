package com.tmdt.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.tmdt.db.ConnectDB;
import com.tmdt.errors.CustomError;
import com.tmdt.model.LoaiSanPham;
import com.tmdt.utilities.JsonResponse;
import com.tmdt.utilities.ProjectPath;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import mybatis.mapper.LoaiSanPhamMapper;

@Result(name = "input", location = "/index", type = "redirectAction", params = {
    "namespace", "/",
    "actionName", "bad-request"
})
public class LoaiSanPhamAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private int maLoaiSanPham;
    private String tenLoaiSanPham;
    private Integer maLoaiCha;
    private File anhLoaiSanPham;
    private String anhLoaiSanPhamFileName;
    private String anhLoaiSanPhamContentType;

    // region Getter and Setter
    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public Integer getMaLoaiCha() {
        return maLoaiCha == 0 ? null : maLoaiCha;
    }

    public void setMaLoaiCha(Integer maLoaiCha) {
        this.maLoaiCha = maLoaiCha;
    }

    public File getAnhLoaiSanPham() {
        return anhLoaiSanPham;
    }

    public void setAnhLoaiSanPham(File anhLoaiSanPham) {
        this.anhLoaiSanPham = anhLoaiSanPham;
    }

    public String getAnhLoaiSanPhamFileName() {
        return anhLoaiSanPhamFileName;
    }

    public void setAnhLoaiSanPhamFileName(String anhLoaiSanPhamFileName) {
        this.anhLoaiSanPhamFileName = anhLoaiSanPhamFileName;
    }

    public String getAnhLoaiSanPhamContentType() {
        return anhLoaiSanPhamContentType;
    }

    public void setAnhLoaiSanPhamContentType(String anhLoaiSanPhamContentType) {
        this.anhLoaiSanPhamContentType = anhLoaiSanPhamContentType;
    }
    // endregion

    HttpServletResponse response = ServletActionContext.getResponse();
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpSession session = request.getSession();

    private SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    // Dành cho menu loại sản phẩm khi add sản phẩm mới
    @Action(value = "/api/v1/loaisanpham/{maLoaiSanPham}", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllLoaiSanPham() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        List<Map<String, Object>> loaiSanPhams = new ArrayList<>();
        if (maLoaiSanPham == 0) {
            loaiSanPhams = loaiSanPhamMapper.getAllLoaiSanPhamCapCao();
        } else {
            loaiSanPhams = loaiSanPhamMapper.getAllLoaiSanPhamCon(maLoaiSanPham);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loaiSanPhams", loaiSanPhams);
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // Kiểm tra xem category đó có sản phẩm hay chưa
    @Action(value = "/api/v1/category_have_product", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllTenLoaiSanPham() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        List<LoaiSanPham> loaiSanPhams = loaiSanPhamMapper.getAllTenLoaiSanPham();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loaiSanPhams", loaiSanPhams);
        sqlSession.commit();
        sqlSession.close();
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    // Liệt kê sản phẩm cùng category
    @Action(value = "/api/v1/category/{maLoaiSanPham}", results = {
            @Result(name = "success", location = "/index.html")
    })
    public String getAllSanPhamByLSP() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        List<Map<String, Object>> sanPhams = loaiSanPhamMapper.getAllSanPhamByLSP(maLoaiSanPham);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sanphams", sanPhams);
        return JsonResponse.createJsonResponse(map, 200, response);
    }

    /* ================== */
    /* Dành cho nhân viên */
    /* ================== */

    // thêm loại sản phẩm
    boolean isValid() {
        return tenLoaiSanPham != null && tenLoaiSanPham.length() > 0
                && anhLoaiSanPham != null && anhLoaiSanPhamFileName != null
                && anhLoaiSanPhamContentType.contains("image/");
    }

    @Action(value = "/api/v1/admin/loaisanpham/them", results = {
            @Result(name = "success", location = "/index.html")
    }, interceptorRefs = {
            @InterceptorRef("nhanVienStack")
    })
    public String themLoaiSanPham() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoaiSanPhamMapper loaiSanPhamMapper = sqlSession.getMapper(LoaiSanPhamMapper.class);
        if (!isValid()) {
            return CustomError.createCustomError("Vui lòng nhập đầy đủ thông tin", 400, response);
        }
        try {
            String fileNameNew = System.currentTimeMillis() + "_" + anhLoaiSanPhamFileName;
            LoaiSanPham loaiSanPham = new LoaiSanPham(0, tenLoaiSanPham, fileNameNew, getMaLoaiCha());
            loaiSanPhamMapper.addLoaiSanPham(loaiSanPham);

            String filePath = session.getServletContext().getRealPath("/") + "images\\category\\";
            String LocalPath = ProjectPath.getPath() + "\\images\\category\\";

            File fileTmp = new File(filePath + fileNameNew);
            File anhCopy = new File(LocalPath + fileNameNew);

            FileUtils.copyFile(this.anhLoaiSanPham, fileTmp);
            FileUtils.copyFile(this.anhLoaiSanPham, anhCopy);
            return CustomError.createCustomError("Thêm loại sản phẩm thành công", 200, response);
        } catch (PersistenceException e) {
            if (e.getMessage().contains("ten_loai_sp_UNIQUE")) {
                return CustomError.createCustomError("Tên loại sản phẩm đã tồn tại", 409, response);
            }
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
        return CustomError.createCustomError("Có lỗi xảy ra", 500, response);
    }
}
