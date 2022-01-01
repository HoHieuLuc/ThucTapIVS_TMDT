const sanPhamListDOM = document.querySelector('#sanpham-list');

const showSanPhamList = async () => {
    try {
        const { data: { sanphams } } = await axios.post(`${baseURL}api/v1/user/sanpham`);
        const allSanPhams = sanphams.map(sanpham => {
            const { maSanPham, tenSanPham, gia, soLuong, ngayDang, soLuongDaBan, xepHang, status } = sanpham;
            return `
                <tr>
                    <td>${tenSanPham}</td>
                    <td>${gia}</td>
                    <td>${soLuong}</td>
                    <td>${soLuongDaBan}</td>
                    <td>${ngayDang}</td>
                    <td>${xepHang ?? 0}</td>
                    <td>${status}</td>
                    <td>
                        <div class="d-flex justify-content-evenly">
                            <a href="./sanpham/${maSanPham}" class="">Chi tiết</a>
                        </div>
                    </td>
                </tr>`;
        }).join('');
        sanPhamListDOM.innerHTML = allSanPhams;
    }
    catch (error) {
        console.log(error);
    }
}

showSanPhamList();