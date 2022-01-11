const listThongBaoDOM = document.querySelector("#listThongBao");
const soThongBaoDOM = document.querySelector("#soThongBao");

const showThongBao = async (status) => {
    try {
        const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/user/thongbao/${status}`);
        const allThongBaos = thong_baos.map(data => {
            const { noi_dung, nguoi_gui, ngay_tao,status } = data;
            return `
                <li class="list-group-item d-flex justify-content-between align-items-start ${status} dropdown-item">
                    <div class="ms-2 me-auto">
                    <div class="fw-bold">${nguoi_gui}</div>
                        ${noi_dung}
                    </div>
                    <span class="badge bg-warning rounded-pill">
                        ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year} 
                    </span>
                </li>`;
        }).join('');
        listThongBaoDOM.innerHTML = allThongBaos;
    }
    catch (error) {
        console.log(error);
        thongBao(error.response.data.message, true);
    }
}


// Cho tạm số bất kì khác 0,999 để hiện tất cả thông báo
showThongBao(-1);

//Đánh dấu tất cả đã đọc
document.querySelector("#danhDauDaDoc").addEventListener('click', async() => {
    try {
        await axios.get(
            `${baseURL}api/v1/user/thongbao_seen`
        )
    } catch (error) {
        console.log(error);
       thongBao(error.response.data.message, true);
    }
})

//Mở list thông báo chưa đọc
document.querySelector("#listChuaDoc").addEventListener('click', () => {
    showThongBao(0);
})

//Mở list tất cả thông báo 
document.querySelector("#listAll").addEventListener('click', () => {
    showThongBao(-1);
})