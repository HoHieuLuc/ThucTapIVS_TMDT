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
             //Thêm record vào đơn đặt hàng
            datHangMapper.themDonDHMoi(maKhachHang);

            // Lấy mã đặt hàng gần đây nhất dựa vào mã khách hàng
            int maDHLately = datHangMapper.getMaDHLatelY(maKhachHang);

            // Duyệt từng cặp (số lượng, mã sản phẩm) trong giỏ hàng, thêm chi tiết đặt hàng cho
            // mã đặt hàng ở trên
            for (Map<String,Object> gh : gioHangByMaKHs) {
                //System.out.println(gh.get("so_luong"));
                
                datHangMapper.themChiTietDh(maDHLately,(String) gh.get("ma_san_pham"),(int) gh.get("so_luong"));
            }
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
