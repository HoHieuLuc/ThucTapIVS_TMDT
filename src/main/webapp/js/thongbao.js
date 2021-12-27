const toastThongBao = document.querySelector('#toastThongBao');
const toastHeader = document.querySelector('#toastHeader');
const toastNoiDung = document.querySelector('#toastNoiDung');

const toast = new bootstrap.Toast(toastThongBao);
const thongBao = (msg, isError = false) => {
    toastHeader.classList.add('bg-primary');
    toastHeader.classList.remove('bg-danger');
    if(isError) {
        toastHeader.classList.add('bg-danger');
        toastHeader.classList.remove('bg-primary');
    }
    toastNoiDung.textContent = msg;
    toast.show();
}
