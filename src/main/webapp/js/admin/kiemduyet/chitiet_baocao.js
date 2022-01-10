document.title = "Admin - Chi tiết báo cáo người dùng ";
//Lấy mã báo cáo trên đường dẫn
const params = window.location.pathname.split('/').slice(0);
const maBaoCao = params[params.length - 1];

//Các biến của chi tiết báo cáo
const maBaoCaoDOM = document.querySelector('#maBaoCao');
const unameSenderDOM = document.querySelector('#unameSender');
const unameReceiverDOM = document.querySelector('#unameReceiver');
const statusDOM = document.querySelector('#status');
const noiDungDOM = document.querySelector('#noiDung');

//Tạo chức năng tùy theo trạng Thái
const chucNangDOM = document.querySelector('#chucNang');
const chucNangFormDOM = document.querySelector('#chucNangForm');

//Show báo cáo xong thì mới chạy mấy lệnh này 
// Các biến DOM của duyệt và xử lý báo cáo 
let noiDungGuiDOM;
let hinhThucDuyetDOM;
let noiDungGuiKhacDOM;



const showBaoCaoDetail = async () => {
    try {
        const { data: { chitiet_baocao } } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/${maBaoCao}`);
        // có avatar nữa
        const { ma_bao_cao, unameReceiver, unameSender, noi_dung, status } = chitiet_baocao;
        maBaoCaoDOM.textContent = ma_bao_cao;
        unameReceiverDOM.textContent = unameReceiver;
        unameSenderDOM.textContent = unameSender;
        noiDungDOM.textContent = noi_dung;
        
        
        //if status==0 statusDOM.textContent = `${status}`;
        switch (status) {
            case -2:
                statusDOM.textContent = `Vi phạm nặng nhất (Khóa tài khoản)`;
                break;
            case -1:
                statusDOM.textContent = `Cảnh cáo`;
                break;
            case 0:
                statusDOM.textContent = `Chưa duyệt`;
                chucNangDOM.innerHTML = `

                <!-- Chọn hình thức duyệt -->
                <select id="hinhThucDuyet"  class="form-select">
                    <option value="-2">Vi phạm nặng nhất</option>
                    <option value="-1">Cảnh cáo</option>
                    <option value="1">Nhắc nhở</option>
                    <option value="2">Không vi phạm</option>
                 </select>
                 <!-- Chọn hoặc điền nội dung để gửi cho người bị báo cáo -->
                 <select id="noiDungGui" class="form-select">
                        <option value="1">Sản phẩm cấm</option>
                        <option value="2">Lừa đảo</option>
                        <option value="3">Hàng giả</option>
                        <option value="4">Khác</option>
                 </select>
                 <!-- làm sao để khi mình bấm khác thì nó hiện ra input nhập nội dung khác-->
                 <textarea type="input" id="noiDungGuiKhac" cols="50" rows="4" class="d-none"></textarea>
            `;
                noiDungGuiDOM = document.querySelector('#noiDungGui');
                hinhThucDuyetDOM = document.querySelector('#hinhThucDuyet');
                noiDungGuiKhacDOM = document.querySelector('#noiDungGuiKhac');

                noiDungGuiDOM.addEventListener('change', (event) => {
                    if (event.target.value === '4') {
                        noiDungGuiKhacDOM.classList.remove('d-none');
                    } else {
                        noiDungGuiKhacDOM.classList.add('d-none');
                    }
                });

                break;
            case 1:
                statusDOM.textContent = `Nhắc nhở`;
                break;
            case 2:
                statusDOM.textContent = `Bỏ qua`;
                break;
        }
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}
showBaoCaoDetail();





//Bắt sự kiện của từng nút để duyệt
chucNangFormDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('maBaoCao', maBaoCao);
    // Người nhận báo cáo chính là người bị báo cáo
    formData.append('userName', unameReceiverDOM.textContent);
    formData.append('status', hinhThucDuyetDOM.value);

    if (noiDungGuiDOM.value == '4') {
        formData.append('noiDung', noiDungGuiKhacDOM.value);
    }
        
    else {
        // Cần xử lý số thành nội dung đã định nghĩa sẵn
        formData.append('noiDung', noiDungGuiDOM.value)
    }
        
    // Display the key/value pairs
    for (var pair of formData.entries()) {
        console.log(pair[0] + ', ' + pair[1]);
    }
    try {
        await axios.post(`${baseURL}api/v1/nhanvien/baocao/changestatus`, formData)
    }
    catch (error) {
        console.log(error)
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true)
    }
    //Reload lại data chi tiết sản phẩm
    showBaoCaoDetail();
    chucNangFormDOM.remove();
})



