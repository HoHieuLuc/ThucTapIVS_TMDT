const sanPhamListDOM = document.querySelector('#listSanPham');

const showSanPhamList = async () => {
    sanPhamListDOM.textContent = 'Loading...';

    try {
        const { data: { sanphams } } = await axios.get(`${baseURL}api/v1/sanpham/getall`);
        console.log(sanphams);
        const allSanPhams = sanphams.map((sanpham) => {
            const { maSanPham,tenSanPham, gia, anhSanPham } = sanpham;
            return `
                <div class="col-sm-3 mb-2">
                  <img 
                    src="${baseURL}images/product/${anhSanPham}" 
                    class="img-responsive" 
                    style="object-fit: contain; width: 100%; height: 12rem;" 
                    alt="${tenSanPham}"
                  >
                  <p style="color:red;font-size:25px;font-family:poroto">${gia} VNĐ</p>
                  <p>${tenSanPham}</p>
                  <div class="d-flex gap-2">
                    <a href="${baseURL}sanpham/${maSanPham}" class="btn btn-block w-100 btn-success">
                      Chi tiết
                    </a>
                    <button type="button" data-masanpham="${maSanPham}" class="add-to-cart-btn w-100 btn btn-block btn-warning">
                      <i class="fas fa-cart-plus"></i>
                    </button>
                  </div>
                </div>`;
        }).join('');
        sanPhamListDOM.innerHTML = allSanPhams;
    } catch (error) {
        console.log(error);
    }
}

showSanPhamList();

