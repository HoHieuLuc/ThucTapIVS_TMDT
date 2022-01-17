const formThongKeDOM = document.querySelector('.formThongKe');
const tuyChonThongKeDOM = document.querySelector('.tuyChonThongKe');
const topSanPhamBanChayDOM = document.querySelector('#topSanPhamBanChay');
const ctx = document.getElementById('pieChart').getContext('2d');
let pieChart;

const buildThongKeChart = (thongKe) => {
    const dataThongKe = [];
    const labels = [];
    const colors = [];
    //Trường hợp khoảng ngày đó không có dữ liệu, đưa data array về  [0,0,0,0]
    if (thongKe.length == 0) {
        thongBao("Tình trạng đặt hàng không có dữ liệu thống kê trong khoảng thời gian này", true);
        dataThongKe.push(0);
        labels.push("Chưa có dữ liệu");
        colors.push("#ff0000");
    }
    else {
        thongKe.forEach(item => {
            if (item.status === -1) {
                dataThongKe.push(item.so_luong);
                labels.push('Bị hủy');
                colors.push('rgb(255,0,0)');
            }
            else if (item.status === 0) {
                dataThongKe.push(item.so_luong);
                labels.push('Đang chờ tiếp nhận');
                colors.push('rgb(255,255,0)');
            }
            else if (item.status === 1) {
                dataThongKe.push(item.so_luong);
                labels.push('Đang vận chuyển');
                colors.push('rgb(88, 130, 255)');
            }
            else if (item.status === 2) {
                dataThongKe.push(item.so_luong);
                labels.push('Giao hàng thành công');
                colors.push('rgb(1, 255, 1)');
            }
        });
    }

    //Dữ liệu
    const data = {
        labels: labels,
        datasets: [{
            label: 'Tình Trạng Đơn Đặt Hàng',
            data: dataThongKe,
            backgroundColor: colors,
            hoverOffset: 4
        }]
    };

    //Cấu hình
    const config = {
        type: 'pie',
        data: data,
        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'Tình Trạng Chi Tiết Đặt Hàng',
                    fontSize: 15
                }
            }
        }
    };
    if (pieChart) {
        pieChart.destroy();
    }
    pieChart = new Chart(ctx, config);
}

const thongKeTinhTrangDonHang = async (type, tuNgay, denNgay) => {
    try {
        const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/nhanvien/thongke/${type}`, {
            params: {
                tuNgay,
                denNgay,
            }
        });
        buildThongKeChart(thong_ke);
    }
    catch (error) {
        console.log(error)
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

thongKeTinhTrangDonHang(1, null, null);

const thongKeDonGian = async () => {
    try {
        const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/nhanvien/thongke/0`);
        document.querySelector("#soDonHang").innerHTML = thong_ke.so_don_dat_hang;
        document.querySelector("#soSanPham").innerHTML = thong_ke.so_san_pham;
        document.querySelector("#soNguoiDung").innerHTML = thong_ke.so_thanh_vien;
        document.querySelector("#soDanhGia").innerHTML = thong_ke.so_danh_gia;
    }
    catch (error) {
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

tuyChonThongKeDOM.addEventListener('change', (event) => {
    if (event.target.value === 'tuychinh') {
        formThongKeDOM.classList.remove('d-none');
    } else {
        formThongKeDOM.classList.add('d-none');
        thongKeTinhTrangDonHang(1, null, null);
    }
});

formThongKeDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formData = new FormData(formThongKeDOM);
    const tuNgay = formData.get('tuNgay');
    const denNgay = formData.get('denNgay');
    thongKeTinhTrangDonHang(1, tuNgay, denNgay);
});

const showTop10SanPhamBanChay = async () => {
    try {
        const { data: { thong_kes } } = await axios.get(`${baseURL}api/v1/nhanvien/thongke/2`);
        console.log(thong_kes);
        const listThongKe = thong_kes.map((thong_ke) => {
            const { ten_san_pham, ten_loai_sp, so_luot_mua } = thong_ke;
            return `
                <li class="item">            
                    <div class="product">
                        <h6>${ten_loai_sp}</h6>
                        <span class="badge badge-success float-right m-1">${so_luot_mua} lượt mua</span>
                        <span class="product-description">
                        ${ten_san_pham}
                        </span>
                    </div>
                </li>
            `;
        }).join('');
        topSanPhamBanChayDOM.innerHTML = listThongKe;
    }
    catch (error) {
        console.log(error);
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

thongKeDonGian();
showTop10SanPhamBanChay();