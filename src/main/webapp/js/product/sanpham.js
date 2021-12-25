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
        //Chưa nghĩ ra cái tên nên để tạm customer
        pageNguoiDangSP.href = `${baseURL}customer/${maKhachHang}`;
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


//http://localhost:8080/TMDT-0.0.1-SNAPSHOT/viewCurrentDanhGiaSanPham/35a99f29-64da-11ec-bb14-8378cfa7d63d
const noiDungDanhGiaSP = document.querySelector('#noiDungDanhGiaSP > textarea');
const soSaoDanhGiaSP = document.querySelector('#soSao');
const updateOrSubmit = document.querySelector('#updateOrSubmit');
const formData = new FormData(formDOM);


const check_HanhViDanhGiaSP = async () => {
    
    formData.append("maSanPham", maSanPham);
    //Nếu đã bình luận sản phẩm này
    try {
        const { data : {danhGiaSPHienTai} } = await axios.post(`${baseURL}viewCurrentDanhGiaSanPham/${maSanPham}`);
        //Thay đổi nút thêm bình luận thành nút cập nhật bình luận
        updateOrSubmit.innerHTML = "Cập nhật bình luận";
        console.log("Đây là nội dung, số sao khách hàng đã bình luận từ trước");
        console.log(danhGiaSPHienTai[0].noi_dung);
        console.log("<! --Đây là nội dung, số sao khách hàng đã bình luận từ trước -->");

        
        //Chạy await ok thì mình hiển thị nội dung và số sao đã có lên form
        noiDungDanhGiaSP.value = danhGiaSPHienTai[0].noi_dung;
        soSaoDanhGiaSP.value = danhGiaSPHienTai[0].so_sao;
        //Update đánh giá sản phẩm chỗ này, viết 1 hàm submit sang action update đánh giá là xong
        //Lấy mã đánh giá đưa vào formDOM
        formData.append('maDanhGia',danhGiaSPHienTai[0].ma_danh_gia);

    } catch (e) {
        console.log(e);
    }
}


//Form này vừa có chức năng update, vừa có chức năng submit đánh giá mới của khách hàng
const Submit_Or_Update = async () =>{
    const formData = new FormData(formDOM);
    try {
        formData.append("maSanPham", maSanPham);
        console.log(formData);
        await axios.post(`${baseURL}danhGiaSP_Submit_Or_Update`, formData);
        showDanhGiaSPList();
        showSanPhamDetail();
    } catch (error) {
        const data = error.response.data;
        console.log(data);
        errorMsg.textContent = data.error ?? "";
    }
}

check_HanhViDanhGiaSP();

if (formDOM) {
    formDOM.addEventListener('submit', (event) => {
        event.preventDefault();
        Submit_Or_Update();
    });
}



