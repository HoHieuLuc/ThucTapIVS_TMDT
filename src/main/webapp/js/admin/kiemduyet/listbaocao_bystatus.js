
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
    const allBaoCaos = datas.map(data => {
        const { ma_bao_cao, unameReceiver, unameSender, noi_dung, ngay_tao } = data;
        return `
            <tr>
                <td>${ma_bao_cao}</td>
                <td>${unameSender}</td>
                <td><a href="${baseURL}store/${unameReceiver}" class="">${unameReceiver}</a></td>
                <td>   ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}
                <td>${noi_dung}</td>
                <td>
                    <div class="d-flex justify-content-evenly">
                        <a href="${baseURL}${ma_bao_cao}" class="">Chi tiết</a>
                    </div>
                </td>
            </tr>`;
    }).join('');
    listBaoCaoDOM.innerHTML = allBaoCaos;
}

//Show list báo cáo lần đầu tiên
const showListBaoCao = () => {
    renderData("Data from axios " + statusButtonDOM.value);
}

showListBaoCao();