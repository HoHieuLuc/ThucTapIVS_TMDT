const formDOM = document.querySelector('#formDanhGiaSanPham');
const huyDanhGiaBtnDOM = document.querySelector('#huyDanhGiaBtn');
const danhGiaBtnDOM = document.querySelector('#danhGiaBtn');
//Lấy mã sản phẩm trên đường dẫn
const params = window.location.pathname.split('/').slice(0);
const maSanPham = params[params.length - 1];

//Các biến của chi tiết sản phẩm
const nguoiDangSanPham = document.querySelector('#nguoiDangSanPham');
const tenSanPhamDOM = document.querySelector('#tenSanPham');
const danhGiaDOM = document.querySelector('#danhGia');
const moTaSanPhamDOM = document.querySelector('#moTaSanPham');
const giaDOM = document.querySelector('#gia');
const $anhSanPhamDOM = document.querySelector('#anhSanPham');

//Các biến của đánh giá sản phẩm
const danhGiaSPListDom = document.querySelector('#danhGiaSPListDom');
const errorMsg = document.querySelector('#errorMsg');

// inline gallery
const buildInlineGallery = (images) => lightGallery($anhSanPhamDOM, {
    container: $anhSanPhamDOM,
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
        const { tenSanPham, tenKhachHang, moTa, gia, anhSanPhams, xepHang, avatar, username } = sanpham;
        tenSanPhamDOM.innerHTML = tenSanPham;
        danhGiaDOM.innerHTML = xepHang ?? "Chưa có đánh giá";
        moTaSanPhamDOM.innerHTML = moTa;
        giaDOM.innerHTML = gia;
        nguoiDangSanPham.innerHTML = tenKhachHang;
        nguoiDangSanPham.href = `${baseURL}store/${username}`;
        const anhSanPhamData = anhSanPhams.map((anhSanPham) => {
            return {
                src: `${baseURL}images/product/${anhSanPham}`,
                thumb: `${baseURL}images/product/${anhSanPham}`
            }
        });
        buildInlineGallery(anhSanPhamData).openGallery();
        showDanhGiaSPs();

    } catch (error) {
        thongBao(error.response.data.message, true);
    }
}

/* hiện danh sách đánh giá */
const showDanhGiaSPs = async () => {
    try {
        const { data: { danhGiaSPs, danhGiaCuaBan } } = await axios.get(`${baseURL}api/v1/danhgia/sanpham/${maSanPham}`);

        let danhGiaCuaBanHTML = '';
        let danhGiaSPsHTML = '';
        if (danhGiaCuaBan !== undefined) {
            formDOM.style.display = 'none';
            const { ngay_tao, ngay_sua, noi_dung, so_sao, ten, username, avatar } = danhGiaCuaBan;
            document.querySelector('#noiDung').value = noi_dung;
            document.querySelector('#soSao').value = so_sao;
            //in ra icon ngôi sao đánh giá
            let so_sao_html = '';
            for (let i = 0; i < so_sao; i++) {
                so_sao_html += '<span>&#9733;</span>';
            }
            const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : '';
            danhGiaCuaBanHTML = `
                <div class="comment mt-4 text-justify float-left" id="danhGiaCuaToi"> 
                    <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                    <h4>${ten}</h4> <span>${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}  lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p</span>${lanSuaCuoi}
                    <br>
                    ${so_sao_html}
                    <p>${noi_dung}</p>
                    <button class="btn btn-link" onclick="suaDanhGiaSP()">Sửa</button>
                </div>`;
        }
        if (danhGiaSPs.length > 0) {
            const allDanhGiaSPs = danhGiaSPs.map((danhGiaSP) => {
                const { ngay_tao, ngay_sua, noi_dung, so_sao, ten, username, avatar } = danhGiaSP;
                //in ra icon ngôi sao đánh giá
                let so_sao_html = '';
                for (let i = 0; i < so_sao; i++) {
                    so_sao_html += '<span>&#9733;</span>';
                }
                const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : ``;
                return `
                    <div class="comment mt-4 text-justify float-left"> 
                        <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                        <h4>${ten}</h4><span>
                        ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p
                        </span>${lanSuaCuoi}
                        <br>
                        ${so_sao_html}
                        <p>${noi_dung}</p>
                        <button class="btn btn-link" onclick="phanHoiDanhGiaSP()">Phản hồi</button>
                    </div>
                `;
            }).join('');
            danhGiaSPsHTML = allDanhGiaSPs;
        }
        danhGiaSPListDom.innerHTML = danhGiaCuaBanHTML + danhGiaSPsHTML;
        if (danhGiaSPs.length === 0 && danhGiaCuaBan === undefined) {
            danhGiaSPListDom.innerHTML = `<h4>Sản phẩm này chưa có đánh giá</h4>`;
        }
    } catch (error) {
        thongBao(error.response.data.message, true);
    }
}

showSanPhamDetail();

// sửa đánh giá
const suaDanhGiaSP = () => {
    document.querySelector('#danhGiaCuaToi').style.display = 'none';
    formDOM.style.display = 'block';
    huyDanhGiaBtnDOM.style.display = 'block';
    document.querySelector('#noiDung').focus();
    danhGiaBtnDOM.value = "Cập nhật";
}

// Phản hồi đánh giá sản phẩm
const phanHoiDanhGiaSP = () => {
    document.querySelector('#danhGiaCuaToi').style.display = 'none';
    formDOM.style.display = 'block';
    huyDanhGiaBtnDOM.style.display = 'block';
    document.querySelector('#noiDung').focus();
    danhGiaBtnDOM.value = "Phản hồi";
}


const submitDanhGiaSP = async () => {
    const formData = new FormData(formDOM);
    // Phân biệt request link giữa đánh giá sản phẩm và phản hồi đánh giá sản phẩm
    var requestLink = null;
    if (danhGiaBtnDOM.value != "Phản hồi")  requestLink = `${baseURL}api/v1/danhgia/sanpham/submit`;
        else requestLink = `${baseURL}api/v1/phanhoi/sanpham/submit`;
    
    try {
        await axios.post(requestLink, formData, { params: { maSanPham: maSanPham } });
        formDOM.style.display = 'none';
        showSanPhamDetail();
        showDanhGiaSPs();
        thongBao("Gửi đánh giá thành công");
    } catch (error) {
        thongBao(error.response.data.message, true);
    }
}

if (formDOM) {
    formDOM.addEventListener('submit', (event) => {
        event.preventDefault();
        submitDanhGiaSP();
    });
    huyDanhGiaBtnDOM.addEventListener('click', () => {
        formDOM.style.display = 'none';
        danhGiaCuaToi.style.display = 'block';
    });
}