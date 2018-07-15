function deleteUser(userId) {
    $.ajax({
        url: 'user?userId=' + userId,
        type: "DELETE",
        success: function () {
            location.reload();
        }
    });
}

function call(form) {
    var msg = $(form).serialize();
    $.ajax({
        type: 'POST',
        url: 'user?' + msg,
        success: function (data) {
            location.reload();
        }
    });
}

function editUser(userId) {
    var form = $("#detailsForm");
    $.get('user?action=getUserByID&userId='+userId, function (data) {
        $.each(JSON.parse(data), function (key, value) {
            form.find("input[name=" + key + "]").val(value);
        });
    });
    $("#editRow").modal();
}

function add() {
    var form = $("#detailsForm");
    form.find(":input:not(.btn)").val("");
    $("#editRow").modal();
}

