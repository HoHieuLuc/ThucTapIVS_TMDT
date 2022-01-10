const sanPhamListDOM = document.querySelector('#sanpham-list');
const paginationDOM = document.querySelector('#pagination');
const searchFormDOM = document.querySelector('.searchForm');


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
        const allSanPhams = sanphams.map(sanpham => {
            const { ma_san_pham, ten_san_pham, gia, so_luong,
                ngay_dang, so_luong_da_ban, xep_hang
            } = sanpham;
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
                    <td>
                        <div class="d-flex justify-content-evenly">
                            <a href="${baseURL}user/sanpham/${ma_san_pham}" class="">Chi tiết</a>
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