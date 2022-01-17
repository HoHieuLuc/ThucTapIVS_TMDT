const sanPhamYeuThichDOM = document.querySelector('.sanPhamYeuThich');

const showSanPhamYeuThich = async () => {
    try {
        const { data: { sanPhamYeuThich } } = await axios.get(`${baseURL}api/v1/fav`);
        if(sanPhamYeuThich.length === 0){
            sanPhamYeuThichDOM.innerHTML = `
                <div class="text-center">
                    <h3>Bạn không có sản phẩm yêu thích nào</h3>
                    <a href="${baseURL}" class="btn btn-primary">Về trang chủ</a>
                </div>
            `;
            return;
        }
        const sanPhamsHTML = sanPhamYeuThich.map(sanPham => {
            const { ten_san_pham, anh, gia, ma_san_pham } = sanPham;
            const donGiaVND = gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <div class="col-6 col-md-4 col-lg-3 col-xl-3 mb-3">
                    <div class="card">
                        <a href="${baseURL}sanpham/${ma_san_pham}">
                            <div class="d-flex border-bottom">
                                <img 
                                    class="card-img-top img-fluid tlt-thumbnail mx-auto my-auto" 
                                    src="${baseURL}images/product/${anh}"
                                    alt="${ten_san_pham}"
                                >
                            </div>
                        </a>
                        <div class="card-body">
                            <a 
                                href="${baseURL}sanpham/${ma_san_pham}" 
                                class="text-dark tlt-overflow-ellipsis" 
                                title="${ten_san_pham}"
                            >
                                ${ten_san_pham}
                            </a>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button 
                                        data-masanpham="${ma_san_pham}" 
                                        class="add-to-cart-btn btn btn-sm btn-outline-secondary"
                                    >
                                        <i class="fas fa-shopping-cart"></i>
                                    </button>
                                    <button 
                                        data-masanpham="${ma_san_pham}"
                                        class="remove-from-fav-btn btn btn-sm btn-outline-secondary"
                                    >
                                        <i class="fas fa-heart-broken"></i>
                                    </button>
                                </div>
                                <h6 
                                    title="${donGiaVND}"
                                    class="font-weight-bold tlt-overflow-ellipsis text-dark"
                                >
                                    ${donGiaVND}
                                </h6>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }).join('');
        sanPhamYeuThichDOM.innerHTML = sanPhamsHTML;
    } catch (error) {
        console.log(error);
    }
}

showSanPhamYeuThich();