const sanPhamListDOM = document.querySelector('#listSanPham');

const showSanPhamList = async () => {
    sanPhamListDOM.textContent = 'Loading...';

    try {
        const { data: { sanphams } } = await axios.get(`./api/v1/sanpham/getall`);
        console.log(sanphams);
        const allSanPhams = sanphams.map((sanpham) => {
            const { maSanPham,tenSanPham, gia, anhSanPham } = sanpham;
            return `
                <div class="col-sm-3">
                  <img src="${baseURL}images/product/${anhSanPham}" class="img-responsive" style="width: 200px; height: 200px;" alt="${tenSanPham}">
                  <p style="color:red;font-size:25px;font-family:poroto">${gia} VNĐ</p>
                  <p>${tenSanPham}</p>
                  <div class="card-footer">
                    <a href="${baseURL}sanpham/${maSanPham}">
                      <button type="button" class="btn btn-success">Xem chi tiết</button>
                    </a>
                    <a href="#">
                      <button type="button" class="btn btn-success float-right">Mua ngay</button>
                    </a>
                  </div>
                </div>`;
        }).join('');
        sanPhamListDOM.innerHTML = allSanPhams;
    } catch (error) {
        console.log(error);
    }
}

showSanPhamList();

