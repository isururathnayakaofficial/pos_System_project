// Load all customers into the table
function loadAllCustomers() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/get_customers',
        method: 'GET',
        dataType: 'json',
        success: function(customers) {
            console.log("Customers from backend:", customers);
            let tableBody = $("#customerTable tbody");
            tableBody.empty();

            if (!customers || customers.length === 0) {
                console.warn("No customers found");
                return;
            }

            customers.forEach(c => {
                tableBody.append(`
                    <tr>
                        <td>${c.cid}</td>
                        <td>${c.cname}</td>
                        <td>${c.caddress}</td>
                        <td>${c.cphone}</td>
                    </tr>
                `);
            });
        },
        error: function(err) {
            console.error("Error fetching customers:", err);
        }
    });
}

// Load next customer ID and set in input
function loadNextCustomerId() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/next-id',
        method: 'GET',
        success: function(nextId) {
            $('#customerId').val(nextId);
        },
        error: function(err) {
            console.error("Error fetching next customer ID:", err);
        }
    });
}

// Save customer
$('#saveCustomer').click(function () {
    var customer = {
        cid: $('#customerId').val(),
        cname: $('#customerName').val(),
        caddress: $('#customerAddress').val(),
        cphone: $('#customerContact').val()
    };

    if (!customer.cname || !customer.caddress || !customer.cphone){
        alert("Fill all fields");
        return;
    }

    $.ajax({
        url: 'http://localhost:8080/api/v1/customer',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(customer),
        success: function () {
            alert("Customer saved successfully!");

            $('#customerName').val('');
            $('#customerAddress').val('');
            $('#customerContact').val('');

            loadAllCustomers();      // refresh table
            loadNextCustomerId();    // update next ID
        },
        error: function (err) {
            console.error(err.responseText);
            alert("Error saving customer!");
        }
    });
});

// On page load
$(document).ready(function() {
    loadNextCustomerId(); // auto-fill next ID
    loadAllCustomers();    // load table
});
