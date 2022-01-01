//Hàm này sẽ kiểm tra xem sản phẩm đó có trong giỏ hàng (khách hàng session) hay chưa
//Và sẽ cập nhật lên các button sản phẩm từ đặt hàng thành, Đã thêm vào giỏ hàng
const capNhatUIGioHang = ()=>{

}

const themVaoGioHang = (maSanPham) => {
    /*TODO */
    document.querySelector(`#button2_${maSanPham}`).innerText = "Đã thêm vào giỏ hàng"
    alert("Bạn đã thêm sản phẩm có mã là:" + maSanPham);
    // Gọi lại hàm cập nhật UI giỏ hàng
    capNhatUIGioHang();
}