const formThongKeDOM = document.querySelector('.formThongKe');
const tuyChonThongKeDOM = document.querySelector('.tuyChonThongKe');
let pieChart;

const thongKeTinhTrangDonHangAll = async () => {

  const ctx = document.getElementById('pieChart').getContext('2d');

  try {
    const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/nhanvien/thongke/1`);
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
        data: [thong_ke.bi_huy, thong_ke.dang_cho, thong_ke.dang_van_chuyen, thong_ke.da_nhan_hang],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(211, 227, 110)',
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
    const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/nhanvien/thongke/0`);
    console.log(thong_ke.so_thanh_vien);
    document.querySelector("#soDonHang").innerHTML = thong_ke.so_don_dat_hang;
    document.querySelector("#soSanPham").innerHTML = thong_ke.so_san_pham;
    document.querySelector("#soNguoiDung").innerHTML = thong_ke.so_thanh_vien;
    document.querySelector("#soDanhGia").innerHTML = thong_ke.so_danh_gia;
  }
  catch (error) {
    thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
  }
}

thongKeDonGian();

tuyChonThongKeDOM.addEventListener('change', (event) => {
  if (event.target.value === 'tuychinh') {
    formThongKeDOM.classList.remove('d-none');
  } else {
    formThongKeDOM.classList.add('d-none');
    //Bình thường sẽ thống kê toàn thời gian
    thongKeTinhTrangDonHangAll();
  }
});

formThongKeDOM.addEventListener('submit', async(event) => {
  event.preventDefault();
  const formData = new FormData(formThongKeDOM);
  const tuNgay = formData.get('tuNgay');
  const denNgay = formData.get('denNgay');
  console.log(tuNgay);
  console.log(denNgay);
  const ctx = document.getElementById('pieChart').getContext('2d');

  try {
    const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/nhanvien/thongke/2`,{
      params: {
          tuNgay,
          denNgay,
      }
  });
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
        data: [thong_ke.bi_huy, thong_ke.dang_cho, thong_ke.dang_van_chuyen, thong_ke.da_nhan_hang],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(211, 227, 110)',
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
    };
    pieChart.destroy();
     pieChart = new Chart(ctx, config);
  }
  catch (error) {
    thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
  }
});