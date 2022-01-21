var thongBaoCardDOM = document.querySelector("#thongbao-table");
const thongBaoFilterDOM = document.querySelector("#thongbao-filter");

const showThongBaoCard = async (_status) => {
    try {
        const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/thongbao/${_status}`);
        if (thong_baos.length === 0) {
            thongBaoCardDOM.innerHTML = `Không có thông báo nào`;
            return;
        }
        const allThongBaos = thong_baos.map(data => {
            const { noi_dung, nguoi_gui, ngay_tao, status, ma_tb } = data;
            const bgColor = status === false ? "bg-primary" : "bg-secondary";
            let danhDauDaDocCuThe = "";
            if (!status) {
                danhDauDaDocCuThe = `
                    <button type="button" class="seen-btn btn btn-tool text-white" data-id="${ma_tb}">
                        <i class="fas fa-check"></i>
                    </button>
                `;
            } else {
                danhDauDaDocCuThe = ``;
            }
            return `
                <div class="card" style="display: block;">
                    <div class="card-header ${bgColor}">
                        <h3 class="card-title text-white">${nguoi_gui}, ${ngay_tao}</h3>
                        <div class="card-tools">
                            ${danhDauDaDocCuThe}
                            <button type="button" class="btn btn-tool text-white" data-card-widget="collapse">
                                <i class="fas fa-minus"></i>
                            </button>
                            <button type="button" data-id=${ma_tb} class="xoa-btn btn btn-tool text-white">
                                <i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <div class="card-body p-0" style="display: block;">
                        <div class="tlt-thong-bao pl-2 pr-2">
                            ${noi_dung}
                        </div>
                    </div>
                </div>
            `;
        }).join('');
        thongBaoCardDOM.innerHTML = allThongBaos;
    }
    catch (error) {
        console.log(error);
        //thongBao(error.response.data.message, true);
    }
}
showThongBaoCard(-1);

// Bộ lọc thông báo
thongBaoFilterDOM.addEventListener('change', () => {
    showThongBaoCard(thongBaoFilterDOM.value);
});

thongBaoCardDOM.addEventListener('click', async (event) => {
    const eventTarget = event.target;
    if (eventTarget.classList.contains('seen-btn')) {
        const id = eventTarget.dataset.id;
        await danhDauDaDoc(id);
        showThongBaoCard(-1);
    }
    if (eventTarget.classList.contains('xoa-btn')) {
        const id = eventTarget.dataset.id;
        await xoaThongBao(id);
        showThongBaoCard(-1);
    }
});