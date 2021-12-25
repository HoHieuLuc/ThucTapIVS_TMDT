const formDOM = document.querySelector("#them-san-pham-form");
const maLoaiSanPhamDOM = document.querySelector("#ma-loai-san-pham");

const getLoaiSanPham = async () => {
    try {
        const { data: { loaiSanPhams } } = await axios.get(`${baseURL}api/v1/loaisanpham`);
        const options = buildOptions(loaiSanPhams, "maLoaiSanPham", "tenLoaiSanPham");
        maLoaiSanPhamDOM.innerHTML = options;
    } catch (error) {
        console.log(error);
    }
}

getLoaiSanPham();

const upload = async () => {
    const formData = new FormData(formDOM);
    try {
        await axios.post(`${baseURL}api/v1/user/sanpham/create`, formData);
    } catch (error) {
        console.log(error);
    }
}

formDOM.addEventListener("submit", (event) => {
    event.preventDefault();
    upload();
});