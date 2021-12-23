const formDOM = document.querySelector('#registerForm');
const userNameErrorMessage = document.querySelector('#username_error');
const passwordErrorMessage = document.querySelector('#password_error');

const validateRegisterForm = async () => {
    const formData = new FormData(formDOM);
    //Thực hiện request
    try {
        await axios.post(`./registerSubmit`, formData);
        window.location.href = "./";
    } catch (error) {
        const data = error.response.data;
        console.log(data);
        userNameErrorMessage.textContent = data.username ?? "";
        passwordErrorMessage.textContent = data.password ?? "";
    }
}

formDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    validateRegisterForm();
});



