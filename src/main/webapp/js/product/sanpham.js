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
const anhChinhDOM = document.querySelector('#anhChinh');
const pageNguoiDangSP = document.querySelector('#pageNguoiDangSP');


//Các biến của đánh giá sản phẩm
const danhGiaSPListDom = document.querySelector('#danhGiaSPListDom');
const errorMsg = document.querySelector('#errorMsg');

const showSanPhamDetail = async () => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/sanpham/${maSanPham}`);
        const { tenSanPham, nguoiDangSP, moTa, gia, anhSanPham, xepHang, maKhachHang } = sanpham;
        tenSanPhamDOM.innerHTML = tenSanPham;
        danhGiaDOM.innerHTML = xepHang ?? "Chưa có đánh giá";
        moTaSanPhamDOM.innerHTML = moTa;
        giaDOM.innerHTML = gia;
        nguoiDangSanPham.innerHTML = nguoiDangSP;
        pageNguoiDangSP.href = `${baseURL}store/${maKhachHang}`;
        anhChinhDOM.src = `${baseURL}images/product/${anhSanPham}`;
    } catch (error) {
        console.log(error);
    }
}

showSanPhamDetail();

/* hiện danh sách đánh giá */
const showDanhGiaSPs = async () => {
    try {
        const { data: { danhGiaSPs, danhGiaCuaBan } } = await axios.get(`${baseURL}api/v1/danhgia/sanpham/${maSanPham}`);
        
        let danhGiaCuaBanHTML = '';
        let danhGiaSPsHTML = '';
        if (danhGiaCuaBan !== undefined) {
            formDOM.style.display = 'none';
            const { ngay_tao, ngay_sua, noi_dung, so_sao, ten } = danhGiaCuaBan;
            document.querySelector('#noiDung').value = noi_dung;
            document.querySelector('#soSao').value = so_sao;
            //in ra icon ngôi sao đánh giá
            let so_sao_html = '';
            for (let i = 0; i < so_sao; i++) {
                so_sao_html += '<span>&#9733;</span>';
            }
            const lanSuaCuoi = ngay_sua ? `<span class="text-muted">(Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : '';
            danhGiaCuaBanHTML = `
                <div class="comment mt-4 text-justify float-left" id="danhGiaCuaToi"> 
                    <img src="https://i.imgur.com/yTFUilP.jpg" alt="avatar" class="rounded-circle" width="40" height="40">
                    <h4>${ten}</h4> <span>${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}  lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p</span>${lanSuaCuoi}
                    <br>
                    ${so_sao_html}
                    <p>${noi_dung}</p>
                    <button class="btn btn-link" onclick="suaDanhGiaSP()">Sửa</button>
                </div>`;
        }
        if (danhGiaSPs.length > 0) {
            const allDanhGiaSPs = danhGiaSPs.map((danhGiaSP) => {
                const { ngay_tao, ngay_sua, noi_dung, so_sao, ten } = danhGiaSP;
                //in ra icon ngôi sao đánh giá
                let so_sao_html = '';
                for (let i = 0; i < so_sao; i++) {
                    so_sao_html += '<span>&#9733;</span>';
                }
                const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : ``;
                return `
                    <div class="comment mt-4 text-justify float-left"> <img src="https://i.imgur.com/yTFUilP.jpg"
                            alt="avatar" class="rounded-circle" width="40" height="40">
                        <h4>${ten}</h4> <span>
                        ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p
                        </span>${lanSuaCuoi}
                        <br>
                        ${so_sao_html}
                        <p>${noi_dung}</p>
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
    }
}

showDanhGiaSPs();

// sửa đánh giá
const suaDanhGiaSP = () => {
    document.querySelector('#danhGiaCuaToi').style.display = 'none';
    formDOM.style.display = 'block';
    huyDanhGiaBtnDOM.style.display = 'block';
    document.querySelector('#noiDung').focus();
    danhGiaBtnDOM.value = "Cập nhật";
}

const submitDanhGiaSP = async () => {
    const formData = new FormData(formDOM);
    try {
        await axios.post(`${baseURL}api/v1/danhgia/sanpham/submit`, formData, { params: { maSanPham: maSanPham } });
        formDOM.style.display = 'none';
        showSanPhamDetail();
        showDanhGiaSPs();
        thongBao("Gửi đánh giá thành công");
    } catch (error) {
        console.log(error);
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