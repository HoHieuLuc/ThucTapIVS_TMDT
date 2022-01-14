const formDanhGiaDOM = document.querySelector('#formDanhGiaSanPham');
const huyDanhGiaBtnDOM = document.querySelector('#huyDanhGiaBtn');
const danhGiaBtnDOM = document.querySelector('#danhGiaBtn');
const sapXepDanhGiaDOM = document.querySelector('#sapXepDanhGia');
//Lấy mã sản phẩm trên đường dẫn
const params = window.location.pathname.split('/').slice(0);
const maSanPham = params.at(-1);

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

//
class SanPhamVuaXem {
    constructor(_maSanPham, _tenSanPham, _donGia, _hinhAnh) {
        this.maSanPham = _maSanPham;
        this.tenSanPham = _tenSanPham;
        this.donGia = _donGia;
        this.hinhAnh = _hinhAnh;
    }
}

//Các biến của đánh giá sản phẩm
const danhGiaSPListDOM = document.querySelector('#danhGiaSPListDom');
const phanTrangDanhGiaDOM = document.querySelector('#phanTrangDanhGia');

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
            gia, anhSanPhams, xep_hang, username, ma_loai_cha
        } = sanpham;
        if (typeof xep_hang === 'number') {
            danhGiaDOM.innerHTML = `<span>${(Math.round(xep_hang * 10) / 10) + " &#9733;"}</span>`;
        } else {
            danhGiaDOM.innerHTML = `<span>Chưa có đánh giá</span>`;
        }
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
        // danh sách sản phẩm vừa xem lưu trong localstorage
        if (formDanhGiaDOM || document.querySelector('#loginDanhGia')) {
            luuSanPhamVuaXem(ten_san_pham, gia, anhSanPhams[0]);
        }
        nguoiDangSanPham.textContent = ten;
        nguoiDangSanPham.href = `${baseURL}store/${username}`;
        const anhSanPhamData = anhSanPhams.map((anhSanPham) => {
            return {
                src: `${baseURL}images/product/${anhSanPham}`,
                thumb: `${baseURL}images/product/${anhSanPham}`
            }
        });
        buildInlineGallery(anhSanPhamData).openGallery();
        showDanhGiaSPs(1);
        getSanPhamCuaShop(username, maSanPham, ma_loai_sp, ma_loai_cha);
        getSanPhamGoiY(maSanPham);
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

const changePageDanhGia = async (page) => {
    await showDanhGiaSPs(page);
    danhGiaSPListDOM.scrollIntoView();
}

sapXepDanhGiaDOM.addEventListener('change', async () => {
    const orderBy = sapXepDanhGiaDOM.value.split('-')[0];
    const order = sapXepDanhGiaDOM.value.split('-')[1];
    await showDanhGiaSPs(1, order, orderBy);
});

/* hiện danh sách đánh giá */
const showDanhGiaSPs = async (page, order = '', orderBy = '') => {
    try {
        const { data: {
            danhGiaSPs, daDanhGia, totalPages
        } } = await axios.get(`${baseURL}api/v1/danhgia/sanpham/${maSanPham}`, {
            params: {
                page,
                order,
                orderBy
            }
        });
        if (daDanhGia !== undefined && formDanhGiaDOM) {
            if (!daDanhGia) {
                formDanhGiaDOM.classList.remove('d-none');
            }
            else {
                formDanhGiaDOM.classList.add('d-none');
            }
        }
        if (danhGiaSPs.length === 0) {
            danhGiaSPListDOM.innerHTML = `<h4>Sản phẩm này chưa có đánh giá</h4>`;
            return;
        }
        let danhGiaSPsHTML = '';
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
                const formPhanHoiElement = `${buildFormPhanHoi(ma_danh_gia)}`;

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
                const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua})</span>` : ``;
                return `
                    <div class="danh-gia-div mt-4" data-so_sao="${so_sao}" data-noi_dung="${noi_dung}"> 
                        <div class="d-flex">
                            <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                            <a href="${baseURL}store/${username}" class="ms-1 fs-5 text-dark text-decoration-none">${ten}</a>
                            <div class="ms-auto">
                                <div class="btn-group">
                                    <button type="button" 
                                        class="action-btn btn rounded-circle bg-light"
                                        data-bs-toggle="dropdown" 
                                        data-ma_danh_gia="${ma_danh_gia}"
                                        aria-expanded="false"
                                    >
                                        <i class="fas fa-ellipsis-h"></i>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li class="text-center">
                                            <div class="spinner-border text-primary" role="status">
                                                <span class="visually-hidden">Loading...</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div>
                            <span>
                                ${ngay_tao}
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
        danhGiaSPListDOM.innerHTML = danhGiaSPsHTML;
        phanTrangDanhGiaDOM.innerHTML = buildPagination(
            page,
            totalPages,
            10,
            "changePageDanhGia",
        );
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

