package com.console.test;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.thuctap.struts2_crud_mybatis.db.ConnectDB;
import com.thuctap.struts2_crud_mybatis.model.Student;
import com.tmdt.model.DanhGiaSanPham;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.mapper.DanhGiaSanPhamMapper;
import mybatis.mapper.KhachHangMapper;

// import mybatis.mapper.KhoaStudentMapper;

public class TestInnerJoin {
static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
   
   public static void main(String[] args) {
      SqlSession sqlSession = sqlSessionFactory.openSession();

      DanhGiaSanPhamMapper danhGiaSanPhamMapper = sqlSession.getMapper(DanhGiaSanPhamMapper.class);

      KhachHangMapper khachHangMapper = sqlSession.getMapper(KhachHangMapper.class);

       // Lấy ngày hiện tại:
      LocalDate today = LocalDate.now();
      //Múi giờ mặc định
      ZoneId defaultZoneId = ZoneId.systemDefault();
      // Đổi ngày tạo tài khoản và ngày hết hạn sang SQL Date
      java.util.Date ngayTao = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
      DanhGiaSanPham dgsp = new DanhGiaSanPham(1, 5, "dasdsads", "SP001", ngayTao, ngayTao);
      try {
          danhGiaSanPhamMapper.themDGSP(dgsp);
      } catch (PersistenceException e) {
          System.out.println(e.getMessage());
      }
      sqlSession.commit();
      sqlSession.close();
   }
}
