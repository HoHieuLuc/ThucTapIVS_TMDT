const listBaoCaoDOM = document.querySelector('#baocao-list');
const searchFormDOM = document.querySelector('.searchForm');
document.title = "Kiểm duyệt báo cáo người dùng";

// Khởi tạo những biến liên quan đến tìm kiếm, lọc thông tin
const init = () => {
    const newParams = window.location.search;
    const search = new URLSearchParams(newParams).get("search") ?? "";
    const status = new URLSearchParams(newParams).get("status") ?? 0;
    searchFormDOM.querySelector('input[name="search"]').value = search;
    const allRadios = searchFormDOM.querySelectorAll('input[name="status"]');
    allRadios.forEach(radio => {
        if (radio.value === status) {
            radio.checked = true;
        }
    });
}

init();

//Render data  api/v1/nhanvien/baocao/{maBaoCao}
const renderData = (datas) => {
    const allBaoCaos = datas.map(data => {
        const { unameReceiver, unameSender, ngay_tao, ma_bao_cao } = data;
        return `
            <tr>
                <td>${unameSender}</td>
                <td><a href="${baseURL}store/${unameReceiver}">${unameReceiver}</a></td>
                <td>${ngay_tao}</td>
                <td class="noExl">
                    <div class="d-flex justify-content-evenly">
                        <a href="${baseURL}admin/kiemduyet/baocao/${ma_bao_cao}">Chi tiết</a>
                    </div>
                </td>
            </tr>`;
    }).join('');
    listBaoCaoDOM.innerHTML = allBaoCaos;
}

//Show list báo cáo lần đầu tiên
const showListBaoCao = async () => {
    try {
        const newParams = window.location.search;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const status = new URLSearchParams(newParams).get("status") ?? 0;
        let tinh_trang = "";
        if (status == -2) {
            tinh_trang = "Vi phạm nặng nhất";
        }
        else if (status == -1) {
            tinh_trang = "Cảnh cáo";
        } else if (status == 0) {
            tinh_trang = "Chưa duyệt";
        } else if (status == 1) {
            tinh_trang = "Nhắc nhở";
        } else if (status == 2) {
            tinh_trang = "Không vi phạm";
        }
        //Thêm tên trạng thái vào thuộc tính filename trong thẻ table..
        document.querySelector('.tlt-fixed-table').setAttribute("filename", `Danh sách báo cáo (${tinh_trang})`);
        const {
            data: { list_baocaos }
        } = await axios.get(`${baseURL}api/v1/admin/baocao/getbystatus/${status}`, {
            params: {
                search
            }
        });
        renderData(list_baocaos);
    } catch (error) {
        console.log(error);
    }
}

showListBaoCao();

searchFormDOM.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(searchFormDOM);
    const status = formData.get('status');
    const search = formData.get('search');
    changeURLparam('status', status);
    changeURLparam('search', search);
    showListBaoCao();
});