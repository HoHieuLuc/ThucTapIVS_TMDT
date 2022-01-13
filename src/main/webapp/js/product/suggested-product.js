const sanPhamCungShopWrapperDOM = document.querySelector('.sanPhamCungShopWrapper');

const getSanPhamCuaShop = async (username, maSanPham, maLoaiSanPham, maLoaiCha) => {
    try {
        const {
            data: { products },
        } = await axios.get(`${baseURL}api/v1/store/${username}/products/suggestion`, {
            params: {
                maSanPham,
                maLoaiSanPham,
                maLoaiCha
            },
        });
        if (products.length === 0) {
            return;
        }
        const allProducts = products.map((product) => {
            const { ma_san_pham, ten_san_pham, gia, anh } = product;
            const giaVND = gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <div class="swiper-slide">
                    <div>
                        <a 
                            href="${baseURL}sanpham/${ma_san_pham}"
                            class="d-flex"    
                        >
                            <img 
                                src="${baseURL}images/product/${anh}" 
                                alt="${ten_san_pham}"
                                class="img-fluid tlt-thumbnail mx-auto my-auto"
                            >
                        </a>
                        <a 
                            href="${baseURL}sanpham/${ma_san_pham}" 
                            style="width: 15em"
                            class="fs-5 text-decoration-none tlt-overflow-ellipsis text-dark"
                            title="${ten_san_pham}"
                        >
                            ${ten_san_pham}
                        </a>
                        <div class="text-center">${giaVND}</div>
                    </div>
                </div>
            `;
        }).join('');
        sanPhamCungShopWrapperDOM.innerHTML = allProducts;
        new Swiper(".sanPhamCungShopSwiper", {
            slidesPerView: 3,
            spaceBetween: 3,
            freeMode: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });
        document.querySelector('.sanPhamKhacCuaShopDiv').classList.remove('d-none');
    } catch (error) {
        console.log(error);
    }
};

const sanPhamGoiYWrapperDOM = document.querySelector('.sanPhamGoiYWrapper');

const getSanPhamGoiY = async(maSanPham) => {
    try {
        const {
            data: { sanPhams },
        } = await axios.get(`${baseURL}api/v1/sanpham/suggestion`, {
            params: {
                maSanPham,
            },
        });
        if (sanPhams.length === 0) {
            return;
        }
        const allProducts = sanPhams.map((product) => {
            const { ma_san_pham, ten_san_pham, gia, anh } = product;
            const giaVND = gia.toLocaleString("vi-VN", {
                style: "currency",
                currency: "VND",
            });
            return `
                <div class="swiper-slide">
                    <div>
                        <a 
                            href="${baseURL}sanpham/${ma_san_pham}"
                            class="d-flex"    
                        >
                            <img 
                                src="${baseURL}images/product/${anh}" 
                                alt="${ten_san_pham}"
                                class="img-fluid tlt-thumbnail mx-auto my-auto"
                            >
                        </a>
                        <a 
                            href="${baseURL}sanpham/${ma_san_pham}" 
                            style="width: 15em"
                            class="fs-5 text-decoration-none tlt-overflow-ellipsis text-dark"
                            title="${ten_san_pham}"
                        >
                            ${ten_san_pham}
                        </a>
                        <div class="text-center">${giaVND}</div>
                    </div>
                </div>
            `;
        }).join('');
        sanPhamGoiYWrapperDOM.innerHTML = allProducts;
        new Swiper(".sanPhamGoiYSwiper", {
            slidesPerView: 3,
            spaceBetween: 3,
            freeMode: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
        });
        document.querySelector('.sanPhamGoiYDiv').classList.remove('d-none');
    } catch (error) {
        console.log(error);
    }
}