
const statusButtonDOM = document.querySelector('#listBaoCaoByStatus');
const listBaoCaoDOM = document.querySelector('#baocao-list');
//Bắt sự kiện thẻ select
statusButtonDOM.addEventListener('change',async(event) => {
    const status = statusButtonDOM.value;
    document.title = "Sản phẩm trong kho ";
    const {data : {list_baocaos} } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/getbystatus/${status}`);
    renderData(list_baocaos);
});

//Render data
const renderData = (datas) => {
    // Nếu xem báo cáo vi phạm thì không có chức năng gì
    // Nếu xem báo cáo chưa duyệt thì có 2 chức năng, 1 là duyệt vi phạm, 2 là duyệt không vi phạm 
    // Nếu xem báo cáo không vi phạm thì  không có chức năng gì
    let chucNangElement = ``;
    const status = statusButtonDOM.value;
    const allBaoCaos = datas.map(data => {
        const { ma_bao_cao, unameReceiver, unameSender, noi_dung, ngay_tao } = data;
        switch (status) {
            case '0' : chucNangElement = `
                <a href="${baseURL}api/v1/nhanvien/baocao/changestatus?maBaoCao=${ma_bao_cao}&status=${status}">Duyệt vi phạm</a>
                <a href="${baseURL}api/v1/nhanvien/baocao/changestatus?maBaoCao=${ma_bao_cao}&status=${status}">Duyệt không vi phạm</a>
            `;
        }
        return `
            <tr>
                <td>${ma_bao_cao}</td>
                <td>${unameSender}</td>
                <td><a href="${baseURL}store/${unameReceiver}">${unameReceiver}</a></td>
                <td>   ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}
                <td>${noi_dung}</td>
                <td>
                    <div class="d-flex justify-content-evenly">
                        ${chucNangElement}
                    </div>
                </td>
            </tr>`;
    }).join('');
    listBaoCaoDOM.innerHTML = allBaoCaos;
}

//Show list báo cáo lần đầu tiên
const showListBaoCao = async () => {
    const {data : {list_baocaos} } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/getbystatus/0`);
    renderData(list_baocaos);
}

showListBaoCao();