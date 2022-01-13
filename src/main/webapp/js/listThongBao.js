const listThongBaoDOM = document.querySelector("#listThongBao");
const soThongBaoDOM = document.querySelector("#soThongBao");
let danhDauDaDocCuThe;
const thongbaoTableDOM = document.querySelector("#thongbao-table");
const thongBaoFilterDOM = document.querySelector("#thongbao-filter");

const showThongBao = async (_status) => {
    try {
        const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/thongbao/${_status}`);
        if (thong_baos.length === 0) {
            listThongBaoDOM.innerHTML = `Không có thông báo nào`;
            return;
        }
        //Map cho thông báo gần đây
        const allThongBaos = thong_baos.map(data => {

            const { noi_dung, nguoi_gui, ngay_tao, status, ma_tb } = data;
            //Nếu phát hiện thông báo này chưa đọc, tạo nút đã đọc tương ứng
            if (status.includes("bg-secondary") || status.includes("bg-body")) {
                danhDauDaDocCuThe = `
                    <button type="button" class="seen-btn btn btn-primary btn-sm rounded-circle" data-id="${ma_tb}">
                        <i class="fas fa-check"></i>
                    </button>
                `;
            }
            else {
                danhDauDaDocCuThe = ``;
            }
            return `
                <li class="list-group-item d-flex justify-content-between align-items-start ${status} dropdown-item">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">${nguoi_gui}</div>
                        <div style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width:123px;">
                            ${noi_dung}
                        </div>
                    </div>
                    <span class="badge bg-warning rounded-pill">
                        ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} 
                    </span>
                    ${danhDauDaDocCuThe}
                </li>`;
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
        document.querySelector("#soThongBao").innerHTML = chua_doc;
    }
    catch (error) {
        console.log(error);
    }
}
showSoThongBao();


// Cho tạm số bất kì khác 0,999 để hiện tất cả thông báo
showThongBao(-1);

const thongBaoMenuDOM = document.querySelector("#thongBaoMenu");

// Thêm sự kiện cập nhật đã đọc cho từng thông báo trong dropdown menu
thongBaoMenuDOM.addEventListener('click', async (event) => {
    const target = event.target;
    if (target.classList.contains('seen-btn')) {
        const id = target.dataset.id;
        try {
            const { data: { message } } = await axios.get(`${baseURL}api/v1/thongbao/seen/${id}`);
            thongBao(message, false);

        } catch (error) {
            console.log(error);
            thongBao(error.response.data.message, true);
        }
        showThongBao(-1);
        showSoThongBao();
    }
})


//Nếu phát hiện element có id="thongbao-table"
if (thongbaoTableDOM) {
    const showThongBaoTable = async (_status) => {
        try {
            const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/thongbao/${_status}`);
            if (thong_baos.length === 0) {
                thongbaoTableDOM.innerHTML = `Không có thông báo nào`;
                return;
            }

            const allThongBaos = thong_baos.map(data => {

                const { noi_dung, nguoi_gui, ngay_tao, status, ma_tb } = data;

                //Nếu phát hiện thông báo này chưa đọc, tạo nút đã đọc tương ứng
                if (status.includes("bg-secondary") || status.includes("bg-body")) {
                    danhDauDaDocCuThe = `
                        <button type="button" class="btn btn-outline-primary" data-id="${ma_tb}">
                            <i class="fas fa-check"></i>
                        </button>
                    `;
                } else {
                    danhDauDaDocCuThe = ``;
                }

                return `
                    <tr>
                        <td>${noi_dung}</td>
                        <td>${nguoi_gui}</td>
                        <td>${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} </td>
                        <td>
                            <div class="d-flex justify-content-evenly">
                                ${danhDauDaDocCuThe}
                            </div>
                        </td>
                    </tr>
                    `;

            }).join('');
            thongbaoTableDOM.innerHTML = allThongBaos;
        }
        catch (error) {
            console.log(error);
            //thongBao(error.response.data.message, true);
        }
    }
    showThongBaoTable(-1);

    // Bộ lọc thông báo
    thongBaoFilterDOM.addEventListener('change', () => {
        showThongBaoTable(thongBaoFilterDOM.value);
    })
}




