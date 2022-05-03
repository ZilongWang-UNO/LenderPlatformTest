//contains each input's id from html page
const ids = [
    "ln",
    "fn",
    "a1",
    "a2",
    "city",
    "state",
    "zip",
    "email",
    "phone",
    "vin",
]

//format phone number input at html page
const phoneFormat = (input) => {
    // Strip all characters from the input except digits
    input = input.replace(/\D/g, '');
    // Set the value of the REAL phone number input (which is hidden from the user)
    document.getElementById("phone").value = input;
    // Trim the remaining input to ten characters, to preserve phone number format
    input = input.substring(0, 10);
    // Based upon the length of the string, we add formatting as necessary
    var size = input.length;
    if (size == 0) {
        input = input;
    }
    else if (size < 4) {
        input = '(' + input;
    }
    else if (size < 7) {
        input = '(' + input.substring(0, 3) + ') ' + input.substring(3, 6);
    }
    else {
        input = '(' + input.substring(0, 3) + ') ' + input.substring(3, 6) + '-' + input.substring(6, 10);
    }
    document.getElementById("phone").value = input;
}

const addPhoneEventListener = () => {
    try {
        document.getElementById("phone").addEventListener("keyup", function (event) {
            var phoneNumber = document.getElementById('phone');
            var charCode = (event.which) ? event.which : event.keyCode;
            phoneFormat(phoneNumber.value);
        });
        phoneFormat(document.getElementById("phone").value);
    }
    catch (error) {
        throw (error);
    }
}

const onSubmit = () => {
    try {
        let data = {};
        //check for each input from front end
        for (var i = 0; i < ids.length; i++) {
            let id = ids[i];
            let element = document.getElementById(id);
            if (element === null) {
                alert("Error: Cannot find required input for: " + id);
                return;
            }
            //check for empty excepts address line 2
            if (id != "a2" && element.value.trim().length == 0) {
                alert(id + " is required");
                return;
            }
            //store each value into data object
            data[id] = element.value;
        }
        
        let vin = document.getElementById("vin").value
        var full = location.protocol + '//' + location.hostname + (location.port.toString().trim().length !== 0 ? ':' + location.port : '');
        
        //check for exist of customer first
        axios.get(full + "/api/lender/getByVin/" + vin)
            .then((res) => {
				//if the customer based on vin is not present, then inserting
                if(res.data.length == 0) {
                axios.post(full + "/api/lender/create", data)
                    .then((res) => {
                        alert("Customer added successfully: " + res.data);
                    })
                    .catch((error) => {
                        alert("Error: " + error.response.data.message);
                    });
                }
                else {
					alert("The customer based on the VIN number already exists.");
				}
            })
            .catch((error) => {
                alert("Error: " + error.response.data.message);
            });
    }
    catch (error) {
        alert("Error: " + error.message);
    }
}