//Đổi tiêu đề trang web
document.title = "Trang phê duyệt sản phẩm "

const searchFormDOM = document.querySelector(".searchForm");

const init = () => {
    const newParams = window.location.search;
    const search = new URLSearchParams(newParams).get("search") ?? "";
    const _status = new URLSearchParams(newParams).get("status") ?? 0;
    searchFormDOM.querySelector("input[name='search']").value = search;
    searchFormDOM.querySelector("select[name='status']").value = _status;
}

//Nút hiển thị sản phẩm mới vô kho,yêu cầu kiểm duyệt,đã duyệt, và bị xóa (hide)
const listSPByStatusDOM = document.querySelector("#listSPByStatus");
//Khu vực danh sách sản phẩm thẻ <div>
const listSanPhamDOM = document.querySelector("#listSanPham");
const phanTrangSanPhamDOM = document.querySelector("#phanTrangSanPham");
let globalData = [];

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
    const allSanPhams = datas.map(data => {
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
                <td>
                    <div class="d-flex justify-content-evenly">
                         <a href="${baseURL}admin/sanpham/${ma_san_pham}"  class="noExl">Chi tiết</a>
                    </div>
                </td>
            </tr>`;
    }).join('');
    listSanPhamDOM.innerHTML = allSanPhams;
    if (currentPage !== undefined && totalPage !== undefined) {
        phanTrangSanPhamDOM.innerHTML = buildPagination(currentPage, totalPage, 5, "changePage");
    }
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
    globalData = sanphams;
    renderData(sanphams, total_page, page);
    let tinh_trang = "";
    if (status == -1) {
        tinh_trang = "Bị xóa";
    }
    else if (status == 0) {
        tinh_trang = "Trong kho";
    } else if (status == 1) {
        tinh_trang = "Đang chờ duyệt";
    } else if (status == 2) {
        tinh_trang = "Đang bán";
    } 
    //Thêm tên trạng thái vào thuộc tính filename trong thẻ table..
    document.querySelector('.tlt-fixed-table').setAttribute("filename", `Danh sách kiểm duyệt sản phẩm (${tinh_trang})`);
}
showSanPhams();

searchFormDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(searchFormDOM);
    removeURLparam("page");
    changeURLparam("search", formData.get("search"));
    showSanPhams();
});

const sortTheoTen = (a, b) => {
    const nameA = a.ten_san_pham.toUpperCase().normalize('NFKD').replace(/[^\w\s.-_\/]/g, '');
    const nameB = b.ten_san_pham.toUpperCase().normalize('NFKD').replace(/[^\w\s.-_\/]/g, '');
    return nameA.localeCompare(nameB);
}

const sortTheoGia = (a, b) => {
    return a.gia - b.gia;
}

const mainTableDOM = document.querySelector(".tlt-fixed-table");

mainTableDOM.addEventListener('click', (event) => {
    const eventTarget = event.target;
    if (!eventTarget.classList.contains("sort-btn")) {
        return;
    }
    const icon = eventTarget.querySelector(".sort-icon");
    if (icon !== null && icon.classList.contains("fa-angle-up")) {
        icon.classList.remove("fa-angle-up");
        icon.classList.add("fa-angle-down");
    } else if (icon !== null && icon.classList.contains("fa-angle-down")) {
        icon.classList.remove("fa-angle-down");
        icon.classList.add("fa-angle-up");
    }
    if (eventTarget.classList.contains("sort-ten-asc")) {
        globalData.sort(sortTheoTen);
        renderData(globalData);
        eventTarget.classList.remove("sort-ten-asc");
        eventTarget.classList.add("sort-ten-desc");
        return;
    }
    if (eventTarget.classList.contains("sort-ten-desc")) {
        globalData.sort(sortTheoTen).reverse();
        renderData(globalData);
        eventTarget.classList.remove("sort-ten-desc");
        eventTarget.classList.add("sort-ten-asc");
        return;
    }
    if (eventTarget.classList.contains("sort-gia-asc")) {
        globalData.sort(sortTheoGia);
        renderData(globalData);
        eventTarget.classList.remove("sort-gia-asc");
        eventTarget.classList.add("sort-gia-desc");
        return;
    }
    if (eventTarget.classList.contains("sort-gia-desc")) {
        globalData.sort(sortTheoGia).reverse();
        renderData(globalData);
        eventTarget.classList.remove("sort-gia-desc");
        eventTarget.classList.add("sort-gia-asc");
    }
});