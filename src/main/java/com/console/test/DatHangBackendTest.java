package com.console.test;

import com.tmdt.db.ConnectDB;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.mapper.DatHangMapper;

public class DatHangBackendTest {
    //Getter and setter Simulator
    /*
        @Param("maKhachHang") String maKhachHang,
        @Param("tongTien") int tongTien,
        @Param("tinhTrang") int tinhTrang,
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
         
         try {
            datHangMapper.themDonDHMoi(maKhachHang, 88000, 0, "abcd", 1);
            // Khi sai mã sản phẩm ở câu querry thứ 2  
            // Thì câu querry thứ nhất không commit được dữ liệu vào database
            sqlSession.commit();
         }
         catch(PersistenceException e) {
            System.out.println(e.getMessage());
         }
         finally {
             
             sqlSession.close();
         }
    }
}