showSanPhamDetail();

// hiển thị form sửa đánh giá
const suaDanhGiaSP = (noiDung, soSao) => {
    danhGiaSPListDOM.querySelector('.danh-gia-div').classList.add('d-none');
    formDanhGiaDOM.classList.remove('d-none');
    formDanhGiaDOM.querySelector('textarea').value = noiDung;
    formDanhGiaDOM.querySelector('select').value = soSao;
    huyDanhGiaBtnDOM.classList.remove('d-none');
    huyDanhGiaBtnDOM.classList.add('d-block');
    formDanhGiaDOM.querySelector('textarea').focus();
    danhGiaBtnDOM.value = "Cập nhật";
}

const submitDanhGiaSP = async () => {
    const formData = new FormData(formDanhGiaDOM);
    try {
        const { data: { message } } = await axios.post(`${baseURL}api/v1/danhgia/sanpham/submit`, formData, { params: { maSanPham: maSanPham } });
        formDanhGiaDOM.classList.add('d-none');
        showSanPhamDetail(true);
        showDanhGiaSPs(1);
        thongBao(message);
        formDanhGiaDOM.classList.add('d-none');
    } catch (error) {
        thongBao(error.response.data.message, true);
    }
}

// xóa đánh giá sản phẩm
const xoaDanhGiaSP = async (maDanhGia) => {
    try {
        await axios.delete(`${baseURL}api/v1/danhgia/sanpham/delete`, { params: { maDanhGia: maDanhGia } });
        showSanPhamDetail(true);
        showDanhGiaSPs(1);
        thongBao("Xóa đánh giá thành công");
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
        formDanhGiaDOM.classList.add('d-none');
        // nút hủy đánh giá button chỉ được bấm khi người dùng đang sửa đánh giá
        // câu dưới sẽ select cá đánh giá đầu tiên, và sẽ hiện lại cái đánh giá đó
        // vì khi mình ấn nút sửa thì cái đánh giá đầu tiên bị ẩn, cái form hiện ra
        danhGiaSPListDOM.querySelector('.danh-gia-div').classList.remove('d-none');
    });
}

