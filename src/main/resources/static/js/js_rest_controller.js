
$(document).ready(function() {
    alert("Hello! I am an alert box!!");
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
            $('<td>').text((user.roles).map(r => r.role.replace('ROLE_','')).join(', '))
     /*       $('<td>').append($('<button>').text("Edit").attr({
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
            }).data("user", user))*/
        ).appendTo('#tbody')
    })
}

/*alert("Hello! I am an alert box!!");*/
/*
$(document).on("click", "#btnNewUser", function () {
    let user =$("#formNewUser").serialize();
    $('#table-tab').trigger('click');
    $('#nameNewUser').val('');
    $('#lastNameNewUser').val('');
    $('#ageNewUser').val('');
    $('#emailNewUser').val('');
    $('#passNewUser').val('');

    $.ajax({
        type: 'POST',
        url: '/admin/index',
        data: user,
        timeout: 3000,
        success: function (){
            getAllUser()
        }
    });
});
*/
