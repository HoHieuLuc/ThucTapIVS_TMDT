const formDOM = document.querySelector('#edit-student-form');
const formAlertDOM = document.querySelector('#form-alert');
const editBtnDOM = document.querySelector('.edit-btn');

const params = window.location.pathname.split('/').slice(0);
const id = params[params.length - 1];
console.log(params);

const showStudent = async () => {
    try {
        const { data: student } = await axios.get(`../../api/v1/student/${id}`);
        console.log(student);
        const { name, branch, percentage, phone, email } = student;

        formDOM.querySelector('#id').value = id;
        formDOM.querySelector('#name').value = name;
        formDOM.querySelector('#branch').value = branch;
        formDOM.querySelector('#percentage').value = percentage;
        formDOM.querySelector('#phone').value = phone;
        formDOM.querySelector('#email').value = email;
    } catch (error) {
        formDOM.querySelector('#id').value = error.response.data.message;
    }
}

showStudent();

formDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    editBtnDOM.disabled = true;
    const formData = new FormData(formDOM);
    try {
        const { data } = await axios.post(`../../api/v1/student/edit/${id}`, formData);
        console.log(data);
        formAlertDOM.style.display = 'block';
        formAlertDOM.textContent = `Cập nhật thông tin sinh viên thành công`;
        formAlertDOM.classList.add('text-success');
    } catch (error) {
        console.log(error);
        formAlertDOM.style.display = 'block';
        formAlertDOM.textContent = error.response.data.message;
        formAlertDOM.classList.add('text-danger');
    }
    editBtnDOM.disabled = false;
    setTimeout(() => {
        formAlertDOM.style.display = 'none';
        formAlertDOM.classList.remove('text-success', 'text-danger');
    }, 3000);
});