$('#saveItem').click(function (){
    var item={
        Iid:$('#itemId').val(),
        IName:$('#itemName').val(),
        IQuantity:$('#itemQty').val(),
        IPrice:$('#itemPrice').val()
    };

    if (!item.IName || !item.IPrice || !item.IPrice){
        alert("fill the all fields");
        return;
    }
    $.ajax({
        url:'http://localhost:8080/api/v2/save_item',
        type:'POST',
        contentType:'application/json',
        data:JSON.stringify(item),
        success:function (){
            alert("Item saved successfully");

            $('#itemName').val(''),
            $('#itemQty').val(''),
                $('#itemPrice').val('')
        },
        error:function (err){
            alert("Error saving customer!")
        }
    })
})