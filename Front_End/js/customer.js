$('#saveCustomer').click(function () {

    let customer = {
        cid: $('#customerId').val(),
        cname: $('#customerName').val(),
        caddress: $('#customerAddress').val(),
        cphone: $('#customerContact').val()
    };

    if (!customer.cname || !customer.caddress || !customer.cphone) {
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
            loadAllCustomers();

            // clear inputs
            $('#customerId').val('');
            $('#customerName').val('');
            $('#customerAddress').val('');
            $('#customerContact').val('');

            // 🔥 reload customers
            loadAllCustomers();
        },
        error: function (err) {
            console.error(err.responseText);
            alert("Error saving customer");
        }
    });
});

$(document).ready(function () {
    loadAllCustomers();
});

function loadAllCustomers() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/get_customers',
        method: 'GET',
        dataType: 'json',
        success: function(customers) {
            console.log("Customers from backend:", customers); // Debug

            let tableBody = $("#customerTable tbody"); // append inside tbody
            tableBody.empty(); // clear old rows

            if (!customers || customers.length === 0) {
                console.warn("No customers found");
                return;
            }

            customers.forEach(c => {
                let row = `
                    <tr>
                        <td>${c.cid}</td>
                        <td>${c.cname}</td>
                        <td>${c.caddress}</td>
                        <td>${c.cphone}</td>
                    </tr>
                `;
                tableBody.append(row);
            });
        },
        error: function(err) {
            console.error("Error fetching customers:", err);
        }
    });
}
