$( document ).ready(function() {
    getData();
    getListsData();
    $('#firstListDiv').click(function (){
        $('.appendedRaw').remove();
        getData();
        $('#saveBtn').text("SAVE LIST").click(saveList);
        $('#title').text("New Shopping List");
    });
    $('#submitBtn').click(saveNewItem);
    $('#saveBtn').click(saveList);
});


function getData(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/items/noListObjects",
        mimeType: "application/json",
        success: function(data){
            $.each(data, function(index , value){
                addNewRow(value);
            });
        }
    });
}

function getListsData(){
    $.ajax({
        dataType: "json",
        url: "http://localhost:8080/lists",
        mimeType: "application/json",
        success: function(data){
            $.each(data, function(index , value){
                addNewDiv(value)
            });
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

function deleteList(id){
    var url = 'http://localhost:8080/lists/' + id;
    $.ajax({
        url: url,
        type: 'DELETE'
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

function saveList(){
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "http://localhost:8080/lists",
        dataType : 'json',
        success : function (data){
            data.items.forEach(function (item) {
                $('#row' + item.id).remove();
            });
            addNewDiv(data);
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
    newValue += '<tr id=' + rowId + ' class = "appendedRaw">';
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

function addNewDiv(data){
    var newDiv =  document.createElement('DIV');
    newDiv.setAttribute("id", "div" + data.id);
    newDiv.setAttribute("class", "listDiv");
    $('#listsContainer').append(newDiv);
    $('#div' + data.id).click(function (){
        divOnClick(data);
    });
    var name = document.createElement('h1');
    name.innerText = data.id;
    newDiv.appendChild(name);
}

function divOnClick(data){
    $('.appendedRaw').remove();
    var url = 'http://localhost:8080/lists/' + data.id;
    console.log("git");
    $.ajax({
        url: url,
        type: 'GET',
        success: function(data){
            data.items.forEach(function (item) {
                addNewRow(item);
            });
            $('#saveBtn').text("DELETE LIST").off().
            click(function () {
                $('#div' + data.id).remove();
                deleteList(data.id);
            })
            $('#title').text("List" + data.id);

        }
    });
}


