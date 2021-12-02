const formDOM = document.querySelector('#create-student-form');

formDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    const data = new FormData(formDOM);

    try {
        await axios.post('CreateStudent', data);
        window.location.href = './';
        //console.log('adadad');
    } catch (error) {
        console.log(error);
    }
});