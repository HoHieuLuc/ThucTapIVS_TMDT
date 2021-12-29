const params = window.location.pathname.split('/').slice(0);
const username = params[params.length - 1];

const getStoreInfo = async () => {
    try {
        const response = await axios.get(`${baseURL}api/v1/store/${username}/info`);
        console.log(response);
    } catch (error) {
        console.log(error);
    }
}

getStoreInfo();