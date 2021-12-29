const avatarDOM = document.querySelector('#avatar');
const tenNguoiBanDOM = document.querySelector('#tenNguoiBan');
const gioiThieuDOM = document.querySelector('#gioiThieu');
const ratingDOM = document.querySelector('#rating');
const linkDOM = document.querySelector('#link');
const ngayThamGiaDOM = document.querySelector('#ngayThamGia');
const soDienThoaiDOM = document.querySelector('#soDienThoai');
const emailDOM = document.querySelector('#email');
const diaChiDOM = document.querySelector('#diaChi');

const ratingChartDOM = document.querySelector('#ratingChart');

const params = window.location.pathname.split('/').slice(0);
const username = params[params.length - 1];

const data = (myData, myLabels) => ({
    labels: myLabels,
    datasets: [{
        label: 'Số đánh giá',
        data: myData,
        backgroundColor: 'lightgreen',
        borderColor: 'darkgreen',
        borderWidth: 1
    }]
});

const config = (myData, myLabels) => ({
    type: 'bar',
    data: data(myData, myLabels),
    options: {
        scales: {
            y: {
                beginAtZero: true
            },
        },
        indexAxis: 'y',
        aspectRatio: 1.5,
    },
});

const getStoreInfo = async () => {
    try {
        const { data: { store_info, product_rating } } = await axios.get(`${baseURL}api/v1/store/${username}/info`);
        const { ten, dia_chi, gioi_thieu, ngay_tao, email, so_dien_thoai,
            avatar, twitter_link, facebook_link, personal_link, rating, so_danh_gia
        } = store_info;
        console.log(gioi_thieu);
        avatarDOM.src = `${baseURL}images/user/${avatar}`;
        tenNguoiBanDOM.textContent = ten;
        gioiThieuDOM.textContent = gioi_thieu;
        ratingDOM.innerHTML = rating === undefined ? 'Chưa có đánh giá' : `${rating} &#11088; (${so_danh_gia} đánh giá)`;
        ngayThamGiaDOM.textContent = `${ngay_tao.date.day}/${ngay_tao.date.month}/${ngay_tao.date.year}`;
        soDienThoaiDOM.textContent = so_dien_thoai;
        emailDOM.textContent = email;
        diaChiDOM.textContent = dia_chi;

        const facebookLink = facebook_link === undefined ? '' : `<a href="${facebook_link}" target="_blank"><i class="fab fa-facebook"></i></a>`;
        const twitterLink = twitter_link === undefined ? '' : `<a href="${twitter_link}" target="_blank"><i class="fab fa-twitter"></i></a>`;
        const personalLink = personal_link === undefined ? '' : `<a href="${personal_link}" target="_blank"><i class="fas fa-external-link-alt"></i></a>`;
        linkDOM.innerHTML = `${facebookLink} ${twitterLink} ${personalLink}`;

        const myData = [];
        let currentItemIndex = 0;
        for (let i = 5; i > 0; i--) {
            if (currentItemIndex < product_rating.length && product_rating[currentItemIndex].so_sao == i) {
                myData.push(product_rating[currentItemIndex].so_luong);
                currentItemIndex++;
            }
            else {
                myData.push(0);
            }
        }
        console.log(myData);
        const labels = [
            '5 sao',
            '4 sao', 
            '3 sao', 
            '2 sao', 
            '1 sao'
        ];
        const myConfig = config(myData, labels);
        new Chart(
            ratingChartDOM,
            myConfig
        );
    } catch (error) {
        console.log(error);
        thongbao(error.response.data.message, true);
    }
}

getStoreInfo();

