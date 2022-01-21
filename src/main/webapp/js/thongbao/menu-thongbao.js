const listThongBaoDOM = document.querySelector("#listThongBao");
const soThongBaoDOM = document.querySelector("#soThongBao");
var thongBaoCardDOM = document.querySelector("#thongbao-table");

const showMenuThongBao = async (_status) => {
    try {
        const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/thongbao/${_status}`);
        if (thong_baos.length === 0) {
            listThongBaoDOM.innerHTML = `Không có thông báo nào`;
            return;
        }
        //Map cho thông báo gần đây
        const allThongBaos = thong_baos.map(data => {
            const { noi_dung, nguoi_gui, ngay_tao, status, ma_tb } = data;
            const bgColor = status === false ? "bg-secondary text-white" : "bg-light text-black";
            let danhDauDaDocCuThe = "";
            //Nếu phát hiện thông báo này chưa đọc, tạo nút đã đọc tương ứng
            if (!status) {
                danhDauDaDocCuThe = `
                    <button type="button" class="seen-btn align-self-center btn btn-primary btn-sm rounded-circle" data-id="${ma_tb}">
                        <i class="fas fa-check"></i>
                    </button>
                `;
            }
            else {
                danhDauDaDocCuThe = `
                    <button type="button" class="align-self-center btn btn-sm opacity-0">
                        <i class="fas fa-check"></i>
                    </button>
                `;
            }
            return `
                <li class="list-group-item d-flex justify-content-between align-items-start ${bgColor} dropdown-item">
                    <div class="ms-2 me-auto w-100">
                        <div class="d-flex">
                            <div class="fw-bold me-2">${nguoi_gui}</div>
                            <span class="badge ms-auto bg-warning rounded-pill">
                                ${ngay_tao} 
                            </span>
                        </div>
                        <div style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width:15em;">
                            ${noi_dung}
                        </div>
                    </div>
                    ${danhDauDaDocCuThe}
                </li>
            `;
        }).join('');
        listThongBaoDOM.innerHTML = allThongBaos;
    }
    catch (error) {
        console.log(error);
        //thongBao(error.response.data.message, true);
    }
}

// Hiển thị số thông báo , status = 999
const showSoThongBao = async () => {
    try {
        const { data: { chua_doc } } = await axios.get(`${baseURL}api/v1/thongbao/${999}`);
        const soThongBaoChuaDoc = chua_doc > 9 ? "9+" : chua_doc;
        document.querySelector("#soThongBao").textContent = soThongBaoChuaDoc;
    }
    catch (error) {
        console.log(error);
    }
}
showSoThongBao();

// hiển thị top 5 thông báo gần đây
showMenuThongBao(-999);

const danhDauDaDoc = async (id) => {
    try {
        const { data: { message } } = await axios.get(`${baseURL}api/v1/thongbao/seen/${id}`);
        thongBao(message, false);
        showMenuThongBao(-999);
        if(thongBaoCardDOM){
            showThongBaoCard(-1);
        }
        showSoThongBao();
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

// xóa thông báo
const xoaThongBao = async (id) => {
    try {
        const { data: { message } } = await axios.delete(`${baseURL}api/v1/thongbao/delete/${id}`);
        thongBao(message);
        showMenuThongBao(-1);
        showSoThongBao();
    } catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}

const thongBaoMenuDOM = document.querySelector("#thongBaoMenu");

// Thêm sự kiện đánh dấu đã đọc cho thông báo trên menu
thongBaoMenuDOM.addEventListener('click', async (event) => {
    const target = event.target;
    if (target.classList.contains('seen-btn')) {
        const id = target.dataset.id;
        await danhDauDaDoc(id);
    }
});