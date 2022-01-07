//Đổi tiêu đề trang web
document.title = "Trang phê duyệt sản phẩm "

//Nút hiển thị sản phẩm mới vô kho,yêu cầu kiểm duyệt,đã duyệt, và bị xóa (hide)
const listSPByStatusDOM = document.querySelector("#listSPByStatus");
//Khu vực danh sách sản phẩm thẻ <div>
const listSanPhamDOM = document.querySelector("#listSanPham");

//Render lại danh sách sản phẩm đúng với status  
listSPByStatusDOM.addEventListener('change', async () => {
    const status = listSPByStatusDOM.value;
    const { data: { sanphams } } = await axios.get(`${baseURL}api/v1/nhanvien/sanpham/getbystatus/${status}`);
    renderData(sanphams);
})

//Hàm xuất table ra màn hình trang quản lý
const renderData =  (datas) => {
    const allSanPhams = datas.map(data => {
        //Tui định làm @Result mà thấy file SanPhamMapper dài quá nên thôi ^_^!, ô thông cảm nhan )
        const { ma_san_pham, ten_san_pham, gia, so_luong, ngay_dang, ten, ten_loai_sp } = data;

        return `
            <tr>
                <td>${ten_san_pham}</td>
                <td>${gia}</td>
                <td>${so_luong}</td>
                <td>   ${ngay_dang.date.day}/${ngay_dang.date.month}/${ngay_dang.date.year}
                <td>${ten}</td>
                <td>${ten_loai_sp}</td>
                <td>
                    <div class="d-flex justify-content-evenly">
                         <a href="${baseURL}admin/sanpham/${ma_san_pham}" class="">Chi tiết</a>
                    </div>
                </td>
            </tr>`;
    }).join('');
    listSanPhamDOM.innerHTML = allSanPhams;
}

const firstTimeRun = async () => {
    const status = listSPByStatusDOM.value;
    document.title = "Sản phẩm trong kho "
    const { data: { sanphams } } = await axios.get(`${baseURL}api/v1/nhanvien/sanpham/getbystatus/${status}`);
    renderData(sanphams);
}
firstTimeRun();




