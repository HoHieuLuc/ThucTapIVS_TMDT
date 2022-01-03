package com.console.test;

import java.util.List;
import java.util.Map;

import com.tmdt.db.ConnectDB;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.mapper.DatHangMapper;

public class DatHangBackendTest {
    //Getter and setter Simulator
    /*
      @Param("maDatHang") int maDatHang,
        @Param("maSanPham") String maSanPham,
        @Param("soLuong") int soLuong
    */
    static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();
    public static void main(String[] args) {
        //Sqlsession và mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);

         // Lấy mã khách hàng từ session
         Integer maKhachHang = 1;
         // Get giỏ hàng (lấy danh sách gồm mã sản phẩm và số lượng tương ứng cho từng sản phẩm đó)
         List<Map<String, Object>> gioHangByMaKHs = datHangMapper.getGioHangByMaKH(maKhachHang);
         try {
            datHangMapper.themDonDHMoi(maKhachHang);
            for (Map<String,Object> gh : gioHangByMaKHs) {
                System.out.println(gh.get("so_luong"));
                
            }
            //sqlSession.commit();
         }
         catch(PersistenceException e) {
            System.out.println(e.getMessage());
         }
         finally {
             
             sqlSession.close();
         }
    }
}
