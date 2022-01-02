package com.console;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.mysql.cj.xdevapi.Collection;
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
        Map<String, Object> sanPhams = new HashMap<String,Object>();

        // Lấy seller id cho các sản phẩm trong giỏ hàng
        List<Integer> sellerID = gioHangMapper.getSellerList(maKhachHang);
        Gson gson = new Gson();
        System.out.println("------------Danh sách các id người bán, có trong các sản phẩm của giỏ hàng---------");
        System.out.println(gson.toJson(sellerID));

        System.out.println("------------Danh sách sản phẩm chia theo id người bán--------");
        for (Integer integer : sellerID) {
            System.out.println(gson.toJson(gioHangMapper.getGH_Info_By_Seller_ID(1, integer)));
            //gioHangs.add(integer, gioHangMapper.getGH_Info_By_Seller_ID(1, integer));
        }  
           
        // }
        // System.out.println("--------Danh sách các sản phẩm theo từng id--------");
        // // System.out.println(gson.toJson(gioHangMapper.getGH_Info_By_Seller_ID(1,15)));
        // // sanPhams.putAll();


       // gioHangs = gioHangMapper.getGioHangByMaKH(maKhachHang);
        sqlSession.commit();
        sqlSession.close();

        //Map<String, Object> jsonRes = new HashMap<String, Object>();  
      

     }
}
