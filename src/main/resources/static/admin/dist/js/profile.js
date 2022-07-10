$(function () {

    $('#updateUserNameButton').click(function () {
        $("#updateUserNameButton").attr("disabled",true);
        var userName = $('#loginUserName').val();
        var nickName = $('#nickName').val();
        if (validUserNameForUpdate(userName, nickName)) {

            var params = $("#userNameForm").serialize();
            $.ajax({
                type: "POST",
                url: "/admin/profile/name",
                data: params,
                success: function (r) {
                    $("#updateUserNameButton").attr("disabled",false);
                    console.log(r);
                    if (r == 'success') {
                        alert('Modified successfully');
                    } else {
                        alert('fail to edit');
                    }
                }
            });
        }else{
            $("#updateUserNameButton").attr("disabled",false);
        }

    });

    $('#updatePasswordButton').click(function () {
        $("#updatePasswordButton").attr("disabled",true);
        var originalPassword = $('#originalPassword').val();
        var newPassword = $('#newPassword').val();
        if (validPasswordForUpdate(originalPassword, newPassword)) {
            var params = $("#userPasswordForm").serialize();
            $.ajax({
                type: "POST",
                url: "/admin/profile/password",
                data: params,
                success: function (r) {
                    $("#updatePasswordButton").attr("disabled",false);
                    console.log(r);
                    if (r == 'success') {
                        alert('Modified successfully');
                        window.location.href = '/admin/login';
                    } else {
                        alert('fail to edit');
                    }
                }
            });
        }else {
            $("#updatePasswordButton").attr("disabled",false);
        }

    });
})


function validUserNameForUpdate(userName, nickName) {
    if (isNull(userName) || userName.trim().length < 1) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("Please enter your login name!");
        return false;
    }
    if (isNull(nickName) || nickName.trim().length < 1) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("username can not be empty!");
        return false;
    }
    return true;
}

/**
 * 密码验证
 */
function validPasswordForUpdate(originalPassword, newPassword) {
    if (isNull(originalPassword) || originalPassword.trim().length < 1) {
        $('#updatePassword-info').css("display", "block");
        $('#updatePassword-info').html("Please enter the original password!");
        return false;
    }
    if (isNull(newPassword) || newPassword.trim().length < 1) {
        $('#updatePassword-info').css("display", "block");
        $('#updatePassword-info').html("The new password cannot be empty!");
        return false;
    }

    return true;
}
