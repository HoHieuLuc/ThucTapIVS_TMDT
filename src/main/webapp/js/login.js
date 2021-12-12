const formDOM = document.querySelector('#loginForm');

const loginErrorMessage = document.querySelector('#login_error');

const login = async () => {
    const formData = new FormData(formDOM);
    try {
        await axios.post(`./loginAction`, formData);
        window.location.href = "./welcome";
    } catch (error) {
        loginErrorMessage.textContent = error.response.data.message;
    }
}

formDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    login();
});