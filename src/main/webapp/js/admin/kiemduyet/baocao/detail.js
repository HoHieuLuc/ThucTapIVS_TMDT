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
const ngayTaoDOM = document.querySelector('#ngayTao');

//Tạo chức năng tùy theo trạng Thái
const chucNangDOM = document.querySelector('#chucNang');

const showBaoCaoDetail = async () => {
    try {
        const { data: { chitiet_baocao } } = await axios.get(`${baseURL}api/v1/admin/baocao/${maBaoCao}`);
        const { ma_bao_cao, unameReceiver, unameSender, noi_dung, status, ngay_tao } = chitiet_baocao;
        maBaoCaoDOM.textContent = ma_bao_cao;
        unameReceiverDOM.textContent = unameReceiver;
        unameSenderDOM.textContent = unameSender;
        noiDungDOM.textContent = noi_dung;
        ngayTaoDOM.textContent = ngay_tao;
        chucNangDOM.textContent = '';
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
                    <form class="col-6 mt-1" id="chucNangForm"> 
                        <select name="status" class="form-select mb-1">
                            <option value="" disabled selected>Hình thức duyệt</option>
                            <option value="-2">Vi phạm nặng nhất</option>
                            <option value="-1">Cảnh cáo</option>
                            <option value="1">Nhắc nhở</option>
                            <option value="2">Không vi phạm</option>
                        </select>
                        <select name="noiDung" class="form-select mb-1">
                            <option value="" disabled selected>Lý do</option>
                            <option value="1">Sản phẩm cấm</option>
                            <option value="2">Lừa đảo</option>
                            <option value="3">Hàng giả</option>
                            <option class="khac" value="4">Khác</option>
                        </select>
                        <textarea type="input" id="noiDungGuiKhac" class="d-none form-control"></textarea>
                        <input type="submit" class="btn btn-outline-dark" value="Xác nhận">
                    </form>
                `;
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

const init = async () => {
    await showBaoCaoDetail();
    const chucNangFormDOM = document.querySelector('#chucNangForm');
    if (chucNangFormDOM) {
        const noiDungSelectDOM = chucNangFormDOM.querySelector('select[name="noiDung"]');
        const khacOptionDOM = noiDungSelectDOM.querySelector('.khac');
        const noiDungGuiKhacDOM = chucNangFormDOM.querySelector('#noiDungGuiKhac');
        noiDungSelectDOM.addEventListener('change', (e) => {
            const value = e.target.value;
            if (value == 1 || value == 2 || value == 3) {
                noiDungGuiKhacDOM.classList.add('d-none');
            } else {
                noiDungGuiKhacDOM.classList.remove('d-none');
            }
        });

        noiDungGuiKhacDOM.addEventListener('keyup', (e) => {
            khacOptionDOM.value = e.target.value;
        });

        chucNangFormDOM.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(chucNangFormDOM);
            try {
                await axios.post(`${baseURL}api/v1/admin/baocao/${maBaoCao}/duyet`, formData);
                showBaoCaoDetail();
            }
            catch (error) {
                console.log(error)
                thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true)
            }
        });
    }
}

init();
