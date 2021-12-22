const danhGiaSPListDom = document.querySelector('#danhGiaSPListDom');
const showDanhGiaSPList = async () => {
    try {
        const { data: danhGiaSPs } = await axios.get(`${baseURL}danhGiaSanPham/SP001`);
        console.log(danhGiaSPs);
        const allDanhGiaSPs = danhGiaSPs.danhSachDanhGia.map((danhGiaSP) => {
            const { ngay_sua, ngay_tao, noi_dung, so_sao, ten } = danhGiaSP;
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