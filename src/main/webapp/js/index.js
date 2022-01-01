const loginLinkDOM = document.querySelector('#login');
const registerLinkDOM = document.querySelector('#register');
const logOutLinkDOM = document.querySelector('#logout');
const loginDanhGiaKhachHangDOM = document.querySelector('#loginDanhGiaKH');
const currentURL = new URL(window.location);
currentURL.searchParams.delete('redirect');

if (loginLinkDOM) {
    loginLinkDOM.href = `${baseURL}login?redirect=${encodeURIComponent(currentURL)}`;
    registerLinkDOM.href = `${baseURL}register?redirect=${encodeURIComponent(currentURL)}`;
    loginDanhGiaKhachHangDOM.href = `${baseURL}login?redirect=${encodeURIComponent(currentURL)}`;
}

if(logOutLinkDOM){
    logOutLinkDOM.addEventListener('click', async (event) => {
        event.preventDefault();
        await axios.post(`${baseURL}logout`);
        location.reload();
    })
}