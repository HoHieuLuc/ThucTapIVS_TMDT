const sanPhamListDOM = document.querySelector('#listSanPham');

const showSanPhamList = async () => {
    sanPhamListDOM.textContent = 'Loading...';

    try {
        const { data: { sanphams } } = await axios.get(`./api/v1/sanpham/getall`);
        console.log(sanphams);
        const allSanPhams = sanphams.map((sanpham) => {
            const { tenSanPham, gia, anhSanPham } = sanpham;
            return `
                <div class="col-sm-3">
                <img src="./images/${anhSanPham}" class="img-responsive" style="width: 300px; height: 300px;" alt="${tenSanPham}">
                <p style="color:red;font-size:25px;font-family:poroto">${gia} VNĐ</p>
                <p>${tenSanPham}</p>
                <div class="card-footer">
                    <button type="button" class="btn btn-success"href="chitiet.html">Xem chi tiết</button>
                    <button type="button" class="btn btn-success float-right">Mua ngay</button>
                </div>
                </div>`;
        }).join('');
        sanPhamListDOM.innerHTML = allSanPhams;
    } catch (error) {
        console.log(error);
    }
}

showSanPhamList();

