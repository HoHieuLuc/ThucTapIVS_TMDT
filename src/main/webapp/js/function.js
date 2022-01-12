const changeURLparam = (key, value) => {
    const url = new URL(window.location.href);
    url.searchParams.set(key, value);
    window.history.pushState('s', '', url);
}

const removeURLparam = (key) => {
    const url = new URL(window.location.href);
    url.searchParams.delete(key);
    window.history.pushState('s', '', url);
}

const buildPagination = (page, totalPages, maxPages, callback) => {
    // maxPages là số nút điều hướng tối đa sẽ hiển thị
    if (totalPages <= 1) {
        return "";
    }
    if(page === undefined){
        page = 1;
    }
    page = parseInt(page);
    let paginationHTML = "";
    paginationHTML += `
        <nav aria-label="Page navigation">
            <ul class="pagination">
    `;
    // trang hiện tại là 1 thì nút previous sẽ bị disabled
    if (page == 1) {
        paginationHTML += `
            <li class="page-item disabled">
                <a class="page-link">
                    <span aria-hidden="true text-muted">&laquo;</span>
                </a>
            </li>
        `;
    }
    if (page > 1) {
        paginationHTML += `
            <li class="page-item">
                <a class="page-link" href="javascript:void(0)" onclick="${callback}(${page - 1})">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        `;
    }
    // tính số trang bắt đầu và kết thúc cho các nút điều hướng
    let start = 0;
    let end = 0;
    if (page <= Math.ceil(maxPages / 2)) {
        start = 1;
        end = maxPages;
    } else if (page < Math.ceil((2 * totalPages - (maxPages - 1)) / 2)) {
        start = page - Math.ceil(maxPages / 2) + (maxPages % 2 == 0 ? 0 : 1);
        end = page + Math.ceil(maxPages / 2) - 1;
    } else {
        start = totalPages - (maxPages - 1);
        end = totalPages;
    }

    for (let i = start; i <= end; i++) {
        if (i >= 1 && i <= totalPages) {
            // trang hiện tại sẽ bị disabled
            if (i == page) {
                paginationHTML += `
                    <li class="page-item active border border-primary disabled">
                        <a class="page-link">
                            ${i}
                        </a>
                    </li>
                `;
            } else {
                paginationHTML += `
                    <li class="page-item">
                        <a class="page-link" href="javascript:void(0)" onclick="${callback}(${i})">
                            ${i}
                        </a>
                    </li>
                `;
            }
        }
    }

    // trang hiện tại nhỏ hơn trang cuối thì mới bấm được nút next
    if (page < totalPages) {
        paginationHTML += `
            <li class="page-item">
                <a class="page-link" href="javascript:void(0)" onclick="${callback}(${page + 1})">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        `;
    }

    if (page == totalPages) {
        paginationHTML += `
            <li class="page-item disabled">
                <a class="page-link">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        `;
    }
    paginationHTML += `
            </ul >
        </nav >
    `;
    return paginationHTML;
}

const buildOptions = (data, id, value, defaultOption = '') => {
    let defaultOptionHtml = '';
    if (defaultOption != '') {
        defaultOptionHtml = `<option value="" selected disabled class="form-control">${defaultOption}</option>`;
    }
    const optionHtml = data.map((item) => {
        return `<option value="${item[id]}" class="form-control">${item[value]}</option>`;
    }).join('');
    return defaultOptionHtml + optionHtml;
}