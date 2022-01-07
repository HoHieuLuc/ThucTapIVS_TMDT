document.title = "Admin - Chi tiết sản phẩm ";
//Lấy mã sản phẩm trên đường dẫn
const params = window.location.pathname.split('/').slice(0);
const maSanPham = params[params.length - 1];

//Các biến của chi tiết sản phẩm
const nguoiDangSanPham = document.querySelector('#nguoiDangSanPham');
const tenSanPhamDOM = document.querySelector('#tenSanPham');
const moTaSanPhamDOM = document.querySelector('#moTaSanPham');
const giaDOM = document.querySelector('#gia');
const anhSanPhamDOM = document.querySelector('#anhSanPham');
const statusDOM = document.querySelector('#status');


// inline gallery
const buildInlineGallery = (images) => lightGallery(anhSanPhamDOM, {
    container: anhSanPhamDOM,
    dynamic: true,
    // Turn off hash plugin in case if you are using it
    // as we don't want to change the url on slide change
    hash: false,
    // Do not allow users to close the gallery
    closable: false,
    // Add maximize icon to enlarge the gallery
    showMaximizeIcon: true,
    // Append caption inside the slide item
    // to apply some animation for the captions (Optional)
    appendSubHtmlTo: ".lg-item",
    // Delay slide transition to complete captions animations
    // before navigating to different slides (Optional)
    // You can find caption animation demo on the captions demo page
    plugins: [lgZoom, lgThumbnail],
    dynamicEl: images,
    // Completely optional
    // Adding as the codepen preview is usually smaller
    thumbWidth: 60,
    thumbHeight: "40px",
    thumbMargin: 4,
    download: false,
});

const showSanPhamDetail = async () => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/sanpham/${maSanPham}`);
        // có avatar nữa
        const { tenSanPham, tenKhachHang, moTa, gia, anhSanPhams,username,status } = sanpham;
        tenSanPhamDOM.textContent = tenSanPham;
        moTaSanPhamDOM.textContent = moTa;
        giaDOM.innerHTML = gia;
        nguoiDangSanPham.textContent = tenKhachHang;
        statusDOM.textContent = `${status}`;
        const anhSanPhamData = anhSanPhams.map((anhSanPham) => {
            return {
                src: `${baseURL}images/product/${anhSanPham}`,
                thumb: `${baseURL}images/product/${anhSanPham}`
            }
        });
        buildInlineGallery(anhSanPhamData).openGallery();
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}
showSanPhamDetail();

