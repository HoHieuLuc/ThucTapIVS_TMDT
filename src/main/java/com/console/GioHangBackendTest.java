package com.console;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.tmdt.db.ConnectDB;
import mybatis.mapper.GioHangMapper;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class GioHangBackendTest {
     
     static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
      public static void main(String[] args) {
          // SqlSession và Mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        GioHangMapper gioHangMapper = sqlSession.getMapper(GioHangMapper.class);

        // Lấy mã khách hàng từ session
        Integer maKhachHang = 1;
        // Tạo list gioHangs;
        List<Map<String, Object>> gioHangs;

        // Lấy seller id cho các sản phẩm trong giỏ hàng
        List<Integer> sellerID = gioHangMapper.getSellerList(maKhachHang);
        Gson gson = new Gson();
        System.out.println(gson.toJson(sellerID));
        gioHangs = gioHangMapper.getGioHangByMaKH(maKhachHang);
        sqlSession.commit();
        sqlSession.close();

        Map<String, Object> jsonRes = new HashMap<String, Object>();  
      

     }
}
