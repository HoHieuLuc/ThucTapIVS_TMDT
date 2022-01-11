const listThongBaoDOM = document.querySelector("#listThongBao");
const soThongBaoDOM = document.querySelector("#soThongBao");

const showThongBao = async (status) => {
    try {
        const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/user/thongbao/${status}`);
        // có avatar nữa
        const { ma_bao_cao, unameReceiver, unameSender, noi_dung, status } = thong_baos;
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
// Cho tạm số bất kì khác 0,999 để hiện tất cả thông báo
showThongBao(-1);
