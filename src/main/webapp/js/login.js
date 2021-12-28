const formDOM = document.querySelector('#loginForm');
const url = new URL(window.location);
const redirect = url.searchParams.get('redirect');

const loginErrorMessage = document.querySelector('#login_error');

const login = async () => {
    const formData = new FormData(formDOM);
    try {
        await axios.post(`./loginAction`, formData);
        console.log("Bạn đã đăng nhập thành công");
        window.location.href = decodeURIComponent(redirect);
    } catch (error) {
        loginErrorMessage.textContent = error.response.data.message;
    }
}

formDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    login();
});