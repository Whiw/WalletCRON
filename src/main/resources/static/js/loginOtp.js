function otpLogin() {
    var code = $('#otp_login_key').val();

    var data = {
        code: code
    };

    $.ajax({
        url: '/api/loginOTP',
        type: 'get',
        data : data,
        contentType : "application/json; charset=UTF-8",
        success: function (args) {
            console.log(args);

            if(args == 'otpLoginSuccess!!'){
                location.reload();
            }else if(args == 'otpLoginFail!!'){
                alert('로그인 실패!');
            }
        }
    });
}