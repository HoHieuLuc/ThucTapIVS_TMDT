const sanPhamListDOM = document.querySelector('#sanpham-list');
const paginationDOM = document.querySelector('#pagination');
const searchFormDOM = document.querySelector('.searchForm');

const init = () => {
    const newParams = window.location.search;
    const search = new URLSearchParams(newParams).get("search") ?? "";
    const _status = new URLSearchParams(newParams).get("status") ?? 0;
    searchFormDOM.querySelector('input[name="search"]').value = search;
    searchFormDOM.querySelector('select[name="status"]').value = _status;
}

init();

const changePage = (page) => {
    changeURLparam("page", page);
    showSanPhamList();
};

const showSanPhamList = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get("page") ?? 1;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const _status = new URLSearchParams(newParams).get("status") ?? 0;
        const { data: { sanphams, total_page } } = await axios.get(`${baseURL}api/v1/user/sanpham`, {
            params: {
                search,
                status: _status,
                page,
            }
        });
        let tinh_trang = "";
        if (_status == 0) {
            tinh_trang = "Trong kho";
        }
        else if (_status == 1) {
            tinh_trang = "Đang chờ duyệt";
        } else if (_status == 2) {
            tinh_trang = "Đang bán";
        }
        document.querySelector('.tlt-fixed-table').setAttribute("filename", `Danh sách sản phẩm của bạn (${tinh_trang})`);
        const allSanPhams = sanphams.map(sanpham => {
            const { ma_san_pham, ten_san_pham, gia, so_luong,
                ngay_dang, so_luong_da_ban, xep_hang
            } = sanpham;

            //Thêm tên trạng thái vào thuộc tính filename trong thẻ table..
            const ngayDang = `${ngay_dang.date.day}/${ngay_dang.date.month}/${ngay_dang.date.year}`;
            const giaVND = gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <tr>
                    <td title="${ten_san_pham}">${ten_san_pham}</td>
                    <td>${giaVND}</td>
                    <td>${so_luong}</td>
                    <td>${so_luong_da_ban}</td>
                    <td>${ngayDang}</td>
                    <td>${xep_hang ?? 0}</td>
                    <td class="noExl">
                        <div class="d-flex justify-content-evenly">
                            <a title="Chi tiết" href="${baseURL}user/sanpham/${ma_san_pham}">
                                <i class="fas fa-info"></i>
                            </a>
                            <a title="Sửa" href="${baseURL}user/sanpham/${ma_san_pham}/edit">
                                <i class="fas fa-edit"></i>
                            </a>
                        </div>
                    </td>
                </tr>`;
        }).join('');
        sanPhamListDOM.innerHTML = allSanPhams;
        paginationDOM.innerHTML = buildPagination(page, total_page, 10, "changePage");
    }
    catch (error) {
        console.log(error);
    }
}

showSanPhamList();

searchFormDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(searchFormDOM);
    removeURLparam("page");
    changeURLparam("search", formData.get('search'));
    changeURLparam("status", formData.get('status'));
    showSanPhamList();
});