package com.console.test;


import java.util.ArrayList;

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

           // // Lấy mã khách hàng từ session
         Integer maKhachHang = 1;
        int soLuongGH = 200;
        String maSanPham = "130ea67a-6528-11ec-b702-7845f2f0d96e";

       // int listCheckSPHetHang = gioHangMapper.checkSPHetHang(maKhachHang,maSanPham,soLuongGH);
        int soLuongSPHienCo = gioHangMapper.getSoLuongSPHienCo(maSanPham);
        if (soLuongGH > soLuongSPHienCo)
        {
               System.out.println("Sản phẩm này chỉ cho phép đặt tối đa là " + soLuongSPHienCo);
        }
        else System.out.println("OK");
        //System.out.println("Số lượng sản phẩm bị hết hàng   " +listCheckSPHetHang);
        

     
        // // Tạo list gioHangs;
        // List<Map<String, Object>> gioHangs = new ArrayList<Map<String,Object>>();
          
        // // Lấy seller id cho các sản phẩm trong giỏ hàng
        // List<Map<String,Object>> sellerList = gioHangMapper.getSellerList(maKhachHang);
        // Gson gson = new Gson();

        // // System.out.println("------------Danh sách sản phẩm chia theo id người bán--------");
        // for (Map<String, Object> seller : sellerList) {
        //    // System.out.println(gson.toJson(gioHangMapper.getGH_Info_By_Seller_ID(1, integer)));
        //     List<Map<String,Object>> sanPhams = gioHangMapper.getGH_Info_By_Seller_ID(1, (String) seller.get("username"));
        //     seller.put("san_phams", sanPhams);
        // }  
        // gioHangs.addAll(sellerList);
        // System.out.println("-----------Giỏ hàng--------");
        // System.out.println(gson.toJson(gioHangs));


        // if (gioHangMapper.deleteSP(maKhachHang,"35a99f29-64da-11ec-bb14-8378cfa7d63d") == 0) {
        //   System.out.println("Sản phẩm cần xóa không có");
        // }
        // sqlSession.commit();
           
        // }
        // System.out.println("--------Danh sách các sản phẩm theo từng id--------");
        // // System.out.println(gson.toJson(gioHangMapper.getGH_Info_By_Seller_ID(1,15)));
        // // sanPhams.putAll();


       // gioHangs = gioHangMapper.getGioHangByMaKH(maKhachHang);
        sqlSession.close();

        //Map<String, Object> jsonRes = new HashMap<String, Object>();  
      

     }
}
