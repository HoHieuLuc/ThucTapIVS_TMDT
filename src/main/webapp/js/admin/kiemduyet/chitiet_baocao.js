document.title = "Admin - Chi tiết báo cáo người dùng ";
//Lấy mã báo cáo trên đường dẫn
const params = window.location.pathname.split('/').slice(0);
const maBaoCao = params[params.length - 1];

//Các biến của chi tiết báo cáo
const maBaoCaoDOM = document.querySelector('#maBaoCao');
const unameSenderDOM = document.querySelector('#unameSender');
const unameReceiverDOM = document.querySelector('#unameReceiver');
const noiDungDOM = document.querySelector('#noiDung');
const statusDOM = document.querySelector('#status');

//Tạo chức năng tùy theo trạng Thái
const chucNangDOM = document.querySelector('#chucNang');



const showBaoCaoDetail = async () => {
    try {
        const { data: { chitiet_baocao } } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/${maBaoCao}`);
        // có avatar nữa
        const { ma_bao_cao, unameReceiver, unameSender, noi_dung,status } = chitiet_baocao[0];
        maBaoCaoDOM.textContent = ma_bao_cao;
        unameReceiverDOM.textContent = unameReceiver;
        unameSenderDOM.textContent = unameSender;
        noiDungDOM.textContent = noi_dung;
        //if status==0 statusDOM.textContent = `${status}`;
        switch (status) {
            case -3:
                statusDOM.textContent = `Vi phạm nặng nhất (khóa tài khoản)`;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-danger" data-status="-1">Xóa</button>
            `;
                break;
            case -2:
                statusDOM.textContent = `Vi phạm nặng (Cảnh cáo)`;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-danger" data-status="-1">Xóa</button>
            `;
                break;
            case 0:
                statusDOM.textContent = `Chưa duyệt`;
                chucNangDOM.innerHTML =`
                    <button type="button" class="btn btn-primary" data-status="0">Phục hồi vào kho</button>
                `;
                break;
            case -1:
                statusDOM.textContent = `Vi phạm nhẹ (Chỉ nhắc nhở)`;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-danger" data-status="-1">Xóa</button>
            `;
                break;
            case 1:
                statusDOM.textContent = `Không vi phạm`;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-success" data-status="2">Duyệt</button></div>
                <button type="button" class="btn btn-danger" data-status="0">Từ chối duyệt</button></div>
            `;
                break;
            case 2:
                statusDOM.textContent = `Bỏ qua`;
                chucNangDOM.innerHTML =`
                <button type="button" class="btn btn-primary" data-status="0">Đưa vào kho</button></div>
            `;
                break;
        }
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}
showBaoCaoDetail();


//Bắt sự kiện của từng nút để duyệt
chucNangDOM.addEventListener('click',async(event)=>{
    const target = event.target;
    const formData = new FormData();
    formData.append('maBaoCao',maBaoCao);
    // Người nhận báo cáo chính là người bị báo cáo
    formData.append('userName',unameReceiverDOM.textContent);
    //console.log(target.dataset);
    formData.append('status',target.dataset.status);
    try {
        await axios.post(`${baseURL}api/v1/nhanvien/baocao/changestatus`,formData)
    }
    catch (error) {
        console.log(error)
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true)
    }
    //Reload lại data chi tiết sản phẩm
    showBaoCaoDetail();
})
