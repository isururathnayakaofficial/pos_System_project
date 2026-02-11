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
            loadAllItems();
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

$('#updateItem').click(function (){
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
        url:'http://localhost:8080/api/v2/update_item',
        type:'PUT',
        contentType:'application/json',
        data:JSON.stringify(item),
        success:function (){
            loadAllItems();
            alert("Item updated successfully");

            $('#itemName').val(''),
                $('#itemQty').val(''),
                $('#itemPrice').val('')
        },
        error:function (err){
            alert("Error Update item!")
        }
    })
})
function loadAllItems(){
    $.ajax({
        url: 'http://localhost:8080/api/v2/get_items',
        method:'GET',
        dataType:'json',
        success:function (items){
            let tableBody = $("#itemTable tbody");
            tableBody.empty();

            items.forEach(c => {
                tableBody.append(`
                    <tr>
                        <td class="item-id">${c.Iid}</td>
                        <td class="item-name">${c.IName}</td>
                        <td class="item-quantity">${c.IQuantity}</td>
                        <td class="item-price">${c.IPrice}</td>
                    </tr>
                `);
            });
        },
        error: function(err) {
            console.error("Error fetching items:", err);
        }
    });


}
$(document).ready(function (){
    loadAllItems();
});

$(document).on('click','#itemTable tbody tr',function (){
    let id=$(this).find('.item-id').text();
    let name=$(this).find('.item-name').text();
    let quantity=$(this).find('.item-quantity').text();
    let price=$(this).find('.item-price').text();

    $("#itemId").val(id);
    $("#itemName").val(name);
    $("#itemQty").val(quantity);
    $("#itemPrice").val(price);



})