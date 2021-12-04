const formDOM = document.querySelector('#create-student-form');
const errorMsgDOM = document.querySelector('#error-msg');

formDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    const formData = new FormData(formDOM);
    errorMsgDOM.textContent = '';
    try {
        const { data } = await axios.post('../api/v1/student/create', formData);
        window.location.href = './index';
        console.log(data);
        //console.log('adadad');
    } catch (error) {
        errorMsgDOM.textContent = error.response.data.message;
        //console.log(error.response.data.message);
    }
});
