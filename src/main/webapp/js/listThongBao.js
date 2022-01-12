const listThongBaoDOM = document.querySelector("#listThongBao");
const soThongBaoDOM = document.querySelector("#soThongBao");
let danhDauDaDocCuThe;
const thongbaoTableDOM = document.querySelector("#thongbao-table");

const showThongBao = async (status) => {
    try {
        const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/thongbao/${status}`);
        if (thong_baos.length === 0) {
            listThongBaoDOM.innerHTML = `Không có thông báo nào`;
            return;
        }
        //Map cho thông báo gần đây
        const allThongBaos = thong_baos.map(data => {

            const { noi_dung, nguoi_gui, ngay_tao, status, ma_tb } = data;
            //Nếu phát hiện thông báo này chưa đọc, tạo nút đã đọc tương ứng
            if (status.includes("bg-secondary") || status.includes("bg-body")) danhDauDaDocCuThe = `<button type="button" class="btn btn-primary"  data-id="${ma_tb}" >Đã đọc</button>`;
            else danhDauDaDocCuThe = ``;
            return `
                <li class="list-group-item d-flex justify-content-between align-items-start ${status} dropdown-item">
                    <div class="ms-2 me-auto">
                    <div class="fw-bold">${nguoi_gui}</div>
                        ${noi_dung}
                    </div>
                    ${danhDauDaDocCuThe}
                    <span class="badge bg-warning rounded-pill">
                        ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} 
                    </span>
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

document.querySelector("#danhDauDaDoc").addEventListener('click', async () => {
    try {
        // -9999 đánh dấu toàn bộ đã đọc
        const { data: { message } } = await axios.get(`${baseURL}api/v1/thongbao/seen/-9999`);
        thongBao(message, false);

    } catch (error) {
        console.log(error);
    }
    showThongBao(-1);
    showSoThongBao();
})




//Giữ cho dropdown menu không bị đóng khi nhấn vô mấy button
$('body > div.wrapper > nav > ul.navbar-nav.ml-auto > li.nav-item.dropdown.me-2 > ul').on({
    "click": function (e) {
        e.stopPropagation();
    }
});

// Thêm sự kiện cập nhật đã đọc cho từng thông báo trong dropdown menu
listThongBaoDOM.addEventListener('click', async (event) => {
    const target = event.target;
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
})


//Nếu phát hiện element có id="thongbao-table"
if (thongbaoTableDOM) {
    const showThongBaoTable = async (status) => {
        try {
            const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/thongbao/${status}`);
            if (thong_baos.length === 0) {
                thongbaoTableDOM.innerHTML = `Không có thông báo nào`;
                return;
            }

            const allThongBaos = thong_baos.map(data => {

                const { noi_dung, nguoi_gui, ngay_tao, status, ma_tb } = data;

                //Nếu phát hiện thông báo này chưa đọc, tạo nút đã đọc tương ứng
                if (status.includes("bg-secondary") || status.includes("bg-body")) danhDauDaDocCuThe = `<button type="button" class="btn btn-primary"  data-id="${ma_tb}" >Đã đọc</button>`;
                else danhDauDaDocCuThe = ``;

                return `
                    <tr>
                            <td>${noi_dung}</td>
                            <td>${nguoi_gui}</td>
                            <td>${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} </td>
                            <td>${danhDauDaDocCuThe}</td>
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
}

