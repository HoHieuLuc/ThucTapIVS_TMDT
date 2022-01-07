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

//Tạo chức năng tùy theo trạng Thái
const chucNangDOM = document.querySelector('#chucNang');


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
        //if status==0 statusDOM.textContent = `${status}`;
        switch (status) {
            case -1:
                statusDOM.textContent = `Bị Xóa (Ẩn)`;
                chucNangDOM.innerHTML =`
                    <button type="button" class="btn btn-primary" data-status="0">Phục hồi vào kho</button>
                    <button type="button" class="btn btn-success" data-status="2">Duyệt</button></div>
                `;
                break;
            case 0:
                statusDOM.textContent = `Trong Kho`;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-danger" data-status="-1">Xóa</button>
                <button type="button" class="btn btn-success" data-status="2">Duyệt</button></div>
            `;
                break;
            case 1:
                statusDOM.textContent = `Yêu Cầu Duyệt `;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-danger" data-status="-1">Xóa</button>
                <button type="button" class="btn btn-success" data-status="2">Duyệt</button></div>
            `;
                break;
            case 2:
                statusDOM.textContent = `Đã duyệt`;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-danger" data-status="-1">Xóa</button>
                <button type="button" class="btn btn-primary" data-status="0">Đưa vào kho</button></div>
            `;
                break;
        }
        
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


//Bắt sự kiện của từng nút để duyệt
chucNangDOM.addEventListener('click',async(event)=>{
    const target = event.target;
    const formData = new FormData();
    formData.append('maSanPham',maSanPham);
    console.log(target.dataset);
    formData.append('status',target.dataset.status);
    try {
        await axios.post(`${baseURL}api/v1/nhanvien/sanpham/changestatus`,formData)
    }
    catch (error) {
        console.log(error)
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true)
    }
    //Reload lại data chi tiết sản phẩm
    showSanPhamDetail();
})
