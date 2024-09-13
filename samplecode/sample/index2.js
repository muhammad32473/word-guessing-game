const submitBtn = document.getElementById("submit");

submitBtn.onclick = async function(event){
    event.preventDefault();

    let id = document.getElementById("user-id").value.trim();
    let name = document.getElementById("user-name").value.trim();
    let address = document.getElementById("user-address").value.trim();
    
    if (id === "" || name === "" || address === "") {
        alert("Please enter all the required data.");
        return;
    }

    console.log(id);
    console.log(name);
    console.log(address);

    let jsObject = {
        id: id,
        name: name,
        address: address
    };

    let jsonObject = JSON.stringify(jsObject);

    try {
        const response = await fetch("http://localhost:8080/api/add",{
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonObject 
        });
        
        if (response.ok) {
            const result = await response.json();
            console.log("User added:", result);
        } else {
            console.error("Failed to add user:", response.status);
        }
    }
    catch (error) {
        console.log(error);
    }   
}
