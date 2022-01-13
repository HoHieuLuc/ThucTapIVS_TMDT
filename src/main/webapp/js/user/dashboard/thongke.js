const soDonDatHangChartDOM = document.querySelector('.soDonDatHangChart');
const formThongKeDOM = document.querySelector('.formThongKe');
const tuyChonThongKeDOM = document.querySelector('.tuyChonThongKe');
const debugButton = document.querySelector('.debug-button');
let donDatHangChart;
let pieChart;
const topSanPhamBanChayDOM = document.querySelector('#topSanPhamBanChay');

const buildLineChartConfig = (data, xTitle) => ({
    type: 'line',
    data: data,
    options: {
        scales: {
            x: {
                title: {
                    display: true,
                    text: xTitle
                }
            },
            y: {
                ticks: {
                    beginAtZero: true,
                    callback: function (value) { if (value % 1 === 0 && value >= 0) { return value; } }
                }
            }
        },
    }
});

const buildDataTheoNgay = (jsonData, labels) => {
    let index = 0; // index để lặp qua soDonDatHang
    const dataList = [];
    // labels theo ngày trong tháng, từ ngày 1 tới ngày hiện tại
    labels.forEach(item => {
        if (index < jsonData.length) {
            if (item === new Date(jsonData[index].ngay).getDate()) {
                dataList.push(jsonData[index].so_luong);
                index++;
            } else {
                dataList.push(0);
            }
        } else {
            dataList.push(0);
        }
    });
    return dataList;
}

const getSoDonDatHangThongKe = async (tuNgay, denNgay) => {
    try {
        const { data: { soDonDatHang, trongThang } } = await axios.get(`${baseURL}api/v1/user/thongke/dathang`, {
            params: {
                tuNgay,
                denNgay,
            }
        });
        let dataList = [];
        let labels = [];
        if (trongThang) {
            labels = Array.from(Array(new Date().getDate()), (_, i) => i + 1);
            dataList = buildDataTheoNgay(soDonDatHang, labels);
        } else {
            let xBatDau = true;
            let xKetThuc = true;
            const _tuNgay = moment(tuNgay).format('L');
            const _denNgay = moment(denNgay).format('L');
            soDonDatHang.forEach(item => {
                const so_luong = item.so_luong;
                const ngay = moment(new Date(item.ngay)).format('L');
                dataList.push(so_luong);
                labels.push(ngay);
                if (ngay === _tuNgay) {
                    xBatDau = false;
                }
                if (ngay === _denNgay) {
                    xKetThuc = false;
                }
            });
            if (xBatDau) {
                labels.unshift(_tuNgay);
                dataList.unshift(0);
            }
            if (xKetThuc) {
                labels.push(_denNgay);
                dataList.push(0);
            }
        }
        const data = {
            labels: labels,
            datasets: [{
                label: 'Số lượng',
                data: dataList,
                fill: false,
                borderColor: 'rgb(75, 192, 192)',
                tension: 0.1
            }]
        };
        const config = buildLineChartConfig(data, 'Ngày');
        if (donDatHangChart) {
            donDatHangChart.destroy();
        }
        donDatHangChart = new Chart(soDonDatHangChartDOM, config);
    } catch (error) {
        console.log(error);
        const { response: { data: { message } } } = error;
        thongBao(message ?? 'Có lỗi xảy ra', true);
    }
}

getSoDonDatHangThongKe(null, null);

debugButton.addEventListener('click', () => {
    getSoDonDatHangThongKe();
});

tuyChonThongKeDOM.addEventListener('change', (event) => {
    if (event.target.value === 'tuychinh') {
        formThongKeDOM.classList.remove('d-none');
    } else {
        formThongKeDOM.classList.add('d-none');
        getSoDonDatHangThongKe(null, null);
        pieChart.destroy();
        thongKeTinhTrangDonHangAll();
    }
});

formThongKeDOM.addEventListener('submit', async(event) => {
    event.preventDefault();
    const formData = new FormData(formThongKeDOM);
    const tuNgay = formData.get('tuNgay');
    const denNgay = formData.get('denNgay');
    getSoDonDatHangThongKe(tuNgay, denNgay);

    // Tiếp đến là sơ đồ tròn
    const ctx = document.getElementById('pieChart').getContext('2d');
    try {
        let { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/user/thongke/2`, {
            params: {
                tuNgay,
                denNgay,
            }
        });
        console.log(thong_ke);
        //Trường hợp khoảng ngày đó không có dữ liệu, đưa data array về  [0,0,0,0]
        if (thong_ke.length == 0) {
             thongBao("Tình trạng đặt hàng không có dữ liệu thống kê trong khoảng thời gian này", true);
            // return;
            thong_ke = [0,0,0,0]
        }


        //Dữ liệu
        const data = {
            labels: [
                'Bị hủy',
                'Đang chờ tiếp nhận',
                'Đang vận chuyển',
                'Giao hàng thành công'
            ],
            datasets: [{
                label: 'Tình Trạng Đơn Đặt Hàng',
                data: thong_ke,
                backgroundColor: [
                    'rgb(255,0,0)',
                    'rgb(255,255,0)',
                    'rgb(88, 130, 255)',
                    'rgb(1, 255, 1)'
                ],
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
                        fontSize : 15
                    }
                }
            }
        };
        pieChart.destroy();
        pieChart = new Chart(ctx, config);
    }
    catch (error) {
        console.log(error)
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
});



const thongKeTinhTrangDonHangAll = async () => {

    const ctx = document.getElementById('pieChart').getContext('2d');

    try {
        const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/user/thongke/1`);
        console.log(thong_ke);

        //Dữ liệu
        const data = {
            labels: [
                'Bị hủy',
                'Đang chờ tiếp nhận',
                'Đang vận chuyển',
                'Giao hàng thành công'
            ],
            datasets: [{
                label: 'Tình Trạng Đơn Đặt Hàng',
                data: thong_ke,
                backgroundColor: [
                    'rgb(255,0,0)',
                    'rgb(255,255,0)',
                    'rgb(88, 130, 255)',
                    'rgb(1, 255, 1)'
                ],
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
                        fontSize : 15
                    }
                }
            }
        };
        pieChart = new Chart(ctx, config);
    }
    catch (error) {
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}


thongKeTinhTrangDonHangAll();


const thongKeDonGian = async () => {
    try {
        const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/user/thongke/0`);
        console.log(thong_ke.so_thanh_vien);
        document.querySelector("#soDonHang").innerHTML = thong_ke.so_don_dat_hang;
        document.querySelector("#soSanPham").innerHTML = thong_ke.so_san_pham;
        document.querySelector("#soDanhGia").innerHTML = thong_ke.so_danh_gia;
    }
    catch (error) {
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }
}

thongKeDonGian();


showTop10SanPhamBanChay = async () => {
    try {
        const { data: { thong_kes } } = await axios.get(`${baseURL}api/v1/user/thongke/3`);
        console.log(thong_kes);
        const listThongKe = thong_kes.map((thong_kes) => {
            const { ten_san_pham, gia, ten_loai_sp, so_luot_mua } = thong_kes;
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
        `
        }).join(' ');
        topSanPhamBanChayDOM.innerHTML = listThongKe;
    }
    catch (error) {
        console.log(error);
        thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
    }

}

showTop10SanPhamBanChay();