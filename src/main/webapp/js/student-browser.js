const searchFormDOM = document.querySelector('#search-form');
const searchInputDOM = document.querySelector('#search');
const studentListDOM = document.querySelector('#student-list');


const showStudentList = async () => {
    studentListDOM.textContent = 'Loading...';
    const params = window.location.search;
    // đặt value trong ô input bằng giá trị search của params
    searchInputDOM.value = new URLSearchParams(params).get('search') ?? "";
    const searchString = searchInputDOM.value;
    try {
        // nếu không có encodeURIComponent thì khi nhập ký tự đặc biệt sẽ bị lỗi
        // Invalid character found in the request target. 
        // The valid characters are defined in RFC 7230 and RFC 3986
        const { data: students } = await axios.get(`../api/v1/student/list?search=${encodeURIComponent(searchString)}`);
        console.log(students);
        const allStudents = students.map((student) => {
            const { id, name, branch, percentage, phone, email } = student;
            return `
                <tr>
                    <td>${id}</td>
                    <td>${name}</td>
                    <td>${branch}</td>
                    <td>${percentage}</td>
                    <td>${phone}</td>
                    <td>${email}</td>
                    <td>
                        <div class="d-flex justify-content-evenly">
                            <a href="./edit/${id}" class="">Sửa</a>
                            <a class="delete-btn" data-id="${id}">Xóa</a>
                        </div>
                    </td>
                </tr>`;
        }).join('');
        studentListDOM.innerHTML = allStudents;
    } catch (error) {
        console.log(error);
    }
}

showStudentList();

// xóa
studentListDOM.addEventListener('click', async (event) => {
    const eventTarget = event.target;
    if (eventTarget.classList.contains('delete-btn')) {
        const id = eventTarget.dataset.id;
        try {
            await axios.post(`../api/v1/student/delete/${id}`);
            showStudentList();
        } catch (error) {
            console.log(error);
        }
    }
});


searchFormDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    // thay đổi url không cần reload
    window.history.pushState('search', '','?search=' + searchInputDOM.value);
    showStudentList();
});