const searchFormDOM = document.querySelector('#search-form');
const studentListDOM = document.querySelector('#student-list');
const paginationDOM = document.querySelector('#pagination');
const searchInput = document.querySelector('#search');
const params = new URLSearchParams(window.location.search);
searchInput.value = params.get('search') ?? "";
let page =  params.get('page') ?? 1;

const showStudentList = async () => {
    studentListDOM.textContent = 'Loading...';
    try {
        const { data: { students, totalPages } } = await axios.get(`${baseURL}api/v1/student/list`, {params: {search: searchInput.value, page: page}});
        console.log(students);
        console.log(totalPages);
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
        paginationDOM.innerHTML = buildPagination(page, totalPages, 5);
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
            await axios.post(`${baseURL}api/v1/student/delete/${id}`);
            showStudentList();
        } catch (error) {
            console.log(error);
        }
    }
});

// thay đổi url khi tìm kiếm
const changeURL = () => {
    const searchString = searchInput.value === "" ? "" : `search=${encodeURIComponent(searchInput.value)}`;
    const queryString = searchString === "" ? "" : `?${searchString}`;
    if (queryString === ""){
        window.history.pushState('search', '', `index`);
        return;
    }
    window.history.pushState('search', '', `index${queryString}`);
}

searchFormDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    changeURL();
    page = 1;
    showStudentList();
});

const btnReloadDOM = document.querySelector('#btn-reload');
btnReloadDOM.addEventListener('click', () => {
    showStudentList();
});