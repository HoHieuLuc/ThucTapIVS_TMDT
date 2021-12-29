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
const anhSanPhamDOM = document.querySelector('#anhSanPham');

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

const showSanPhamDetail = async (skip) => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/sanpham/${maSanPham}`);
        const { tenSanPham, tenKhachHang, moTa, gia, anhSanPhams, xepHang, avatar, username } = sanpham;
        danhGiaDOM.innerHTML = xepHang ?? "Chưa có đánh giá";
        if (skip) {
            return;
        }
        tenSanPhamDOM.innerHTML = tenSanPham;
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
//Check đã đăng nhập hay chưa 
const loginCheckFunction = async () => {
    try {
        await axios.get(`${baseURL}checkCustomer`);
        return true;
    }
    catch (error){
        console.log(error.response.data.message);
        return false;
    }
}
  




/* hiện danh sách đánh giá */
const showDanhGiaSPs = async () => {
    try {
        //Bieens isLogin
        const isLogin = await loginCheckFunction();
        const { data: { danhGiaSPs, danhGiaCuaBan } } = await axios.get(`${baseURL}api/v1/danhgia/sanpham/${maSanPham}`);

        let danhGiaCuaBanHTML = '';
        let danhGiaSPsHTML = '';
        if (danhGiaCuaBan !== undefined) {
            formDOM.style.display = 'none';
            const { ma_danh_gia,so_phan_hoi, ngay_tao, ngay_sua, noi_dung, so_sao, ten, username, avatar } = danhGiaCuaBan;
            document.querySelector('#noiDung').value = noi_dung;
            document.querySelector('#soSao').value = so_sao;
            //in ra icon ngôi sao đánh giá
            let so_sao_html = '';
            for (let i = 0; i < so_sao; i++) {
                so_sao_html += '<span>&#9733;</span>';
            }
            //Nếu số phản hồi 0 thì không in ra
            let phanHoiElement = '';
            if (so_phan_hoi > 0) phanHoiElement = `<button class="btn btn-link" onclick="{buildListPhanHoi(${ma_danh_gia})}>Xem ${so_phan_hoi} phản hồi </button>`;

            const lanSuaCuoi = ngay_sua ? `<span class="text-muted"> (Lần sửa cuối: ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p)</span>` : '';
            danhGiaCuaBanHTML = `
                <div class="comment mt-4 text-justify float-left" id="danhGiaCuaToi"> 
                    <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                    <h4>${ten}</h4> <span>${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}  lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p</span>${lanSuaCuoi}
                    <br>
                    ${so_sao_html}
                    <p>${noi_dung}</p>
                    <button class="btn btn-link" onclick="suaDanhGiaSP()">Sửa</button>
                    ${phanHoiElement}
                </div>`;
        }
        if (danhGiaSPs.length > 0) {
            const allDanhGiaSPs = danhGiaSPs.map((danhGiaSP) => {
                const { ma_danh_gia, ngay_tao, ngay_sua, noi_dung, so_sao, ten, username, avatar, so_phan_hoi } = danhGiaSP;
                //in ra icon ngôi sao đánh giá
                let so_sao_html = '';
                for (let i = 0; i < so_sao; i++) {
                    so_sao_html += '<span>&#9733;</span>';
                }

                // Kiểm tra nếu chưa đăng nhập, không hiện nút phản hồi
                let formPhanHoiElement = ``;
                let onClickElement = ``;

              

                //Chỉ khi đăng  nhập rồi thì mới hiện for
                if (ma_danh_gia != undefined) {
                    console.log("ở chỗ đánh giá của bạn có mã đánh giá là ", ma_danh_gia);
                    
                    formPhanHoiElement = `${buildFormPhanHoi(ma_danh_gia,isLogin)}`;
                   
                }
                

                //Nếu số phản hồi 0 thì không in ra
                let phanHoiElement = '';
                //Tạo nút xem danh sách phản hồi
                onClickElement = `onclick="{buildListPhanHoi(${ma_danh_gia})}"`;


                if (so_phan_hoi > 0 && onClickElement != ``) phanHoiElement = `<div class="btn btn-link" ${onClickElement}  id="dsph${ma_danh_gia}" >Xem ${so_phan_hoi} phản hồi </div>`;

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
        showSanPhamDetail(true);
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
//Button phản hồi và form phản hồi đánh gias ph
const buildFormPhanHoi = (ma_danh_gia,isLogin) => {
   
    if (isLogin == true)
        return `
        <button class="btn btn-link" onclick="document.querySelector('#mdg_${ma_danh_gia}').style.display = 'block';">Phản hồi</button>
                            
        <form style="display: none;" id="mdg_${ma_danh_gia}">
            <input type="text" name="noiDung" class="form-control" placeholder="Nhập nội dung phản hồi">
            <button type="button" class="btn btn-success float-right" onclick="phanHoiDanhGiaSP(${ma_danh_gia});" >Gửi phản hồi</button>
            <button type="button" class="btn btn-success float-right"
            onclick="document.querySelector('#mdg_${ma_danh_gia}').style.display = 'none';"> Hủy </button>
        </form>
        `
    else {
        return ``
    }
}



//Gửi phản hồi đánh giá sp
const phanHoiDanhGiaSP = async (ma_danh_gia) => {
    //Lấy dữ liệu từ chính cái form mà người dùng đang nhập
    //Form đó đã có noiDung
     const formDanhGiaSanPham = new FormData(document.querySelector(`#mdg_${ma_danh_gia}`));

    //Gửi dữ liệu vào request
    try {
        await axios.post(`${baseURL}api/v1/phanhoi/sanpham/submit`, formDanhGiaSanPham, {
            params: { maDanhGia: ma_danh_gia }
        });
        formDOM.style.display = 'none';
        thongBao(`Gửi phản hồi cho đánh giá số ${ma_danh_gia} thành công`);
    } catch (error) {
        thongBao(error.response.data.message, true);
    }
}

//Xem phản hồi theo mã đánh giá 
const buildListPhanHoi = async (ma_danh_gia) => {
    try {
        const { data: { phanHoiDGSPs } } = await axios.get(`${baseURL}api/v1/phanhoi/${ma_danh_gia}`);
        console.log(phanHoiDGSPs);
        //SELECT tk.avatar,tk.username,kh.ten, phdgsp.noi_dung, phdgsp.ngay_tao, phdgsp.ngay_sua
        const allPhanHoiDGSPs = phanHoiDGSPs.map((phanHoiDGSP) => {
            const {avatar,ten,ngay_tao,ngay_sua,noi_dung} = phanHoiDGSP;
            return `
            <div class="comment mt-4 text-justify float-left"> 
                <img src="${baseURL}images/user/${avatar}" alt="avatar" class="rounded-circle" width="40" height="40">
                <h4>${ten}</h4><span>
                    ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} lúc ${ngay_tao.time.hour}h:${ngay_tao.time.minute}p
                </span> ${ngay_sua.date.day}/${ngay_sua.date.month}/${ngay_sua.date.year} lúc ${ngay_sua.time.hour}h:${ngay_sua.time.minute}p
                <br>
                <p>${noi_dung}</p>
            </div>
            `
        } )
        document.querySelector(`#dsph${ma_danh_gia}`).innerHTML = allPhanHoiDGSPs;
    }
    catch (error) {
        console.log(error);
    }
}
