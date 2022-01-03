package mybatis.mapper;

public interface DatHangMapper {
    //To obtain the value immediately after an INSERT, use a SELECT query with the LAST_INSERT_ID() function.
    // (https://dev.mysql.com/)
    final String THEM_DON_DH_MOI = "INSERT INTO `dat_hang`( `ma_khach_hang`, `ngay_dat`, `tong_tien`, `tinh_trang`) " +
        "VALUES (1,now(),88000,0); " +

    "INSERT INTO `chi_tiet_dat_hang`(`ma_dat_hang`, `ma_san_pham`, `so_luong`) " +
        "VALUES (LAST_INSERT_ID(),'1e717293-652c-11ec-b702-7845f2f0d96e',3);";

}
