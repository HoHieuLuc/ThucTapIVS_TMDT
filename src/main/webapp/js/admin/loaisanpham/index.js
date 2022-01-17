const searchFormDOM = document.querySelector(".searchForm");
const listLoaiSanPhamDOM = document.querySelector('#listLoaiSanPham');
const phanTrangDOM = document.querySelector('#phanTrang');

const init = () => {
    const newParams = window.location.search;
    const search = new URLSearchParams(newParams).get("search") ?? "";
    searchFormDOM.querySelector('input[name="search"]').value = search;
}

init();

const changePage = (page) => {
    changeURLparam("page", page);
    showLoaiSanPham();
};

const showLoaiSanPham = async () => {
    try {
        const newParams = window.location.search;
        const page = new URLSearchParams(newParams).get("page") ?? 1;
        const search = new URLSearchParams(newParams).get("search") ?? "";
        const { data: { loaiSanPhams, totalPages } } = await axios.get(`${baseURL}api/v1/category`, {
            params: {
                page,
                search
            }
        });
        const allLoaiSanPham = loaiSanPhams.map((loaiSanPham) => {
            const { ma_loai_sp, ten_loai_sp, ma_loai_sp_cha, ten_loai_sp_cha } = loaiSanPham;
            const loaiSPCha = ma_loai_sp_cha
                ? `<a href="${baseURL}category/${ma_loai_sp_cha}"><i class="fas fa-external-link-alt"></i></a>
                    ${ten_loai_sp_cha}`
                : 'Không';
            return `
                <tr>
                    <td>
                        <a href="${baseURL}category/${ma_loai_sp}"><i class="fas fa-external-link-alt"></i></a>
                        ${ten_loai_sp}
                    </td>
                    <td>${loaiSPCha}</td>
                </tr>
            `;
        }).join('');
        listLoaiSanPhamDOM.innerHTML = allLoaiSanPham;
        phanTrangDOM.innerHTML = buildPagination(page, totalPages, 5, "changePage");
    } catch (error) {
        console.log(error);
    }
}

showLoaiSanPham();

searchFormDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(searchFormDOM);
    removeURLparam("page");
    changeURLparam("search", formData.get("search"));
    showLoaiSanPham();
});