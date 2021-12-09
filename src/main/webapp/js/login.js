const formDOM = document.querySelector('#loginForm');

const loginErrorMessage = document.querySelector('#login_error');
const passwordErrorMessage = document.querySelector('#password_error');

const validateLoginForm = async () => {
    //Hiên thị thông báo lỗi

    const formData = new FormData(formDOM);
    //Thực hiện request
    try {
        await axios.post(`./loginAction`, formData);
        window.location.href = "./student/index";
    } catch (error) {
        //Khi có lỗi thì 
        const data = error.response.data;
        console.log(data);
        loginErrorMessage.innerHTML = data[0];
        passwordErrorMessage.innerHTML = data[1];
    }
}

formDOM.addEventListener('submit', (event) => {
    event.preventDefault();
    validateLoginForm();
});