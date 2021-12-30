const storeListDOM = document.querySelector('#storeList');
		storeListDOM.textContent = "Loading..."
const showstoreListDOM = async () => {

    try {
        const { data: { listStores } } = await axios.get(`${baseURL}/api/v1/store`);
        
	// 	const allLoaiSanPhams = loaiSanPhams.map((loaiSanPham) => {
    //         const { maLoaiSanPham,tenLoaiSanPham } = loaiSanPham;
    //         return `
    //            <a class="dropdown-item" href="${baseURL}/api/v1/sanpham/${maLoaiSanPham}">${tenLoaiSanPham}</a>
	// 		   `;
    //     }).join('');
		
	 	storeListDOM.innerHTML = console.log(listStores);
    } catch (error) {
        console.log(error);
    }
}

showstoreListDOM();