const studentListDOM = document.querySelector('#student-list');
const debugBtnDOM = document.querySelector('#debug-btn');

const showStudentList = async () => {
    studentListDOM.textContent = 'Loading...';
    try {
        const { data: students } = await axios.get('../api/v1/student/list');
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
                            <a href="" class="">Sửa</a>
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
            studentListDOM.textContent = 'Loading...';
            // đổi nhánh xong xài delete bị lỗi luôn phải chuyển sang xài post
            //await axios.delete(`../api/v1/student/delete/${id}`);
            await axios.post(`../api/v1/student/delete/${id}`);
            showStudentList();
        } catch (error) {         
            console.log(error);
        }
    }
});

debugBtnDOM.addEventListener('click', () => {
    showStudentList();
});