// trả về chuỗi html của form phản hồi
const buildFormPhanHoi = (ma_danh_gia) => {
    return `
        <button class="phan-hoi-btn btn btn-link text-decoration-none">Phản hồi</button>
        <form class="d-none">
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

//Gửi phản hồi đánh giá sp
const submitPhanHoiDanhGiaSP = async (_formDOM, ma_danh_gia) => {
    //Lấy dữ liệu từ chính cái form mà người dùng đang nhập
    //Form đó đã có noiDung
    const formDanhGiaSanPham = new FormData(_formDOM);
    formDanhGiaSanPham.append('maDanhGia', ma_danh_gia);
    //Gửi dữ liệu vào request
    try {
        await axios.post(`${baseURL}api/v1/phanhoi/submit`, formDanhGiaSanPham);
        thongBao(`Gửi phản hồi thành công`);
        _formDOM.querySelector('textarea').value = '';
        return true;
    } catch (error) {
        thongBao(error.response.data.message, true);
        return false;
    }
}

const suaPhanHoiDanhGiaSP = async (maPhanHoi, formData) => {
    try {
        const { data: { message } } = await axios.post(`${baseURL}api/v1/phanhoi/${maPhanHoi}/update`, formData);
        thongBao(message);
        return true;
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
        return false;
    }
}

const xoaPhanHoiDanhGiaSP = async (maPhanHoi) => {
    try {
        const { data: { message } } = await axios.delete(`${baseURL}api/v1/phanhoi/${maPhanHoi}/delete`);
        thongBao(message);
        return true;
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
        return false;
    }
}

// build list phản hồi sẽ return chuỗi html
const buildListPhanHoi = async (ma_danh_gia) => {
    try {
        const { data: { phanHoiDGSPs } } = await axios.get(`${baseURL}api/v1/phanhoi/${ma_danh_gia}`);
        return phanHoiDGSPs.map((phanHoiDGSP) => {
            const { ma_phan_hoi, username, avatar, ten, ngay_tao, ngay_sua, noi_dung } = phanHoiDGSP;
            const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua})</span>` : ``;
            return `
                <div class="m-4 phan-hoi-div" data-noi_dung="${noi_dung}" data-ma_phan_hoi="${ma_phan_hoi}">
                    <div class="phan-hoi-div-con">
                        <div class="d-flex">
                            <div class="ms-auto">
                                <div class="btn-group">
                                    <button type="button" 
                                        class="phan-hoi-action-btn btn rounded-circle bg-light"
                                        data-bs-toggle="dropdown" 
                                        data-ma_phan_hoi="${ma_phan_hoi}"
                                        aria-expanded="false"
                                    >
                                        <i class="fas fa-ellipsis-h"></i>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li class="text-center">
                                            <div class="spinner-border text-primary" role="status">
                                                <span class="visually-hidden">Loading...</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                        <a href="${baseURL}store/${username}" class="fs-5 text-dark text-decoration-none">${ten}</a>
                        <div>
                            <span>${ngay_tao}</span>${lanSuaCuoi}
                        </div>
                        <p class="tlt-comment">${noi_dung}</p>
                    </div>
                </div>
            `;
        }).join('');
    }
    catch (error) {
        console.log(error);
        return '';
    }
}

const getDanhGiaAction = async (maDanhGia, dropdown) => {
    try {
        const { data: { yours } } = await axios.get(`${baseURL}api/v1/danhgia/sanpham/${maDanhGia}/action`);
        let html = '';
        if (yours) {
            html = `
                <li><button class="sua-danh-gia-btn dropdown-item">Sửa</button></li>
                <li>
                    <button data-ma_danh_gia="${maDanhGia}" class="xoa-danh-gia-btn dropdown-item">Xóa</button>
                </li>     
            `;
        }
        else {
            html = `
                <li><button class="bao-cao-btn dropdown-item">Báo cáo</button></li>
            `;
        }
        dropdown.innerHTML = html;
    } catch (error) {
        console.log(error);
    }
}

const getPhanHoiAction = async (maPhanHoi, dropdown) => {
    try {
        const { data: { yours } } = await axios.get(`${baseURL}api/v1/phanhoi/${maPhanHoi}/action`);
        let html = '';
        if (yours) {
            html = `
                <li>
                    <button data-ma_phan_hoi="${maPhanHoi}" class="sua-phan-hoi-btn dropdown-item">Sửa</button>
                </li>
                <li>
                    <button data-ma_phan_hoi="${maPhanHoi}" class="xoa-phan-hoi-btn dropdown-item">Xóa</button>
                </li>
            `;
        }
        else {
            html = `
                <li><button class="bao-cao-btn dropdown-item">Báo cáo</button></li>
            `;
        }
        dropdown.innerHTML = html;
    } catch (error) {
        console.log(error);
    }
}

