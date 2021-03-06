const storeListDOM = document.querySelector('#storeList');
		
const showstoreListDOM = async () => {

    try {
        const { data: { listStores } } = await axios.get(`${baseURL}api/v1/store`);
        //{ma_khach_hang: 1, avatar: "1.png", ten: "Lâm Minh Thiện"}
		const alllistStores = listStores.map((listStore) => {
            const { avatar,ten,username } = listStore;
            return `
               <div class="col-sm-3">
                    <a href="${baseURL}store/${username}">
                            <img src="${baseURL}images/user/${avatar}" class="img-responsive" style="width: 200px; height: 200px; border-radius: 30%;" alt="${ten}">
                    </a>
                     <p>${ten}</p>
             </div>
			   `;
        }).join('');
		
	 	storeListDOM.innerHTML = alllistStores;
    } catch (error) {
        console.log(error);
    }
}

showstoreListDOM();