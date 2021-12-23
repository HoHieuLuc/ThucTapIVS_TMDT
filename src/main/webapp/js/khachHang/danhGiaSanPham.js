const danhGiaSPListDom = document.querySelector('#danhGiaSPListDom');
const maSP_hiddenDom = document.querySelector('#maSP_hiddenDom');
const params = window.location.pathname.split('/').slice(0);
const maSanPham = params[params.length - 1];
console.log(params);
console.log(maSanPham);

const showDanhGiaSPList = async () => {
    try {
        const { data: danhGiaSPs } = await axios.get(`${baseURL}danhGiaSanPham/${maSanPham}`);
        console.log(danhGiaSPs);
        const allDanhGiaSPs = danhGiaSPs.danhSachDanhGia.map((danhGiaSP) => {
            const { ngay_sua, ngay_tao, noi_dung, so_sao, ten } = danhGiaSP;
            //in ra icon ngôi sao đánh giá
            var so_sao_html = ` `;
            for (let i = 0; i <so_sao; i++) {
                 so_sao_html = `<span>&#9733;</span>` + so_sao_html;
              } 
            return `
            <div class="comment mt-4 text-justify float-left"> <img src="https://i.imgur.com/yTFUilP.jpg"
                    alt="avatar" class="rounded-circle" width="40" height="40">
                <h4>${ten}</h4> <span>${ngay_sua}</span> <br>
                     ${so_sao_html}
                <p>${noi_dung}</p>
            </div>
            `
        }).join(' ');
        danhGiaSPListDom.innerHTML = allDanhGiaSPs;
    } catch (error) {
        console.log(error);
    }
}
showDanhGiaSPList();

const formDOM = document.querySelector('#formDanhGiaSanPham');
//const noiDungErrorMessage = document.querySelector('#username_error');

const submitDanhGiaSP = async () => {
    const formData = new FormData(formDOM);
    formData.append("maSanPham",maSanPham);
    //Thực hiện request
    try {
        await axios.post(`../danhGiaSanPhamSubmit`, formData);
        // Display the key/value pairs of form data
            for (var pair of formData.entries()) {
                console.log(pair[0]+ ', ' + pair[1]); 
            }
       // window.location.href = "./";  
    } catch (error) {
        const data = error.response.data;
        console.log(data);
        noiDungErrorMessage.textContent = data.username ?? "";
    }
}

formDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    submitDanhGiaSP();
});