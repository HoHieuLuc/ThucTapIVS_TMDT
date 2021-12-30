const loaiSPListDOM = document.querySelector('#danhMucSP');

const showLoaiSPList = async () => {

    try {
        const { data: { loaiSanPhams } } = await axios.get(`${baseURL}/api/v1/loaisanpham`);
        console.log(loaiSanPhams);
    } catch (error) {
        console.log(error);
    }
}

showLoaiSPList();