$('#saveCustomer').click(function (){
    var customer={
        cid: $('#customerId').val(),
        cname: $('#customerName').val(),
        caddress: $('#customerAddress').val(),
        cphone:$('#customerContact').val()
    }
    if (!customer.cname || !customer.caddress || !customer.cphone){
        alert("fill all fields")
        return;
    }
    $.ajax({
        url:'http://localhost:8080/api/v1/customer',
        type:'POST',
        contentType:'application/json',
        data:JSON.stringify(customer),
        success: function (data) {
            alert("Customer saved successfully!");
            console.log(data);

            // Clear inputs after save
            $('#cid').val('');
            $('#cname').val('');
            $('#caddress').val('');
            $('#cphone').val('');
        },
        error: function (err) {
            alert("Error saving customer! Check console for details.");
            console.error(err.responseText);
        }
    });

})