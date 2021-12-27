--- Trigger này sẽ hoạt động mỗi khi người dùng cập nhật đánh giá
-- Cứ khi người dùng chỉnh sửa đánh giá bao nhiêu lần, thì nó sẽ lưu lại bấy nhiêu đánh giá cũ đó.
 ---
CREATE TRIGGER `log_danh_gia_sp_updated` BEFORE UPDATE ON `danh_gia_san_pham` FOR EACH ROW BEGIN 
INSERT INTO log_danh_gia_san_pham(`ma_danh_gia`, `ma_khach_hang`, `so_sao`, `noi_dung`, `ma_san_pham`, `ngay_tao`, `ngay_sua`) VALUES (OLD.ma_danh_gia, OLD.ma_khach_hang, OLD.so_sao, OLD.noi_dung, OLD.ma_san_pham, OLD.ngay_tao, OLD.ngay_sua);
END