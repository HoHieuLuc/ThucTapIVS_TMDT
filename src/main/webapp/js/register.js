const formDOM = document.querySelector('#registerForm');
const userNameErrorMessage = document.querySelector('#username_error');
const passwordErrorMessage = document.querySelector('#password_error');

const validateRegisterForm = async () => {
    const formData = new FormData(formDOM);
    //Thực hiện request
    try {
        await axios.post(`./registerAction`, formData);
        window.location.href = "./";
    } catch (error) {
        const data = error.response.data;
        console.log(data);
        userNameErrorMessage.textContent = data.username ?? "";
        passwordErrorMessage.textContent = data.password ?? "";
    }
}

function validateUNameFrontEnd() {
    var username = document.getElementById("username").value;
    console.log(username);

    if (!username.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,14}$/)) {
        userNameErrorMessage.textContent = `
            Username Tối thiểu 6 ký tự và tối đa 14 kí tự,
            ít nhất một chữ cái và một số, không có kí tự khoảng trắng
            `;
    }
    else {
        userNameErrorMessage.textContent = `Username đúng quy tắc`;
    }

}

function validatePasswordFrontEnd() {
    var password = document.getElementById("password").value;
    console.log(password);

    if (!password.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,14}$/)) {
        passwordErrorMessage.textContent = `
            Password Tối thiểu 8 và tối đa 14 ký tự, 
            ít nhất một chữ cái viết hoa, một chữ cái viết thường,
             một số và một ký tự đặc biệt, không có khoảng trắng
            `;
    }
    else {
        passwordErrorMessage.textContent = `Password đúng quy tắc`;
    }

}




