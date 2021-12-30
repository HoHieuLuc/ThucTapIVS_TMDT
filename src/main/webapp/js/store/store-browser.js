const storeListDOM = document.querySelector('#storeList');
		storeListDOM.textContent = "Loading..."
const showstoreListDOM = async () => {

    try {
        const { data: { listStores } } = await axios.get(`${baseURL}/api/v1/store`);
        //{ma_khach_hang: 1, avatar: "1.png", ten: "Lâm Minh Thiện"}
		const alllistStores = listStores.map((listStore) => {
            const { ma_khach_hang,avatar,ten } = listStore;
            return `
               <p>${ma_khach_hang} ++ ${avatar} ++ ${ten}</p>
			   `;
        }).join('');
		
	 	storeListDOM.innerHTML = alllistStores;
    } catch (error) {
        console.log(error);
    }
}

showstoreListDOM();