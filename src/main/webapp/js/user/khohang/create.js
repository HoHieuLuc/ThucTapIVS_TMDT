const formDOM = document.querySelector("#them-san-pham-form");
const loaiSanPhamDOM = document.querySelector("#loaiSanPham");

const buildLoaiSanPham = async (maLoai) => {
    try {
        const { data: { loaiSanPhams } } = await axios.get(`${baseURL}api/v1/subcategory/${maLoai}`);
        if (loaiSanPhams.length === 0) {
            return '';
        }
        const options = buildOptions(loaiSanPhams, "ma_loai_sp", "ten_loai_sp", 'Chọn loại sản phẩm');
        return `
            <select name="maLoaiSanPham" class="form-select rounded">
                ${options}
            </select>
        `;

    } catch (error) {
        console.log(error);
        return '';
    }
}

const getLoaiSanPham = async (maLoai) => {
    try {
        const loaiSanPhamHtml = await buildLoaiSanPham(maLoai);
        const newSelectDiv = document.createElement('div');
        newSelectDiv.className = 'divLoaiSanPham col-4';
        newSelectDiv.innerHTML = loaiSanPhamHtml;
        if (loaiSanPhamHtml !== '') {
            loaiSanPhamDOM.appendChild(newSelectDiv);
        }
    } catch (error) {
        console.log(error);
    }
}

getLoaiSanPham(0);

const themSanPham = async () => {
    const formData = new FormData(formDOM);
    formData.set('maLoaiSanPham', formData.getAll('maLoaiSanPham').at(-1));
    try {
        await axios.post(`${baseURL}api/v1/user/sanpham/them`, formData);
        alert('Thêm sản phẩm thành công');
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

formDOM.addEventListener("submit", (event) => {
    event.preventDefault();
    themSanPham();
});

loaiSanPhamDOM.addEventListener('change', async (event) => {
    const eventTarget = event.target;
    const maLoai = eventTarget.value;
    const parentNode = eventTarget.parentNode;
    // xóa các thẻ divLoaiSanPham nằm sau nó
    while (true) {
        const nextNode = parentNode.nextElementSibling;
        if (nextNode) {
            if (nextNode.classList.contains('divLoaiSanPham')) {
                nextNode.remove();
            }
        }
        else {
            break;
        }
    }
    await getLoaiSanPham(maLoai);
});