const url = "http://localhost:8080/task/user/1";

function hideLoader(){
    document.getElementById("loading").style.display="nome";
}

function show(tasks){
    let tab = `<thead>
            <th scope="col">#</th>
            <th scope="col">Description</th>
            <th scope="col">Username</th>
            <th scope="col">ID</th>
        </thead>    
    `;

    for(let task of tasks){
        tab +=`
            <tr>
                <td scope="row">${task.id}</td>
                <td scope="row">${task.description}</td>
                <td scope="row">${task.user.username}</td>
                <td scope="row">${task.user.id}</td>
            </tr>    
        `;
    }

    document.getElementById("tasks").innerHTML= tab;


    async function getAPI(url){
        const reponse = await fetch(url,{method:"GET"});
        let data = await reponse.json();
        if(reponse){
            hideLoader();
        }
        show(data);
    }
}   