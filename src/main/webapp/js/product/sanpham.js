const formDanhGiaDOM = document.querySelector('#formDanhGiaSanPham');
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
const anhSanPhamDOM = document.querySelector('#anhSanPham');
const loaiSanPhamDOM = document.querySelector('#loaiSanPham');
const addToCartBtnDOM = document.querySelector('#addToCartBtn');
const addToFavBtnDOM = document.querySelector('#addToFavBtn');

//Các biến của đánh giá sản phẩm
const danhGiaSPListDom = document.querySelector('#danhGiaSPListDom');
const errorMsg = document.querySelector('#errorMsg');

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

const showSanPhamDetail = async (skip = false) => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/sanpham/${maSanPham}`);
        // có avatar nữa
        const { ten_san_pham, ten, ten_loai_sp, ma_loai_sp, mo_ta,
            gia, anhSanPhams, xep_hang, username
        } = sanpham;
        danhGiaDOM.textContent = xep_hang ?? "Chưa có đánh giá";
        if (skip) {
            return;
        }
        addToCartBtnDOM.dataset.masanpham = maSanPham;
        addToFavBtnDOM.dataset.masanpham = maSanPham;
        tenSanPhamDOM.textContent = ten_san_pham;
        loaiSanPhamDOM.href = `${baseURL}category/${ma_loai_sp}`;
        loaiSanPhamDOM.textContent = ten_loai_sp;
        moTaSanPhamDOM.textContent = mo_ta;
        giaDOM.innerHTML = gia.toLocaleString("vi-VN", {
            style: "currency",
            currency: "VND",
        });
        nguoiDangSanPham.textContent = ten;
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
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}
//Check đã đăng nhập hay chưa 
const loginCheckFunction = async () => {
    try {
        await axios.get(`${baseURL}checkCustomer`);
        return true;
    }
    catch (error) {
        console.log(error.response.data.message);
        return false;
    }
}

/* hiện danh sách đánh giá */
const showDanhGiaSPs = async () => {
    try {
        const isLogin = await loginCheckFunction();
        const { data: { danhGiaSPs, danhGiaCuaBan } } = await axios.get(`${baseURL}api/v1/danhgia/sanpham/${maSanPham}`);

        let danhGiaCuaBanHTML = '';
        let danhGiaSPsHTML = '';
        // nếu đã đánh giá thì sẽ ưu tiên hiển thị đánh giá của người dùng lên đầu
        if (danhGiaCuaBan !== undefined) {
            formDanhGiaDOM.style.display = 'none';
            const { ma_danh_gia, so_phan_hoi, ngay_tao, ngay_sua, noi_dung, so_sao, ten, username, avatar } = danhGiaCuaBan;
            document.querySelector('#noiDung').value = noi_dung;
            document.querySelector('#soSao').value = so_sao;
            //in ra icon ngôi sao đánh giá
            let so_sao_html = '<span>';
            for (let i = 0; i < so_sao; i++) {
                so_sao_html += '&#9733; ';
            }
            so_sao_html += '</span>';

            const formPhanHoiElement = `${buildFormPhanHoi(ma_danh_gia, isLogin)}`;
            //Nếu số phản hồi lớn hơn 0 thì hiển thị ra nút xem phản hồi
            let phanHoiElement = `
                <button 
                    class="an-phan-hoi btn btn-link m-2 d-none" style="text-decoration: none;"
                    data-ma_danh_gia = "${ma_danh_gia}"
                >
                    Ẩn phản hồi
                </button>
            `;
            if (so_phan_hoi > 0) {
                phanHoiElement = `
                    <button 
                        class="xem-phan-hoi btn btn-link m-2" style="text-decoration: none;"
                        data-ma_danh_gia = "${ma_danh_gia}"
                    >
                        Xem phản hồi
                    </button>
                `;
            }

            const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : '';
            danhGiaCuaBanHTML = `
                <div class="mt-4" id="danhGiaCuaToi"> 
                    <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                    <a href="${baseURL}store/${username}" class="fs-5 text-dark text-decoration-none">${ten}</a>
                    <div>
                        <span>
                            ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} 
                            lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p
                        </span>
                        ${lanSuaCuoi}
                    </div>
                    ${so_sao_html}
                    <p class="tlt-comment">${noi_dung}</p>
                    <button class="sua-danh-gia-btn btn btn-link text-decoration-none">Sửa</button>
                    ${phanHoiElement}
                    ${formPhanHoiElement}
                </div>
            `;
        }
        if (danhGiaSPs.length > 0) {
            const allDanhGiaSPs = danhGiaSPs.map((danhGiaSP) => {
                const { ma_danh_gia, ngay_tao, ngay_sua, noi_dung, so_sao, ten, username, avatar, so_phan_hoi } = danhGiaSP;
                //in ra icon ngôi sao đánh giá
                let so_sao_html = '<span>';
                for (let i = 0; i < so_sao; i++) {
                    so_sao_html += '&#9733; ';
                }
                so_sao_html += '</span>';

                // Kiểm tra nếu chưa đăng nhập, không hiện nút phản hồi
                // Chỉ khi đăng  nhập rồi thì mới hiện form
                const formPhanHoiElement = `${buildFormPhanHoi(ma_danh_gia, isLogin)}`;

                let phanHoiElement = `
                    <button 
                        class="an-phan-hoi btn btn-link m-2 d-none" style="text-decoration: none;"
                        data-ma_danh_gia = "${ma_danh_gia}"
                    >
                        Ẩn phản hồi
                    </button>
                `;
                // có phản hồi thì hiện nút xem phản hồi
                if (so_phan_hoi > 0) {
                    phanHoiElement = `
                        <button 
                            class="xem-phan-hoi btn btn-link m-2" style="text-decoration: none;"
                            data-ma_danh_gia = "${ma_danh_gia}"
                        >
                            Xem phản hồi
                        </button>
                    `;
                }

                const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : ``;
                return `
                    <div class="mt-4"> 
                        <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                        <a href="${baseURL}store/${username}" class="fs-5 text-dark text-decoration-none">${ten}</a>
                        <div>
                            <span>
                                ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} 
                                lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p
                            </span>
                                ${lanSuaCuoi}
                        </div>
                        ${so_sao_html}
                        <p class="tlt-comment">${noi_dung}</p>
                        ${phanHoiElement}
                        ${formPhanHoiElement}
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
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

