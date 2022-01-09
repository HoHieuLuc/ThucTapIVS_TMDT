
const statusButtonDOM = document.querySelector('#listBaoCaoByStatus');
const listBaoCaoDOM = document.querySelector('#baocao-list');

//Bắt sự kiện cho từng nút trong table
listBaoCaoDOM.addEventListener('click', (event) => {
    const el = event.target;
    if (el.classList.contains('btn')) {
        console.log("OK");
    }
})

// Expirement to catch data from table
let tdNode;


statusButtonDOM.addEventListener('change', async (event) => {
    const status = statusButtonDOM.value;
    document.title = "Sản phẩm trong kho ";
    const { data: { list_baocaos } } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/getbystatus/${status}`);
    renderData(list_baocaos);
    tdNode = document.querySelectorAll('td');
    console.log(tdNode);

});

//Render data
const renderData = (datas) => {
    //console.log(datas.length);
    const countRows = datas.length;
    // Nếu xem báo cáo vi phạm thì không có chức năng gì
    // Nếu xem báo cáo chưa duyệt thì có 2 chức năng, 1 là duyệt vi phạm, 2 là duyệt không vi phạm 
    // Nếu xem báo cáo không vi phạm thì  không có chức năng gì
    let chucNangElements = [];
    //const status = statusButtonDOM.value;
    const allBaoCaos = datas.map((data,index) => {
        const { ma_bao_cao, unameReceiver, unameSender, noi_dung, ngay_tao } = data;
//          console.log(data);
//          console.log(index);
        // Không biết tạo nhiều formData nó có lag hay không, nên tui tạm comment
        // const formData = new FormData();
        // formData.append('maBaoCao',ma_bao_cao);
        // formData.append('status',status);
        // formData.append('userName',unameSender);
        //Status =0 là chưa duyệt,
        // = 1 là duyệt nhưng không tăng cảnh báo chỉ nhắc nhở qua thông báo
        // 2 là duyệt và tăng cảnh báo, gửi thông báo cho người bị cảnh cáo,
        // cần làm modal hiển thị lên 
        // bây giờ học css selector, lấy các node trong cùng 1 div hoặc 1 thẻ tr ---Dùng HTML NodeList-, để lấy mã báo cáo, unameReceiver, và nội dung
        //dị thì chuc năng elment là 1 modal 
        // switch (status) {
        //     case '0' : chucNangElement = `
        //         <button href="${baseURL}api/v1/nhanvien/baocao/changestatus?maBaoCao=${ma_bao_cao}&status=-1&userName=${unameSender}">Duyệt vi phạm</a>
        //         <a href="${baseURL}api/v1/nhanvien/baocao/changestatus?maBaoCao=${ma_bao_cao}&status=1&userName=${unameSender}">Duyệt không vi phạm</a>
        //     `;
        // }
        //Tạo cái HTML DOM table rows collection đi #listBaoCaoTable
        return `
            <tr>
                <td>${ma_bao_cao}</td>
                <td>${unameSender}</td>
                <td><a href="${baseURL}store/${unameReceiver}">${unameReceiver}</a></td>
                <td>   ${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}
                <td>${noi_dung}</td>
                <td>
                    <div class="d-flex justify-content-evenly">
                       <button class="btn btn-primary" data-index="${index}">Chức năng ${index}</button>
                    </div>
                </td>
            </tr>`;
    }).join('');

    listBaoCaoDOM.innerHTML = allBaoCaos;
    console.log("overhere");
    tdNode = document.querySelectorAll('td');
    console.log(tdNode);
}

//Show list báo cáo lần đầu tiên
const showListBaoCao = async () => {
    const { data: { list_baocaos } } = await axios.get(`${baseURL}api/v1/nhanvien/baocao/getbystatus/0`);
    renderData(list_baocaos);
    
  
}

showListBaoCao();