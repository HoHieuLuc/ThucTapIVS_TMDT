//Đổi tiêu đề trang web
document.title = "Trang phê duyệt sản phẩm "

const searchFormDOM = document.querySelector(".searchForm");

//Nút hiển thị sản phẩm mới vô kho,yêu cầu kiểm duyệt,đã duyệt, và bị xóa (hide)
const listSPByStatusDOM = document.querySelector("#listSPByStatus");
//Khu vực danh sách sản phẩm thẻ <div>
const listSanPhamDOM = document.querySelector("#listSanPham");
const phanTrangSanPhamDOM = document.querySelector("#phanTrangSanPham");

//Render lại danh sách sản phẩm đúng với status  
listSPByStatusDOM.addEventListener('change', async () => {
    const status = listSPByStatusDOM.value;
    removeURLparam("page");
    changeURLparam("status", status);
    showSanPhams();
});

const changePage = (page) => {
    changeURLparam("page", page);
    showSanPhams();
};

//Hàm xuất table ra màn hình trang quản lý
const renderData = (datas, totalPage, currentPage) => {
    const allSanPhams = datas.map((data, index) => {
        //Tui định làm @Result mà thấy file SanPhamMapper dài quá nên thôi ^_^!, ô thông cảm nhan )
        const { ma_san_pham, ten_san_pham, gia, so_luong, ngay_dang, ten, ten_loai_sp } = data;
        const giaVND = gia.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        return `
            <tr>
                <td>${ten_san_pham}</td>
                <td>${giaVND}</td>
                <td>${so_luong}</td>
                <td>${ngay_dang}</td>
                <td>${ten}</td>
                <td>${ten_loai_sp}</td>
                <td class="noExport">
                    <div class="d-flex justify-content-evenly">
                         <a href="${baseURL}admin/sanpham/${ma_san_pham}" class="">Chi tiết</a>
                    </div>
                </td>
            </tr>`;
    }).join('');
    listSanPhamDOM.innerHTML = allSanPhams;
    phanTrangSanPhamDOM.innerHTML = buildPagination(currentPage, totalPage, 5, "changePage");
}

const showSanPhams = async () => {
    const status = listSPByStatusDOM.value;
    const newParams = window.location.search;
    const search = new URLSearchParams(newParams).get("search") ?? "";
    const _status = new URLSearchParams(newParams).get("status") ?? 1;
    const page = new URLSearchParams(newParams).get("page") ?? 1;
    const { data: {
        sanphams, total_page
    } } = await axios.get(`${baseURL}api/v1/nhanvien/sanpham/getbystatus/${status}`, {
        params: {
            page,
            status: _status,
            search
        }
    });
    renderData(sanphams, total_page, page);
}
showSanPhams();

searchFormDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(searchFormDOM);
    removeURLparam("page");
    changeURLparam("search", formData.get("search"));
    showSanPhams();
});


