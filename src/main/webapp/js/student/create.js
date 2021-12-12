const formDOM = document.querySelector('#create-student-form');
const errorMsgDOM = document.querySelector('#error-msg');

formDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formData = new FormData(formDOM);
    errorMsgDOM.textContent = '';
    try {
        await axios.post('../../api/v1/student/create', formData);
        window.location.href = './';
    } catch (error) {
        errorMsgDOM.textContent = error.response.data.message;
    }
});
