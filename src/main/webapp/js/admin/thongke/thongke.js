

const thongKeTinhTrangDonHang = async () => {

  const ctx = document.getElementById('pieChart').getContext('2d');

  //Dữ liệu
  const data = {
    labels: [
      'Red',
      'Blue',
      'Yellow'
    ],
    datasets: [{
      label: 'My First Dataset',
      data: [300, 50, 100],
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(54, 162, 235)',
        'rgb(255, 205, 86)'
      ],
      hoverOffset: 4
    }]
  };

  //Cấu hình
  const config = {
    type: 'pie',
    data: data,
  };
  const pieChart = new Chart(ctx, config);
}
thongKeTinhTrangDonHang();

const thongKeDonGian = async () => {
  try {
    const { data: { thong_ke } } = await axios.get(`${baseURL}api/v1/nhanvien/thongke/0`);
    console.log(thong_ke.so_thanh_vien);
    document.querySelector("#soDonHang").innerHTML = thong_ke.so_don_dat_hang ;
    document.querySelector("#soSanPham").innerHTML = thong_ke.so_san_pham ;
    document.querySelector("#soNguoiDung").innerHTML =thong_ke.so_thanh_vien ;
    document.querySelector("#soDanhGia").innerHTML = thong_ke.so_danh_gia ;
  }
  catch (error) {
    thongBao(error.response.data.message ?? 'Có lỗi xảy ra', true);
  }
}

thongKeDonGian();