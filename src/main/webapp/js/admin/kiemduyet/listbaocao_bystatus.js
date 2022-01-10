
const statusButtonDOM = document.querySelector('#listBaoCaoByStatus');
const listBaoCaoDOM = document.querySelector('#baocao-list');



statusButtonDOM.addEventListener('change', async () => {
    const status = statusButtonDOM.value;
    document.title = "Sản phẩm trong kho ";
    const { data: { list_baocaos } } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/getbystatus/${status}`);
    renderData(list_baocaos);

});

//Render data  api/v1/nhanvien/baocao/{maBaoCao}
const renderData = (datas) => {
    const allBaoCaos = datas.map(data => {
        const { unameReceiver, unameSender, ngay_tao,ma_bao_cao } = data;
        return `
            <tr>
                <td>${unameSender}</td>
                <td><a href="${baseURL}store/${unameReceiver}">${unameReceiver}</a></td>
                <td>   ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}
                <td>
                    <div class="d-flex justify-content-evenly">
                        <a href="${baseURL}admin/baocao/${ma_bao_cao}" class="">Chi tiết</a>
                    </div>
                </td>
            </tr>`;
    }).join('');
    listBaoCaoDOM.innerHTML = allBaoCaos;
}

//Show list báo cáo lần đầu tiên
const showListBaoCao = async () => {
    const { data: { list_baocaos } } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/getbystatus/0`);
    renderData(list_baocaos);
    
}

showListBaoCao();