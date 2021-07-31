
$( document ).ready(function() {
    getData();
    $('#submitBtn').click(saveNewItem);
});

function getData(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/items",
        mimeType: "application/json",
        success: function(data){
            $.each(data, function(index , value){
                addNewRow(value);
            });
        }
    });
}

function updateData(value){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/items/" + value.id,
        mimeType: "application/json",
        success: function(data){
            value = data;
        }
    });
}


function deleteItem(id){
    var url = 'http://localhost:8080/items/' + id;
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function(){
            $('#row' + id).remove();
        }
    });
}

function saveNewItem(){
    var shoppingItemData = {};
        shoppingItemData["name"] = $("#name").val();
        shoppingItemData["amount"] = $("#amount").val();

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "http://localhost:8080/items",
        data : JSON.stringify(shoppingItemData),
        dataType : 'json',
        success : function(data) {
          addNewRow(data);
        }
    });
}

function reduceByOne(value){
    var patch = [
        { "op": "replace", "path": "/amount", "value": (value.amount - 1)},
    ]

    url = 'http://localhost:8080/items/' + value.id;
    if(value.amount > 1) {
        $.ajax({
            type: 'PATCH',
            url: url,
            data: JSON.stringify(patch),
            processData: false,
            contentType: 'application/json-patch+json',
            success: function (data) {
                $('#' + value.id).text(data.amount);
                updateButtons(data);
            }
        });
    }
}

function  increaseByOne(value){
    var patch = [
        { "op": "replace", "path": "/amount", "value": (value.amount + 1)},
    ]

    url = 'http://localhost:8080/items/' + value.id;
        $.ajax({
            type: 'PATCH',
            url: url,
            data: JSON.stringify(patch),
            processData: false,
            contentType: 'application/json-patch+json',
            success: function (data) {
                $('#' + value.id).text(data.amount);
                updateButtons(data);
            }
        });
}

function updateButtons(data){
    var plusBtnId = 'plus' + data.id;
    var minusBtnId = 'minus' + data.id;

    $('#' + plusBtnId).off()
        .click(function () {
            increaseByOne(data);
        })
    $('#' + minusBtnId).off()
        .click(function () {
            reduceByOne(data);
        });
}

function addNewRow(value){
    var newValue ,btnId, rowId, plusBtnId, minusBtnId;
    btnId = 'btn' + value.id;
    plusBtnId = 'plus' + value.id;
    minusBtnId = 'minus' + value.id;
    rowId = 'row' + value.id;

    newValue = '';
    newValue += '<tr id=' + rowId + '>';
    newValue += '<td>' + value.name + '</td>';
    newValue += '<td id =' + value.id  + '>' + value.amount + '</td>';
    newValue += '<td><button id =' + minusBtnId + '>' + '-</button> </td>';
    newValue += '<td><button id =' + plusBtnId + '>' + '+</button> </td>';
    newValue += '<td><button id =' + btnId + '>' + 'DELETE</button> </td>';
    newValue += '</tr>';

    $('#mainTable').append(newValue);

    $('#' + btnId).click(function (){
        deleteItem(value.id);
    });

    $('#' + minusBtnId).click(function () {
        reduceByOne(value);
    });

    $('#' + plusBtnId).click(function () {
        increaseByOne(value);
    });
}


