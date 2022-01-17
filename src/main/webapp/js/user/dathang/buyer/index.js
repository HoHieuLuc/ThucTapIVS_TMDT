const searchFormDOM = document.querySelector('.searchForm');
const datHangListDOM = document.querySelector('#datHangList');
const paginationDOM = document.querySelector('#pagination');

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
    showDatHangList();
};

const showDatHangList = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get("page") ?? 1;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const _status = new URLSearchParams(newParams).get("status") ?? 0;
        const { data: {
            danhSachDatHang, totalPages
        } } = await axios.get(`${baseURL}api/v1/user/buyer/dathang`, {
            params: {
                page,
                search,
                status: _status
            }
        });
        let tinh_trang = "";
        if (_status == -1) {
            tinh_trang = "Bị hủy";
        }
        else if (_status == 0) {
            tinh_trang = "Đang chờ";
        } else if (_status == 1) {
            tinh_trang = "Đang vận chuyển";
        } else if (_status == 2) {
            tinh_trang = "Đã nhận hàng";
        }
        //Thêm tên trạng thái vào thuộc tính filename trong thẻ table..
        document.querySelector('.tlt-fixed-table').setAttribute("filename",`Danh sách đơn hàng bạn được đặt (${tinh_trang})`);

        const allDatHangs = danhSachDatHang.map((datHang) => {
            const { ma_dat_hang, ten_san_pham, ma_san_pham, ngay_dat,
                tong_tien, so_luong, ten
            } = datHang;
            const giaVND = tong_tien.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <tr>
                    <td title="${ten_san_pham}">${ten_san_pham}</td>
                    <td title="${ten}">${ten}</td>
                    <td>${ngay_dat}</td>
                    <td>${so_luong}</td>
                    <td>${giaVND}</td>
                    <td>${tinh_trang}</td>
                    <td>
                        <div class="d-flex justify-content-evenly">
                            <a  class="noExl" href="${baseURL}user/buyer/dathang/${ma_dat_hang}/${ma_san_pham}">Chi tiết</a>
                        </div>
                    </td>
                </tr>`;
        }).join('');
        datHangListDOM.innerHTML = allDatHangs;
        paginationDOM.innerHTML = buildPagination(page, totalPages, 10, "changePage");
    } catch (error) {
        console.log(error);
    }
}
showDatHangList();

searchFormDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(searchFormDOM);
    removeURLparam("page");
    changeURLparam("search", formData.get('search'));
    changeURLparam("status", formData.get('status'));
    showDatHangList();
});