// lưu sản phẩm vừa xem
const luuSanPhamVuaXem = (tenSanPham, gia, anhSanPham) => {
    const danhSachSanPhamVuaXem = JSON.parse(localStorage.getItem('danhSachSanPhamVuaXem')) || [];
    // nếu sản phẩm ko bị trùng thì thêm vào đầu danh sách
    // nếu sản phẩm bị trùng thì chuyển nó lên đầu
    if (!danhSachSanPhamVuaXem.some(sp => sp.maSanPham === maSanPham)) {
        danhSachSanPhamVuaXem.unshift(new SanPhamVuaXem(maSanPham, tenSanPham, gia, anhSanPham));
    } else {
        const index = danhSachSanPhamVuaXem.findIndex(sp => sp.maSanPham === maSanPham);
        danhSachSanPhamVuaXem.splice(index, 1);
        danhSachSanPhamVuaXem.unshift(new SanPhamVuaXem(maSanPham, tenSanPham, gia, anhSanPham));
    }
    localStorage.setItem('danhSachSanPhamVuaXem', JSON.stringify(danhSachSanPhamVuaXem));
    if (danhSachSanPhamVuaXem.length > 6) {
        localStorage.setItem('danhSachSanPhamVuaXem', JSON.stringify(danhSachSanPhamVuaXem.slice(0, 6)));
    }
}

