const loginLinkDOM = document.querySelector('#login');
const registerLinkDOM = document.querySelector('#register');
const logOutLinkDOM = document.querySelector('#logout');
const currentURL = new URL(window.location);
currentURL.searchParams.delete('redirect');

if (loginLinkDOM) {
    loginLinkDOM.href = `${baseURL}login?redirect=${currentURL}`;
    registerLinkDOM.href = `${baseURL}register?redirect=${currentURL}`;
}

if(logOutLinkDOM){
    logOutLinkDOM.addEventListener('click', async () => {
        await axios.post(`${baseURL}logout`);
        location.reload();
    })
}