let imgs = [
    "../img/true.png",
    "../img/false.png",
    "../img/check.png",
    "../img/upload.png",
    "../img/email1.png",
    "../img/delete.png",
]
let notes = [
    "Has Insurance",
    "No Insurance",
    "Check",
    "Upload Policy",
    "Email Insurance Quote Form",
    "Delete Customer",
];

window.onload = function () {
    
    let info = []
    let full = location.protocol + '//' + location.hostname + (location.port.toString().trim().length !== 0 ? ':' + location.port : '');
    axios.get(full + "/api/lender/get")
        .then((res) => {
			//store customer's information into info object
            for (let i = 0; i < res.data.length; i++) {
                let temp = { name: res.data[i]["fn"] + " " + res.data[i]["ln"], email: res.data[i]["vin"], update: "update", delete: "delete", id: res.data[i]["cid"], }
                info.push(temp)
            }

            let title = document.createElement("h1");
            title.innerHTML = "";
            //document.body.appendChild(title);
            let table = document.createElement("table");
            let tbody = document.createElement("tbody");
            table.appendChild(tbody);
            let tr = tbody.insertRow(0);
            let str = "Customer/Policyholder,VIN,Actions".split(",");//Status

            for (let i = 0; i < str.length; i++) {
                let th = document.createElement("th");
                th.innerHTML = str[i];
                tr.appendChild(th);
            }

            for (let i = 0; i < info.length; i++) {//info for customers
                let tr = tbody.insertRow(tbody.rows.length);
                let obj = info[i];
                for (let p in obj) {
                    if (p == "name") {
                        let op = obj[p];
                        let id = obj["id"];
                        let td = tr.insertCell(tr.cells.length);
                        let a = document.createElement("a");
                        a.href = "customer_detail.html?id=" + id.split(" ");
                        a.innerHTML = op;
                        td.appendChild(a);
                    }
                    else if (p == "update") {
                        let op = obj[p];
                        let id = obj["id"];
                        let td = tr.insertCell(tr.cells.length);
                        let a = document.createElement("a");
                        a.href = "updateC.html?id=" + id.split(" ");
                        a.innerHTML = op;
                        td.appendChild(a);
                    }
                    else if (p == "delete") {
                        let op = obj[p];
                        let id = obj["id"];
                        let td = tr.insertCell(tr.cells.length);
                        let a = document.createElement("a");
                        a.href = "deleteC.html?id=" + id.split(" ");
                        a.innerHTML = op;
                        td.appendChild(a);
                    }
                    else if (p == "id")
                        break
                    else {
                        let op = obj[p];
                        let td = tr.insertCell(tr.cells.length);
                        td.innerHTML = op;
                    }
                }
            }
            document.body.appendChild(table);
            let ul = document.createElement("ul");
            for (let i = 0; i < imgs.length; i++) {
                let img = document.createElement("img");
                img.src = imgs[i];
                let li = document.createElement("li");
                li.innerHTML = "<br>";
                ul.appendChild(li);
                ul.appendChild(img);
            }
            document.body.appendChild(ul);
            let ul1 = document.createElement("ul");
            for (let i = 0; i < notes.length; i++) {
                let li = document.createElement("li");
                li.innerHTML = "<br>" + notes[i] + "<br>";
                ul1.appendChild(li);
            }
            document.body.appendChild(ul1);
        })
        .catch((error) => {
            alert("Error: " + error.response.data.message);
        });
}