//Đổi tiêu đề trang web
document.title = "Trang phê duyệt sản phẩm "

//Nút hiển thị sản phẩm mới vô kho,yêu cầu kiểm duyệt,đã duyệt, và bị xóa (hide)
const listTrongKhoDOM = document.querySelector("#trongKho");
const listYeuCauDuyetDOM = document.querySelector("#yeuCauDuyet");
const listDaDuyetDOM = document.querySelector("#daDuyet");
const listBiXoaDOM = document.querySelector("#biXoa");
//Khu vực danh sách sản phẩm thẻ <div>
const listSanPhamDOM = document.querySelector("#listSanPham");

//Render lại danh sách sản phẩm đúng với status  
listTrongKhoDOM.addEventListener('click', async () => {
    document.title = "Sản phẩm trong kho "
    const {data : {sanphams} } = await axios.post(`${baseURL}api/v1/sanpham/getbystatus/0`);
    console.log(sanphams);
    renderData(sanphams);
})

//Render lại danh sách sản phẩm đúng với status 
listBiXoaDOM.addEventListener('click', async () => {
    document.title = "Sản phẩm trong kho "
    const {data : {sanphams} } = await axios.post(`${baseURL}api/v1/sanpham/getbystatus/1`);
    console.log(sanphams);
    listSanPhamDOM.innerHTML = "<p>Test thử nút listBiXoa</p>";
})

//Render lại danh sách sản phẩm đúng với status 
listDaDuyetDOM.addEventListener('click', async () => {
    document.title = "Sản phẩm trong kho "
    const {data : {sanphams} } = await axios.post(`${baseURL}api/v1/sanpham/getbystatus/2`);
    console.log(sanphams);
    listSanPhamDOM.innerHTML = "<p>Test thử nút listDaDuyet</p>";
})

//Render lại danh sách sản phẩm đúng với status 
listYeuCauDuyetDOM.addEventListener('click', async () => {
    document.title = "Sản phẩm trong kho "
    const {data : {sanphams} } = await axios.post(`${baseURL}api/v1/sanpham/getbystatus/-1`);
    console.log(sanphams);
    listSanPhamDOM.innerHTML = "<p>Test thử nút listYeuCauDuyet</p>";
})

//Hàm xuất table ra màn hình trang quản lý
const renderData = (allSanPhams) => {
    const allSanPhams = sanphams.map(sanpham => {
        const { ma_san_pham, ten_san_pham, gia, so_luong, ngay_dang, mo_ta, ten_nguoi_ban, ten_loai_sp } = sanpham;
        return `
            <tr>
                <td>${ten_san_pham}</td>
                <td>${gia}</td>
                <td>${so_luong}</td>
                <td>${ngay_dang}</td>
                <td>${ten_nguoi_ban}</td>
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


