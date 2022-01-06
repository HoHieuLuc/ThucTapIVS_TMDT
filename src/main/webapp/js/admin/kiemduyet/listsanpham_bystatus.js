//Đổi tiêu đề trang web
document.title = "Trang phê duyệt sản phẩm "

//Nút hiển thị sản phẩm mới vô kho,yêu cầu kiểm duyệt,đã duyệt, và bị xóa (hide)
const listSPByStatusDOM = document.querySelector("#listSPByStatus");
//Khu vực danh sách sản phẩm thẻ <div>
const listSanPhamDOM = document.querySelector("#listSanPham");

//Render lại danh sách sản phẩm đúng với status  
listSPByStatusDOM.addEventListener('click', async () => {
    const status = listSPByStatusDOM.value;
    document.title = "Sản phẩm trong kho "
    const {data : {sanphams} } = await axios.post(`${baseURL}api/v1/sanpham/getbystatus/${status}`);
    console.log(sanphams);
    renderData(sanphams);
})

//Hàm xuất table ra màn hình trang quản lý
const renderData = (datas) => {
    const allSanPhams = datas.map(data => {
        const { ma_san_pham, ten_san_pham, gia, so_luong, ngay_dang, mo_ta, ten, ten_loai_sp } = data;
        return `
            <tr>
                <td>${ten_san_pham}</td>
                <td>${gia}</td>
                <td>${so_luong}</td>
                <td>   ${ngay_dang.date.day}/${ngay_dang.date.month}/${ngay_dang.date.year} 
                lúc ${ngay_dang.time.hour}h:${ngay_dang.time.minute}p</td>
                <td>${ten}</td>
                <td>${mo_ta}</td>
                <td>${ten_loai_sp}</td>
                <td>
                    <div class="d-flex justify-content-evenly">
                        <a href="./sanpham/${ma_san_pham}" class="">Chi tiết</a>
                    </div>
                </td>
            </tr>`;
    }).join('');
    listSanPhamDOM.innerHTML = allSanPhams;
}

renderData();


