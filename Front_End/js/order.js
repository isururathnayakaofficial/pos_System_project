let itemList = [];
let selectedItem = null;
let cart = [];

$(document).ready(function () {
    loadCustomerIds();
    loadItemIds();
});


// ================= LOAD CUSTOMERS =================
function loadCustomerIds() {
    $.ajax({
        url: "http://localhost:8080/api/v1/get_customers",
        method: "GET",
        success: function (response) {

            $("#customerId").empty();
            $("#customerId").append(`<option selected disabled>Select Customer</option>`);

            response.forEach(function (customer) {
                $("#customerId").append(
                    `<option value="${customer.cid}">${customer.cid}</option>`
                );
            });
        },
        error: function () {
            alert("Failed to load customers");
        }
    });
}


// ================= LOAD ITEMS =================
function loadItemIds() {
    $.ajax({
        url: "http://localhost:8080/api/v2/get_items",
        method: "GET",
        success: function (response) {

            itemList = response;

            $("#itemId").empty();
            $("#itemId").append(`<option selected disabled>Select Item</option>`);

            response.forEach(function (item) {
                $("#itemId").append(
                    `<option value="${item.Iid}">${item.Iid}</option>`
                );
            });
        },
        error: function () {
            alert("Failed to load items");
        }
    });
}


// ================= ITEM SELECT =================
$("#itemId").on("change", function () {

    let selectedId = $(this).val();

    selectedItem = itemList.find(function (item) {
        return item.Iid === selectedId;
    });

    if (selectedItem) {
        $("#itemName").val(selectedItem.IName);
        $("#itemPrice").val(selectedItem.IPrice);
    } else {
        $("#itemName").val("");
        $("#itemPrice").val("");
    }
});

$("#addToList").click(function () {

    let customerId = $('#customerId').val();
    let itemId = $('#itemId').val();
    let qty = parseInt($('#orderQty').val());
    let itemName = $('#itemName').val();
    let price = parseFloat($('#itemPrice').val());

    if (!customerId) {
        alert("Please select a customer!");
        return;
    }

    if (!itemId) {
        alert("Please select an item!");
        return;
    }

    if (!qty || qty <= 0) {
        alert("Enter valid quantity!");
        return;
    }

    //  REAL TIME STOCK CHECK FROM DATABASE
    $.ajax({
        url: "http://localhost:8080/api/v3/item_stock/" + itemId,
        method: "GET",
        success: function (dbQty) {

            if (dbQty === 0) {
                alert("This item is OUT OF STOCK!");
                return;
            }

            if (qty > dbQty) {
                alert("Stock exceeded! Available quantity: " + dbQty);
                return;
            }

            // If stock OK → add to cart
            let existing = cart.find(item => item.itemId === itemId);

            if (existing) {

                if ((existing.qty + qty) > dbQty) {
                    alert("Stock exceeded! Available quantity: " + dbQty);
                    return;
                }

                existing.qty += qty;
                existing.total = existing.qty * existing.price;

            } else {

                cart.push({
                    itemId: itemId,
                    customerId: customerId,
                    itemName: itemName,
                    price: price,
                    qty: qty,
                    total: qty * price
                });
            }

            renderTable();
            clearFields();
        },
        error: function () {
            alert("Failed to check stock from database");
        }
    });

});

$('#placeorder').click(function () {

    if (cart.length === 0) {
        alert("Cart is empty!");
        return;
    }

    let updateRequests = [];

    // 1️⃣ First update all item quantities
    cart.forEach(function (cartItem) {

        let updateObj = {
            itemId: cartItem.itemId,
            qty: cartItem.qty
        };

        let request = $.ajax({
            url: "http://localhost:8080/update_item",
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(updateObj)
        });

        updateRequests.push(request);
    });

    // 2️⃣ After ALL updates success → place order
    $.when.apply($, updateRequests).done(function () {

        $.ajax({
            url: 'http://localhost:8080/api/v3/place_order',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(placeOrder),
            success: function () {
                alert("Order placed successfully!");

                cart = [];
                renderTable();
            },
            error: function () {
                alert("Error placing order!");
            }
        });

    }).fail(function () {
        alert("Error updating items!");
    });

});



// ================= RENDER TABLE =================
function renderTable() {

    $("#orderTable tbody").empty();

    cart.forEach(function (item, index) {

        $("#orderTable tbody").append(`
            <tr>
                <td>${item.itemId}</td>
                <td>${item.customerId}</td>
                <td>${item.itemName}</td>
                <td>${item.price.toFixed(2)}</td>
                <td>${item.qty}</td>
                <td>${item.total.toFixed(2)}</td>
                <td>
                    <button class="btn btn-sm btn-danger" onclick="removeItem(${index})">
                        Remove
                    </button>
                </td>
            </tr>
        `);
    });

    calculateGrandTotal();
}


// ================= REMOVE ITEM =================
function removeItem(index) {
    cart.splice(index, 1);
    renderTable();
}


// ================= GRAND TOTAL =================
function calculateGrandTotal() {

    let grandTotal = 0;

    cart.forEach(function (item) {
        grandTotal += item.total;
    });

    $("#grandTotal").text("Rs. " + grandTotal.toFixed(2));
}


// ================= CLEAR FIELDS =================
function clearFields() {
    $("#itemId").val("");
    $("#itemName").val("");
    $("#itemPrice").val("");
    $("#orderQty").val("");
    selectedItem = null;
}