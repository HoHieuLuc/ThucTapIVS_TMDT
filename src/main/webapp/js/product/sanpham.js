//Lấy mã sản phẩm trên đường dẫn
const params = window.location.pathname.split('/').slice(0);
const maSanPham = params[params.length - 1];

//Các biến của chi tiết sản phẩm
const tenSanPhamDOM = document.querySelector('#tenSanPham');
const danhGiaDOM = document.querySelector('#danhGia');
const moTaSanPhamDOM = document.querySelector('#moTaSanPham');
const giaDOM = document.querySelector('#gia');
const anhChinhDOM = document.querySelector('#anhChinh');


//Các biến của đánh giá sản phẩm
const danhGiaSPListDom = document.querySelector('#danhGiaSPListDom');
const errorMsg = document.querySelector('#errorMsg');

const showSanPhamDetail = async () => {
    try {
        const { data: { sanpham } } = await axios.get(`${baseURL}api/v1/sanpham/${maSanPham}`);
        const { tenSanPham, moTa, gia, anhSanPham, xepHang } = sanpham;
        tenSanPhamDOM.innerHTML = tenSanPham;
        danhGiaDOM.innerHTML = xepHang ?? "Chưa có đánh giá";
        moTaSanPhamDOM.innerHTML = moTa;
        giaDOM.innerHTML = gia;
        anhChinhDOM.src = `${baseURL}images/product/${anhSanPham}`;
    } catch (error) {
        console.log(error);
    }
}

showSanPhamDetail();

const showDanhGiaSPList = async () => {
    try {
        const { data: { danhGiaSPs } } = await axios.get(`${baseURL}danhGiaSanPham/${maSanPham}`);
        console.log(danhGiaSPs);
        if (danhGiaSPs.length > 0) {
            const allDanhGiaSPs = danhGiaSPs.map((danhGiaSP) => {
                const { ngay_tao, ngay_sua, noi_dung, so_sao, ten } = danhGiaSP;
                //in ra icon ngôi sao đánh giá
                var so_sao_html = ` `;
                for (let i = 0; i < so_sao; i++) {
                    so_sao_html = `<span>&#9733;</span>` + so_sao_html;
                }
                return `
                    <div class="comment mt-4 text-justify float-left"> <img src="https://i.imgur.com/yTFUilP.jpg"
                            alt="avatar" class="rounded-circle" width="40" height="40">
                        <h4>${ten}</h4> <span>${ngay_tao}</span><br>
                            ${so_sao_html}
                        <p>${noi_dung}</p>
                    </div>
                `;
            }).join(' ');
            danhGiaSPListDom.innerHTML = allDanhGiaSPs;

        } else {
            danhGiaSPListDom.innerHTML = `<h4>Sản phẩm này chưa có đánh giá</h4>`;
        }
    } catch (error) {
        console.log(error);
    }
}
showDanhGiaSPList();

const formDOM = document.querySelector('#formDanhGiaSanPham');
//const noiDungErrorMessage = document.querySelector('#username_error');

const submitDanhGiaSP = async () => {
    const formData = new FormData(formDOM);
    formData.append("maSanPham", maSanPham);
    //Thực hiện request
    try {
        await axios.post(`${baseURL}danhGiaSanPhamSubmit`, formData);
        showDanhGiaSPList();
        showSanPhamDetail();
    } catch (error) {
        const data = error.response.data;
        console.log(data);
        errorMsg.textContent = data.error ?? "";
    }
}

if (formDOM) {
    formDOM.addEventListener('submit', (event) => {
        event.preventDefault();
        submitDanhGiaSP();
    });
}

