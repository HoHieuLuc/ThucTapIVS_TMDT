console.log("Fuck you tomcat!!!");


const validateRegisterForm = async () => {
    //Hiên thị thông báo lỗi
    const userNameErrorMessage = document.querySelector('#username_error');
    const passwordErrorMessage = document.querySelector('#password_error');

    const formDOM = document.querySelector('#registerForm');
    const formData = new FormData(formDOM);
    //Thực hiện request
    try {
         await axios.post(`./register`,formData);
         window.location.href = "./register_success.html";
    } catch (error) {
        //Khi có lỗi thì 
           const data = error.response.data;
            console.log(data);
            userNameErrorMessage.innerHTML=data[0];
            passwordErrorMessage.innerHTML=data[1];
    }
}

function validateUNameFrontEnd(){    
    var username = document.getElementById("username").value;
    console.log(username);

    if (!username.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,14}$/g))
        {
            document.getElementById("username_error").innerHTML=`
            Username Tối thiểu 6 ký tự và tối đa 14 kí tự,
            ít nhất một chữ cái và một số, không có kí tự khoảng trắng
            `;
        }
    else {
            document.getElementById("username_error").innerHTML=`Username hợp lệ`;
    }

}  

function validatePasswordFrontEnd(){    
    var password = document.getElementById("password").value;
    console.log(password);

    if (!password.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,14}$/g))
        {
            document.getElementById("password_error").innerHTML=`
            Password Tối thiểu 8 và tối đa 14 ký tự, 
            ít nhất một chữ cái viết hoa, một chữ cái viết thường,
             một số và một ký tự đặc biệt, không có khoảng trắng
            `;
        }
    else {
            document.getElementById("password_error").innerHTML=`Password hợp lệ`;
    }

}  