danhGiaSPListDOM.addEventListener('click', async (event) => {
    const eventTarget = event.target;
    const parentNode = eventTarget.parentNode; // => thẻ cha chứa thẻ mình bấm
    if (eventTarget.classList.contains('xem-phan-hoi')) { // ở đây là thẻ cha của button xem phản hồi
        const ma_danh_gia = eventTarget.dataset.ma_danh_gia;
        // xóa class đó ra khỏi button và thêm class an-phan-hoi
        // để khi người dùng nhấn lại nút đó thì sẽ ẩn phản hồi
        eventTarget.innerHTML = 'Ẩn phản hồi';
        eventTarget.classList.remove('xem-phan-hoi');
        eventTarget.classList.add('an-phan-hoi');
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
    if (eventTarget.classList.contains('an-phan-hoi')) {
        eventTarget.innerHTML = 'Xem phản hồi';
        eventTarget.classList.remove('an-phan-hoi');
        eventTarget.classList.add('xem-phan-hoi');
        parentNode.removeChild(parentNode.querySelector('.div-phan-hoi'));
        return;
    }
    if (eventTarget.classList.contains('phan-hoi-btn')) {
        // nextElementSibling là thẻ form sau thẻ button
        eventTarget.nextElementSibling.classList.remove('d-none');
        eventTarget.nextElementSibling.classList.add('d-block');
        return;
    }
    if (eventTarget.classList.contains('huy-phan-hoi')) {
        parentNode.closest('form').classList.remove('d-block');
        parentNode.closest('form').classList.add('d-none');
        return;
    }
    if (eventTarget.classList.contains('submit-phan-hoi')) {
        const formNode = parentNode.closest('form'); // là cái form phản hồi do cái button nằm trong 1 cái div nữa
        const ma_danh_gia = eventTarget.dataset.ma_danh_gia;
        const kiemTraFormPhanHoi = await submitPhanHoiDanhGiaSP(formNode, ma_danh_gia);
        if (!kiemTraFormPhanHoi) {
            return;
        }
        formNode.classList.add('d-none');
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
            anPhanHoiBtn.classList.remove('d-none');
        }
        else if (xemPhanHoiBtn) {
            xemPhanHoiBtn.classList.remove('xem-phan-hoi');
            xemPhanHoiBtn.classList.add('an-phan-hoi');
            xemPhanHoiBtn.innerHTML = 'Ẩn phản hồi';
        }
        return;
    }
    // sửa xóa đánh giá
    if (eventTarget.classList.contains('action-btn')) {
        const dropdownDOM = parentNode.querySelector('.dropdown-menu');
        if (!eventTarget.classList.contains('show')) {
            return;
        }
        const maDanhGia = eventTarget.dataset.ma_danh_gia;
        await getDanhGiaAction(maDanhGia, dropdownDOM);
        return;
    }
    if (eventTarget.classList.contains('sua-danh-gia-btn')) {
        const danhGiaDivDOM = eventTarget.closest('.danh-gia-div');
        const { noi_dung, so_sao } = danhGiaDivDOM.dataset;
        suaDanhGiaSP(noi_dung, so_sao);
        return;
    }
    if (eventTarget.classList.contains('xoa-danh-gia-btn')) {
        const ma_danh_gia = eventTarget.dataset.ma_danh_gia;
        xoaDanhGiaSP(ma_danh_gia);
        formDanhGiaDOM.querySelector('textarea').value = '';
        formDanhGiaDOM.querySelector('select').value = 1;
        huyDanhGiaBtnDOM.classList.remove('d-block');
        huyDanhGiaBtnDOM.classList.add('d-none');
        danhGiaBtnDOM.value = 'Đánh giá';
        return;
    }
    // sửa xóa phản hồi
    if (eventTarget.classList.contains('phan-hoi-action-btn')) {
        const dropdownDOM = parentNode.querySelector('.dropdown-menu');
        if (!eventTarget.classList.contains('show')) {
            return;
        }
        const maPhanHoi = eventTarget.dataset.ma_phan_hoi;
        await getPhanHoiAction(maPhanHoi, dropdownDOM);
    }
    if (eventTarget.classList.contains('sua-phan-hoi-btn')) {
        const phanHoiDivDOM = eventTarget.closest('.phan-hoi-div');
        const phanHoiDivConDOM = eventTarget.closest('.phan-hoi-div-con');
        phanHoiDivConDOM.classList.add('d-none');
        const { ma_phan_hoi } = phanHoiDivDOM.dataset;
        const noi_dung = phanHoiDivDOM.querySelector('.tlt-comment').innerText;
        phanHoiDivDOM.innerHTML += `
            <form class="form-sua-phan-hoi">
                <div class="form-group">
                    <label>Nội dung phản hồi</label>
                    <textarea class="form-control" name="noiDung" rows="3">${noi_dung}</textarea>
                </div>
                <div class="d-flex gap-2">
                    <button type="button" class="huy-sua-phan-hoi w-100 btn btn-block btn-outline-danger">
                        Hủy
                    </button>
                    <button 
                        type="button" class="submit-sua-phan-hoi w-100 btn btn-block btn-outline-success" 
                        data-ma_phan_hoi="${ma_phan_hoi}"
                    >Sửa</button>
                </div>
            </form>
        `;
        return;
    }
    if (eventTarget.classList.contains('submit-sua-phan-hoi')) {
        const phanHoiDivDOM = eventTarget.closest('.phan-hoi-div');
        const phanHoiDivConDOM = phanHoiDivDOM.querySelector('.phan-hoi-div-con');
        const formSuaPhanHoiDOM = eventTarget.closest('.form-sua-phan-hoi');
        const formData = new FormData(formSuaPhanHoiDOM);
        const ma_phan_hoi = eventTarget.dataset.ma_phan_hoi;
        if (await suaPhanHoiDanhGiaSP(ma_phan_hoi, formData)) {
            phanHoiDivConDOM.classList.remove('d-none');
            phanHoiDivConDOM.querySelector('.tlt-comment').innerHTML = formData.get('noiDung');
            formSuaPhanHoiDOM.remove();
        }
        return;
    }
    if (eventTarget.classList.contains('huy-sua-phan-hoi')) {
        const phanHoiDivDOM = eventTarget.closest('.phan-hoi-div');
        const formSuaPhanHoiDOM = phanHoiDivDOM.querySelector('.form-sua-phan-hoi');
        formSuaPhanHoiDOM.remove();
        const phanHoiDivCon = phanHoiDivDOM.querySelector('.phan-hoi-div-con');
        phanHoiDivCon.classList.remove('d-none');
        return;
    }
    if (eventTarget.classList.contains('xoa-phan-hoi-btn')) {
        const phanHoiDivDOM = eventTarget.closest('.phan-hoi-div');
        const ma_phan_hoi = eventTarget.dataset.ma_phan_hoi;
        if (await xoaPhanHoiDanhGiaSP(ma_phan_hoi)) {
            phanHoiDivDOM.remove();
        }
    }
});