const listThongBaoDOM = document.querySelector("#listThongBao");
const soThongBaoDOM = document.querySelector("#soThongBao");

const showThongBao = async (status) => {
    try {
        const { data: { thong_baos } } = await axios.get(`${baseURL}api/v1/user/thongbao/${status}`);
        listThongBaoDOM.innerHTML ="Không có thông báo nào";
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
        //thongBao(error.response.data.message, true);
    }
}

// Hiển thị số thông báo , status = 999
const showSoThongBao = async() => {
    try {
        const { data: { chua_doc } } = await axios.get(`${baseURL}api/v1/user/thongbao/${999}`);
        document.querySelector("#soThongBao").innerHTML = chua_doc;
    }
    catch (error) {
        console.log(error);
    }
}
showSoThongBao();


// Cho tạm số bất kì khác 0,999 để hiện tất cả thông báo
showThongBao(-1);

document.querySelector("#danhDauDaDoc").addEventListener('click', async() => {
    try {
        await axios.get(
            `${baseURL}api/v1/user/seenall`
        )
    } catch (error) {
        console.log(error);
       thongBao(error.response.data.message, true);
    }
    showThongBao(-1);
    showSoThongBao();
})

//Mở list thông báo chưa đọc, status = 0
document.querySelector("#listChuaDoc").addEventListener('click', () => {
    showThongBao(0);
})

//Mở list tất cả thông báo 
document.querySelector("#listAll").addEventListener('click', () => {
    showThongBao(-1);
})

//Giữ cho dropdown menu không bị đóng khi nhấn vô mấy button
$('body > div.wrapper > nav > ul.navbar-nav.ml-auto > li.nav-item.dropdown.me-2 > ul').on({
	"click":function(e){
      e.stopPropagation();
    }
});



