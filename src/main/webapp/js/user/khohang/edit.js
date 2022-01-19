const params = window.location.href.split("/").slice(0);
const maSanPham = params.at(-2);

const editSanPhamFormDOM = document.querySelector('#editSanPhamForm');
const tenSanPhamDOM = editSanPhamFormDOM.querySelector('input[name="tenSanPham"]');
const moTaDOM = editSanPhamFormDOM.querySelector('textarea[name="moTa"]');
const giaDOM = editSanPhamFormDOM.querySelector('input[name="gia"]');
const soLuongDOM = editSanPhamFormDOM.querySelector('input[name="soLuong"]');

let ckEditor;
ClassicEditor
    .create(moTaDOM, {
        removePlugins: [
            'CKFinderUploadAdapter', 'CKFinder', 'EasyImage', 'Image', 'ImageCaption',
            'ImageStyle', 'ImageToolbar', 'ImageUpload', 'MediaEmbed'
        ],
    })
    .then(editor => {
        ckEditor = editor;
    })
    .catch(error => {
        console.error(error);
    });

const getSanPham = async () => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/user/sanpham/${maSanPham}`);
        const { ten_san_pham, gia, so_luong, mo_ta } = sanpham;
        tenSanPhamDOM.value = ten_san_pham;
        ckEditor.setData(mo_ta);
        giaDOM.value = gia;
        soLuongDOM.value = so_luong;
    } catch (error) {
        console.log(error);
    }
}

getSanPham();

editSanPhamFormDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    const data = new FormData(editSanPhamFormDOM);
    try {
        const { data: { message } } = await axios.post(`${baseURL}api/v1/user/sanpham/${maSanPham}/edit`, data);
        thongBao(message);
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
});