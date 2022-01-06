const loaiSPListDOM = document.querySelector('#danhMucSP');
		loaiSPListDOM.textContent = "Loading..."
const showLoaiSPList = async () => {

    try {
        const { data: { loaiSanPhams } } = await axios.get(`${baseURL}api/v1/category_have_product`);
      //  console.log(loaiSanPhams);
		const allLoaiSanPhams = loaiSanPhams.map((loaiSanPham) => {
            const { maLoaiSanPham,tenLoaiSanPham } = loaiSanPham;
            return `
               <a class="dropdown-item" href="${baseURL}category/${maLoaiSanPham}">${tenLoaiSanPham}</a>
			   `;
        }).join('');
		
		loaiSPListDOM.innerHTML = allLoaiSanPhams;
    } catch (error) {
        console.log(error);
    }
}

showLoaiSPList();