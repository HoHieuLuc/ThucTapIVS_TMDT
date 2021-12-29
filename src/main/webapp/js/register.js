const formDOM = document.querySelector('#registerForm');
const url = new URL(window.location);
const redirect = url.searchParams.get('redirect');

const userNameErrorMessage = document.querySelector('#username_error');
const passwordErrorMessage = document.querySelector('#password_error');
const emailErrorMessage = document.querySelector('#email_error');
const tenErrorMessage = document.querySelector('#ten_error');
const facebookErrorMessage = document.querySelector('#facebook_error');
const twitterErrorMessage = document.querySelector('#twitter_error');
const retypePasswordErrorMessage = document.querySelector('#retype_password')
const phoneErrorMessage = document.querySelector('#phone_error')

const validateRegisterForm = async () => {
    const formData = new FormData(formDOM);
    //Thực hiện request
    try {
        await axios.post(`./registerSubmit`, formData);
        window.location.href = redirect !== null ? decodeURIComponent(redirect) : baseURL;
    } catch (error) {
        const data = error.response.data;
        console.log(data);
        userNameErrorMessage.textContent = data.username ?? "";
        passwordErrorMessage.textContent = data.password ?? "";
        emailErrorMessage.textContent = data.email ?? "";
        tenErrorMessage.textContent = data.ten ?? "";
        facebookErrorMessage.textContent = data.facebook_link ?? "";
        twitterErrorMessage.textContent = data.twitter_link ?? "";
        retypePasswordErrorMessage.textContent = data.xac_nhan_password ?? "";
        phoneErrorMessage.textContent = data.dien_thoai ?? "";

    }
}

formDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    validateRegisterForm();
});



