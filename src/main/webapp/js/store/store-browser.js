const StoreListDOM = document.querySelector('#store');
		StoreListDOM.textContent = "Loading..."
const showStoreListDom = async () => {

    try {
        const { data: { listStores } } = await axios.get(`${baseURL}/api/v1/loaisanpham`);
        console.log(listStores);
	// 	const allLoaiSanPhams = loaiSanPhams.map((loaiSanPham) => {
    //         const { maLoaiSanPham,tenLoaiSanPham } = loaiSanPham;
    //         return `
    //            <a class="dropdown-item" href="${baseURL}/api/v1/sanpham/${maLoaiSanPham}">${tenLoaiSanPham}</a>
	// 		   `;
    //     }).join('');
		
	 	StoreListDOM.innerHTML = allLoaiSanPhams;
    } catch (error) {
        console.log(error);
    }
}

showStoreListDom();