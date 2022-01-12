

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
    const pieChart = new Chart(ctx,config);
}
thongKeTinhTrangDonHang();