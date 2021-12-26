const toastThongBao = document.querySelector('#toastThongBao');
const toastNoiDung = document.querySelector('#toastNoiDung');
const toast = new bootstrap.Toast(toastThongBao);
const thongBao = (msg) => {
    toastNoiDung.textContent = msg;
    toast.show();
}