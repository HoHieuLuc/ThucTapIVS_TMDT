package com.console.test;

//import java.util.List;
//import java.util.Map;

import com.tmdt.db.ConnectDB;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

//import mybatis.mapper.DatHangMapper;

public class DatHangBackendTest {
    // Getter and setter Simulator
    /*
     * @Param("maDatHang") int maDatHang,
     * 
     * @Param("maSanPham") String maSanPham,
     * 
     * @Param("soLuong") int soLuong
     */
    static SqlSessionFactory sqlSessionFactory = ConnectDB.getSqlSessionFactory();

    public static void main(String[] args) {
        // Sqlsession và mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /* DatHangMapper datHangMapper = sqlSession.getMapper(DatHangMapper.class);

        // Lấy mã khách hàng từ session, khách hàng stacks
        int maKhachHang = 1;
        String usernameNguoiBan = "trantest";
 */
        // Get giỏ hàng (lấy danh sách gồm mã sản phẩm và số lượng tương ứng cho từng
        // sản phẩm đó)
        //List<Map<String, Object>> gioHangByMaKHs = datHangMapper.getGioHangBySeller(maKhachHang, usernameNguoiBan);
        try {
            // Thêm record vào đơn đặt hàng, và lấy mã đó để làm chi tiết đơn đặt hàng
            // int maDatHang = datHangMapper.themDonDHMoi(maKhachHang);
            //int maDatHang = datHangMapper.themDonDHTheoSeller(maKhachHang, usernameNguoiBan);

            // Duyệt từng cặp (số lượng, mã sản phẩm) trong giỏ hàng, thêm chi tiết đặt hàng
            // cho khách hàng vừa được tạo đơn đặt hang
            //for (Map<String, Object> gh : gioHangByMaKHs) {
               /*  datHangMapper.themChiTietDh(maDatHang, (String) gh.get("ma_san_pham"), maKhachHang,
                        (int) gh.get("so_luong")); */
            //}
            sqlSession.commit();
        } catch (PersistenceException e) {
            // Khi không thể tạo đơn đặt hàng
            System.out.println(e.getMessage());
            // Trả về danh sách sản phẩm hết hàng,error code 401
            //List<Map<String, Object>> checkSPHetHang = datHangMapper.checkSPHetHang(maKhachHang);
        } finally {

            sqlSession.close();
        }
    }
}
