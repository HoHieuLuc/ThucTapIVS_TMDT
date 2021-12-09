console.log("Fuck you tomcat");
const checkLogined = async () => {
    try {
        await axios.post(`./login`);
        window.location.href = "./student/index";
    }
    catch (error) {
        
    }
}
const validateLoginForm = async () => {
    //Hiên thị thông báo lỗi
    const loginErrorMessage = document.querySelector('#login_error');
    const passwordErrorMessage = document.querySelector('#password_error');

    const formDOM = document.querySelector('#loginForm');
    const formData = new FormData(formDOM);
    //Thực hiện request
    try {
         await axios.post(`./login`,formData);
         window.location.href = "./student/index";
    } catch (error) {
        //Khi có lỗi thì 
           const data = error.response.data;
            console.log(data);
            loginErrorMessage.innerHTML=data[0];
            passwordErrorMessage.innerHTML=data[1];
     
    }
}

//Luôn luôn chạy hàm kiểm tra đã đăng nhập hay chưa (đã có session chưa), để tự redirect sang trang index (list)
checkLogined();


