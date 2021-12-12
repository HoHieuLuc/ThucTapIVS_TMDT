const searchFormDOM = document.querySelector('#search-form');
const studentListDOM = document.querySelector('#student-list');
const searchInput = document.querySelector('#search');
const params = window.location.search;
searchInput.value = new URLSearchParams(params).get('search') ?? "";

let queryString = "";

const changeURL = () => {
    const newParams = window.location.search;
    const page = new URLSearchParams(newParams).get('page') ?? 0;
    const pageString = page === 0 ? '' : `page=${page}`;
    
    // đặt value trong ô input bằng giá trị search của params
    console.log(searchInput.value);
    const searchString = searchInput.value === "" ? "" : `search=${encodeURIComponent(searchInput.value)}`;

    queryString = [pageString, searchString].filter(x => x !== "").join('&');
    queryString = queryString === "" ? "" : `?${queryString}`;
    console.log(pageString);
    console.log(searchString);
    console.log(queryString);
    // thay đổi url không cần reload
    if (queryString === ""){
        window.history.pushState('search', '', `index`);
        return;
    }
    window.history.pushState('search', '', `${queryString}`);
}

changeURL();

const showStudentList = async () => {
    studentListDOM.textContent = 'Loading...';

    try {
        // nếu không có encodeURIComponent thì khi nhập ký tự đặc biệt sẽ bị lỗi
        // Invalid character found in the request target. 
        // The valid characters are defined in RFC 7230 and RFC 3986
        const { data: { students, pageCount } } = await axios.get(`../../api/v1/student/list${queryString}`);
        console.log(students);
        console.log(pageCount);
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
            await axios.post(`../../api/v1/student/delete/${id}`);
            showStudentList();
        } catch (error) {
            console.log(error);
        }
    }
});


searchFormDOM.addEventListener('submit', async (event) => {
    event.preventDefault();
    changeURL();
    showStudentList();
});

const btnReloadDOM = document.querySelector('#btn-reload');
btnReloadDOM.addEventListener('click', () => {
    showStudentList();
});