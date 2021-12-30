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