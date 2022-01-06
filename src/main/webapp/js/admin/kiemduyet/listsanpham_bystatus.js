//Nút hiển thị sản phẩm mới vô kho,yêu cầu kiểm duyệt,đã duyệt, và bị xóa (hide)
const listTrongKhoDOM = document.querySelector("#trongKho");
const listYeuCauDuyetDOM = document.querySelector("#yeuCauDuyet");
const listDaDuyetKhoDOM = document.querySelector("#DaDuyet");
const listBiXoaDOM = document.querySelector("#biXoa");
//Khu vực danh sách sản phẩm thẻ <div>
const listSanPhamDOM = document.querySelector("#listSanPham");

//Render lại danh sách sản phẩm đúng với status 
listTrongKhoDOM.addEventListener('click', async () => {
    const {data : {sanphams} } = await axios.post(`${baseURL}api/v1/sanpham/getbystatus/0`);
    console.log(sanphams);
    userNameErrorMessage.innerHTML = "<p>Test thử nút listTrongKho</p>";
})

