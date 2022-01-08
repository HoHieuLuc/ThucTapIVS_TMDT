const formDOM = document.getElementById('formThemLoaiSP');
const loaiSanPhamDOM = document.querySelector("#loaiSanPham");

const buildLoaiSanPham = async (maLoai) => {
    try {
        const { data: { loaiSanPhams } } = await axios.get(`${baseURL}api/v1/loaisanpham/${maLoai}`);
        if (loaiSanPhams.length === 0) {
            return '';
        }
        const options = buildOptions(loaiSanPhams, "ma_loai_sp", "ten_loai_sp", 'Không');
        return `
            <select name="maLoaiCha" class="form-select rounded">
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

const themLoaiSanPham = async () => {
    const formData = new FormData(formDOM);
    let maLoaiCha = formData.getAll('maLoaiCha');
    // khi không chọn mã loại cha nào
    if (maLoaiCha.length === 0) {
        maLoaiCha = 0;
    }
    else{
        maLoaiCha = formData.getAll('maLoaiCha').at(-1);
    }
    formData.set('maLoaiCha', maLoaiCha);
    try {
        await axios.post(`${baseURL}api/v1/admin/loaisanpham/them`, formData);
        thongBao("Thêm loại sản phẩm thành công");
        setInterval(() => {
            window.location.reload();
        }, 1000);
    } catch (error) {
        console.log(error);
        const { data: { message } } = error.response;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
}

formDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    themLoaiSanPham();
});