showSanPhamDetail();

// hiển thị form sửa đánh giá
const suaDanhGiaSP = () => {
    document.querySelector('#danhGiaCuaToi').style.display = 'none';
    formDanhGiaDOM.style.display = 'block';
    huyDanhGiaBtnDOM.classList.remove('d-none');
    huyDanhGiaBtnDOM.classList.add('d-block');
    document.querySelector('#noiDung').focus();
    danhGiaBtnDOM.value = "Cập nhật";
}

const submitDanhGiaSP = async () => {
    const formData = new FormData(formDanhGiaDOM);
    try {
        await axios.post(`${baseURL}api/v1/danhgia/sanpham/submit`, formData, { params: { maSanPham: maSanPham } });
        formDanhGiaDOM.style.display = 'none';
        showSanPhamDetail(true);
        showDanhGiaSPs();
        thongBao("Gửi đánh giá thành công");
    } catch (error) {
        thongBao(error.response.data.message, true);
    }
}

if (formDanhGiaDOM) {
    formDanhGiaDOM.addEventListener('submit', (event) => {
        event.preventDefault();
        submitDanhGiaSP();
    });
    huyDanhGiaBtnDOM.addEventListener('click', () => {
        formDanhGiaDOM.style.display = 'none';
        danhGiaCuaToi.style.display = 'block';
    });
}

// trả về chuỗi html của form phản hồi
const buildFormPhanHoi = (ma_danh_gia, isLogin) => {
    if (isLogin) {
        return `
        <button class="phan-hoi-btn btn btn-link text-decoration-none">Phản hồi</button>
        <form style="display: none;">
            <textarea type="text" name="noiDung" class="form-control mb-1" placeholder="Nhập nội dung phản hồi"></textarea>
            <div class="d-flex gap-2">
                <button type="button" class="huy-phan-hoi w-100 btn btn-block btn-outline-danger">
                    Hủy
                </button>
                <button 
                    type="button" class="submit-phan-hoi w-100 btn btn-block btn-outline-success" 
                    data-ma_danh_gia = "${ma_danh_gia}"
                >Gửi phản hồi</button>
            </div>
        </form>
        `;
    }
    else {
        return '';
    }
}



//Gửi phản hồi đánh giá sp
const submitPhanHoiDanhGiaSP = async (_formDOM, ma_danh_gia) => {
    //Lấy dữ liệu từ chính cái form mà người dùng đang nhập
    //Form đó đã có noiDung
    const formDanhGiaSanPham = new FormData(_formDOM);
    //Gửi dữ liệu vào request
    try {
        await axios.post(`${baseURL}api/v1/phanhoi/sanpham/submit`, formDanhGiaSanPham, {
            params: { maDanhGia: ma_danh_gia }
        });
        formDanhGiaDOM.style.display = 'none';
        thongBao(`Gửi phản hồi thành công`);
        return true;
    } catch (error) {
        thongBao(error.response.data.message, true);
        return false;
    }
}

