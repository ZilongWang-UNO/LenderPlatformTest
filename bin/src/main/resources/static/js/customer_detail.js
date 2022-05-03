//get customer id from url
function get_id() {
    var url = window.location.search;
    var params = url.substring(url.indexOf("?") + 3);
    var par = params.split("=");
    var str = par[1];
    return str;
}

window.onload = function () {

    let id = get_id()
    let info = []
    let full = location.protocol + '//' + location.hostname + (location.port.toString().trim().length !== 0 ? ':' + location.port : '');
    axios.get(full + "/api/lender/getById/" + id)
        .then((res) => {
			//store each information of the customer into info object
            for (let i = 0; i < res.data.length; i++) {
                let temp = {
                    name: res.data[i]["fn"] + " " + res.data[i]["ln"], address: res.data[i]["a1"] + " " + res.data[i]["a2"] + "<br>" + res.data[i]["city"] + ", " + res.data[i]["state"]
                        + " " + res.data[i]["zip"], email: res.data[i]["email"], phone: res.data[i]["phone"], vin: res.data[i]["vin"],
                }
                info.push(temp)
            }

            let a = document.createElement("a")
            a.href = "list.html"
            let button = document.createElement("button")
            button.innerHTML = "Back To List"
            a.appendChild(button)
            document.body.appendChild(a)

            let title = document.createElement("h1");
            title.innerHTML = "Customer Details<br><br>";
            document.body.appendChild(title);
            let table = document.createElement("table");
            let tbody = document.createElement("tbody");
            table.appendChild(tbody);
            let tr = tbody.insertRow(0);
            let str = "Customer/Policyholder,Address,Email,Phone,VIN".split(",");

            for (let i = 0; i < str.length; i++) {
                let th = document.createElement("th");
                th.innerHTML = str[i];
                tr.appendChild(th);
            }

            for (let i = 0; i < info.length; i++) {
                let tr = tbody.insertRow(tbody.rows.length);
                let obj = info[i];
                for (let p in obj) {
                    let op = obj[p];
                    let td = tr.insertCell(tr.cells.length);
                    td.innerHTML = op;
                }
            }
            document.body.appendChild(table);
        })
        .catch((error) => {
            alert("Error: " + error.response.data.message);
        });
}