const soDonDatHangChartDOM = document.querySelector('.soDonDatHangChart');
const formThongKeDOM = document.querySelector('.formThongKe');
const tuyChonThongKeDOM = document.querySelector('.tuyChonThongKe');
const debugButton = document.querySelector('.debug-button');
let donDatHangChart;

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
    }
});

formThongKeDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    const formData = new FormData(formThongKeDOM);
    const tuNgay = formData.get('tuNgay');
    const denNgay = formData.get('denNgay');
    getSoDonDatHangThongKe(tuNgay, denNgay);
});