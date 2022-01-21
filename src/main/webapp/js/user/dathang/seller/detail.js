const thongTinNguoiDatDOM = document.querySelector('.thongTinNguoiDat');
const chiTietDatHangDOM = document.querySelector('.chiTietDatHang');
const params = window.location.pathname.split('/').slice(0);
const id = params.at(-1);

const buildForm = (maSanPham, status) => {
    return `
        <form class="d-none">
            <input type="hidden" name="maSanPham" value="${maSanPham}">
            <input type="hidden" name="status" value="${status}">
            <div class="form-group">
                <label>Lý do</label>
                <textarea class="form-control" name="lyDo"></textarea>
            </div>
            <div class="d-flex gap-2">
                <button type="button" class="toggle-form-btn btn btn-outline-danger">Đóng</button>
                <button type="button" class="xac-nhan-btn btn btn-outline-success">Xác nhận</button>
            </div>
        </form>
    `;
}

const showChiTietDatHang = async (skip = false) => {
    try {
        const { data: {
            thongTinNguoiDat, chiTietDatHang
        } } = await axios.get(`${baseURL}api/v1/user/seller/dathang/${id}`);
        const {
            ten, so_dien_thoai, dia_chi, email, ngay_dat
        } = thongTinNguoiDat;

        const allChiTietDatHang = chiTietDatHang.map((item) => {
            const { ma_san_pham, so_luong_dat, anh, ma_nhan_hang,
                status, don_gia, ten_san_pham, tong_tien, so_luong,
            } = item;
            let chucNangHtml = "";
            if (status === -1) {
                chucNangHtml = `
                    <div class="text-danger">
                        Đơn hàng đã bị hủy
                    </div>
                `;
            }
            else if (status === 0) {
                chucNangHtml = `
                    <div class="d-flex gap-2">
                        <button class="toggle-form-btn d-block btn btn-danger">
                            Từ chối
                        </button>
                        <button data-id="${ma_san_pham}" data-status="1"
                            class="cap-nhat-btn d-block btn btn-primary"
                        >
                            Bắt đầu chuyển hàng
                        </button>
                    </div>
                    ${buildForm(ma_san_pham, -1)}
                `;
            } else if (status === 1) {
                chucNangHtml = `
                    <button class="toggle-form-btn d-block btn btn-danger">
                        Ngừng chuyển hàng
                    </button>
                    ${buildForm(ma_san_pham, 0)}
                `;
            } else if (status === 2) {
                chucNangHtml = `
                    <div class="text-success">
                        Đã giao hàng
                    </div>
                `;
            }

            const donGiaVND = don_gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            const tongTienVND = tong_tien.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <div class="row">
                    <div class="col-3 d-flex me-1">
                        <img 
                            src="${baseURL}images/product/${anh}" 
                            class="tlt-thumbnail rounded mx-auto my-auto d-block img-fluid"
                            alt="${ten_san_pham}"
                        >
                    </div>
                    <div class="col-8">
                        <div class="row">
                            <div class="col-12">
                                <a
                                    href="${baseURL}sanpham/${ma_san_pham}"
                                    class="text-decoration-none text-dark fw-bold"
                                >
                                    ${ten_san_pham}
                                </a> (còn ${so_luong})
                            </div>
                            <div class="col-2">
                                Đơn giá
                            </div>
                            <div class="col-10">
                                ${donGiaVND}
                            </div>
                            <div class="col-2">
                                Số lượng đặt
                            </div>
                            <div class="col-10">
                                ${so_luong_dat}
                            </div>
                            <div class="col-2">
                                Thành tiền
                            </div>
                            <div class="col-10">
                                ${tongTienVND}
                            </div>
                            <div class="col-2">
                                Mã nhận hàng
                            </div>
                            <div class="col-10">
                                ${ma_nhan_hang}
                            </div>
                            <div class="col-12 chuc-nang">
                                ${chucNangHtml}
                            </div>
                        </div>
                    </div>
                </div>        
            `;
        }).join('');
        if (!skip) {
            thongTinNguoiDatDOM.innerHTML = `
                <div class="col-2 fw-bold">Tên người đặt</div>
                <div class="col-10">${ten}</div>
                <div class="col-2 fw-bold">Địa chỉ</div>
                <div class="col-10">${dia_chi}</div>
                <div class="col-2 fw-bold">Số điện thoại</div>
                <div class="col-10">${so_dien_thoai}</div>
                <div class="col-2 fw-bold">Email</div>
                <div class="col-10">${email}</div>
                <div class="col-2 fw-bold">Ngày đặt</div>
                <div class="col-10">${ngay_dat}</div>
            `;
        }
        chiTietDatHangDOM.innerHTML = allChiTietDatHang;
    } catch (error) {
        console.log(error);
        const { response: { data: { message } } } = error;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
}

showChiTietDatHang();

const capNhatTinhTrang = async (formData) => {
    try {
        await axios.post(`${baseURL}api/v1/user/seller/dathang/${id}/capnhat`, formData);
        showChiTietDatHang(true);
    } catch (error) {
        console.log(error);
        const { response: { data: { message } } } = error;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
}

chiTietDatHangDOM.addEventListener('click', async (event) => {
    const eventTarget = event.target;
    const { classList } = eventTarget;
    if (classList.contains('cap-nhat-btn')) {
        const { dataset: { id: maSanPham, status } } = eventTarget;
        const formData = new FormData();
        formData.append('status', status);
        formData.append('maSanPham', maSanPham);
        capNhatTinhTrang(formData);
        return;
    }
    if (classList.contains('toggle-form-btn')) {
        const chucNangDOM = eventTarget.closest('.chuc-nang');
        const formDOM = chucNangDOM.querySelector('form');
        formDOM.classList.toggle('d-none');
        return;
    }
    if (classList.contains('xac-nhan-btn')) {
        const formDOM = eventTarget.closest('form');
        const formData = new FormData(formDOM);
        capNhatTinhTrang(formData);
    }
});