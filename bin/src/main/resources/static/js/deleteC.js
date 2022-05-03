//get customer's id from url
function get_id() {
    var url = window.location.search;
    var params = url.substring(url.indexOf("?") + 3);
    var par = params.split("=");
    var str = par[1];
    return str;
}

window.onload = function () {
    try {
        let id = get_id()
        let full = location.protocol + '//' + location.hostname + (location.port.toString().trim().length !== 0 ? ':' + location.port : '');
        axios.delete(full + "/api/lender/delete/" + id)
            .then((res) => {
                alert("Customer deleted successfully: " + res.data);
                window.location.href="../lender/list.html"; 
            })
            .catch((error) => {
                alert("Error: " + error.response.data.message);
            });
    }
    catch (error) {
        alert("Error: " + error.message);
    }
}