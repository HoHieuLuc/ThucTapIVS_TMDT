const buildOptions = (data, id, value) => {
    return data.map((item) => {
        return `<option value="${item[id]}" class="form-control">${item[value]}</option>`;
    }).join('');
}