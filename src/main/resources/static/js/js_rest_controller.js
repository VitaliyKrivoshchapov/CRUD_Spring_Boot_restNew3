
$(document).ready(function() {
getAllUser();

})

function getAllUser(){
    $.ajax({
        type: 'Get',
        url:'/restAdmin',
        response: 'json',
        success: function (date){
            setAdminTable(date)
        }
    })
}

function setAdminTable(users){
    $("#tbody").empty();
    $.each(users, function (i, user) {
        $('<tr>').append(
            $('<td>').text(user.id),
            $('<td>').text(user.firstName),
            $('<td>').text(user.lastName),
            $('<td>').text(user.age),
            $('<td>').text(user.mail),
            $('<td>').text((user.roles).map(r => r.role.replace('ROLE_','')).join(', ')),
            $('<td>').append($('<button>').text("Edit").attr({
                "type": "button",
                "class": "btn btn-primary btn-sm edit",
                "style": "background-color: royalblue",
                "data-toggle": "modal",
                "data-target": "#editModal",//это ссылки на модалку
            }).data("user", user)),
            $('<td>').append($('<button>').text("Delete").attr({
                "type": "button",
                "class": "btn btn-danger btn-sm delete",
                "data-toggle": "modal",
                "data-target": "#delModal",//это ссылки на модалку
            }).data("user", user))
        ).appendTo('#tbody')

    })
}
//Добавление нового пользователя
$(document).on("click", "#btnNewUser", function () {
    let user =$("#formAddUser").serialize();
    $.ajax({
        url: "/restAdmin/addUser",
        type: "POST",
        data: user,
        timeout: 3000,
        success: function (){
            getAllUser()
            $('#home-tab').trigger('click');
        }
    })
    $("input[id*='NewUser']").val('');//все input с id  со словами newUser очищаются
    //alert   ('I am from AddNewUser Alert')
    //console.log('Привет от JavaScript!');
})



$(document).on("click", ".delete", function () {
    let user = $(this).data("user");
    $("#idDel").val(user.id);
    $("#nameDel").val(user.firstName);
    $("#lastDel").val(user.lastName);
    $("#ageDel").val(user.age);
    $("#emailDel").val(user.mail);
})

//delete User
$(document).on("click", "#btnDel", function () {
    let user = $("#formDel").serialize();
    $.ajax({
        type: 'DELETE',
        url: '/restAdmin/del',// это путь к рест контроллеру
        data: {id: $('#idDel').val()},
        timeout: 3000,
        success: function () {

            $('#delModal').modal('hide')
            $('.btn-close').trigger('click');
           // alert("i am alert from  ")
            getAllUser()
        }
    })
})



$(document).on("click", ".edit", function () {
    let user = $(this).data("user");
    $("#idEdit").val(user.id);
    $("#nameEdit").val(user.firstName);
    $("#lastEdit").val(user.lastName);
    $("#ageEdit").val(user.age);
    $("#emailEdit").val(user.mail);
    $("#passwordEdit").val(user.password);
})
$(document).on("click", "#btnEdit", function () {
    let user = $("#formToEdit").serialize();
    $.ajax({
        type: "PUT",
        url: '/restAdmin/edit',
        data: user,
        timeout: 3000,
        success: function (){
            $('#editModal').modal('hide')
            $('.btn-close').trigger('click');
             getAllUser()
        }
    })
})