// build list phản hồi sẽ return chuỗi html
const buildListPhanHoi = async (ma_danh_gia) => {
    try {
        const { data: { phanHoiDGSPs } } = await axios.get(`${baseURL}api/v1/phanhoi/${ma_danh_gia}`);
        return phanHoiDGSPs.map((phanHoiDGSP) => {
            const { username, avatar, ten, ngay_tao, ngay_sua, noi_dung } = phanHoiDGSP;
            const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : ``;
            return `
                <div class="m-4"> 
                    <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                    <a href="${baseURL}store/${username}" class="fs-5 text-dark text-decoration-none">${ten}</a>
                    <div>
                        <span>
                            ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} 
                            lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p
                        </span>
                        ${lanSuaCuoi}
                    </div>
                    <p class="tlt-comment">${noi_dung}</p>
                </div>
            `;
        }).join('');
    }
    catch (error) {
        console.log(error);
        return '';
    }
}

danhGiaSPListDom.addEventListener('click', async (event) => {
    const el = event.target;
    const parentNode = event.target.parentNode; // => thẻ cha chứa thẻ mình bấm
    if (el.classList.contains('sua-danh-gia-btn')) {
        suaDanhGiaSP();
        return;
    }
    if (el.classList.contains('xem-phan-hoi')) { // ở đây là thẻ cha của button xem phản hồi
        const ma_danh_gia = el.dataset.ma_danh_gia;
        // xóa class đó ra khỏi button và thêm class an-phan-hoi
        // để khi người dùng nhấn lại nút đó thì sẽ ẩn phản hồi
        el.innerHTML = 'Ẩn phản hồi';
        el.classList.remove('xem-phan-hoi');
        el.classList.add('an-phan-hoi');
        // lấy chuỗi html của phản hồi
        const html = await buildListPhanHoi(ma_danh_gia);
        // tạo 1 div mới để chứa phản hồi
        const div = document.createElement('div');
        div.className = 'div-phan-hoi border-start border-1';
        div.innerHTML = html;
        // thêm phản hồi vào thẻ cha của button (các cái div mà mình build ở showDanhGiaSPs)
        parentNode.appendChild(div);
        return;
    }
    if (el.classList.contains('an-phan-hoi')) {
        el.innerHTML = 'Xem phản hồi';
        el.classList.remove('an-phan-hoi');
        el.classList.add('xem-phan-hoi');
        parentNode.removeChild(parentNode.querySelector('.div-phan-hoi'));
        return;
    }
    if (el.classList.contains('phan-hoi-btn')) {
        // nextElementSibling là thẻ form sau thẻ button
        el.nextElementSibling.style.display = 'block';
        return;
    }
    if (el.classList.contains('huy-phan-hoi')) {
        parentNode.parentNode.style.display = 'none'; // là cái form phản hồi
        return;
    }
    if (el.classList.contains('submit-phan-hoi')) {
        const formNode = parentNode.parentNode; // là cái form phản hồi do cái button nằm trong 1 cái div nữa
        const ma_danh_gia = el.dataset.ma_danh_gia;
        const kiemTraFormPhanHoi = await submitPhanHoiDanhGiaSP(formNode, ma_danh_gia);
        if (!kiemTraFormPhanHoi) {
            return;
        }
        formNode.style.display = 'none';
        // xử lý hiện thị phản hồi vừa tạo
        const html = await buildListPhanHoi(ma_danh_gia); // fetch lại tất cả phản hồi
        // thẻ cha của thẻ form là thẻ div chứa cả đánh giá và phản hồi
        // div chứa cái đánh giá và phản hồi
        const danhGiaDiv = formNode.parentNode;
        const phanHoiDiv = danhGiaDiv.querySelector('.div-phan-hoi');
        // nếu div phản hồi đã được mở (ấn nút xem phản hồi thì cái câu ở trên mới không bị null)
        // thì xóa cái div phản hồi đó đi
        if (phanHoiDiv) {
            danhGiaDiv.removeChild(phanHoiDiv);
        }
        // tạo lại thẻ con mới
        const div = document.createElement('div');
        div.className = 'div-phan-hoi border-start border-1';
        div.innerHTML = html;
        danhGiaDiv.appendChild(div);
        // khi submit thành công sẽ hiện nút ẩn phản hồi
        const anPhanHoiBtn = danhGiaDiv.querySelector('.an-phan-hoi');
        const xemPhanHoiBtn = danhGiaDiv.querySelector('.xem-phan-hoi');
        if (anPhanHoiBtn) {
            console.log(anPhanHoiBtn);
            anPhanHoiBtn.classList.remove('d-none');
        }
        if (xemPhanHoiBtn) {
            console.log(xemPhanHoiBtn);
            xemPhanHoiBtn.classList.remove('xem-phan-hoi');
            xemPhanHoiBtn.classList.add('an-phan-hoi');
            xemPhanHoiBtn.innerHTML = 'Ẩn phản hồi';
        }
    }
});
