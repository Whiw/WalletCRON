$(document).ready(function(){
    //console.log($('#otp_text').text());

    var otpInfo = [[${otpInfo}]];
    console.log('abc : ' + otpInfo);
    console.log(otpInfo[0])
    if($('#otp_text').text().length > 0){
        $('#qrImg').attr('src', 'https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=200x200&chld=M|0' +
            '&cht=qr&chl=otpauth://totp/'+ '${otpInfo[0]}' +'%3Fsecret%3D' + '${otpInfo[1]}');
        //$('#otp_text').text('${otpInfo[1]}');
        $("#delete_otp_container").show();
    }else{
        $("#add_otp_container").show();
    }
});

function createQr() {

    if(!$('#add_opt_checkbox').prop('checked')){
        alert('OTP사용에 체크해주세요!');
    }else{
        $.ajax({
            url: '/api/createOtp.json',
            type: 'get',
            contentType : "application/json; charset=UTF-8",
            success: function (args) {
                console.log(args.length);
                if(args.length > 1){
                    $('#qrImg').attr('src', 'https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=200x200&chld=M|0' +
                        '&cht=qr&chl=otpauth://totp/'+args[0]+'%3Fsecret%3D'+args[1]);

                    $("#delete_otp_container").show();
                    $("#add_otp_container").hide();
                    alert('생성되었습니다!');
                    location.reload();
                }else{
                    alert('생성실패!');
                }
            }
        });
    }
}

function deleteQr() {
    if(!$('#delete_opt_checkbox').prop('checked')){
        alert('OTP 해제에 체크해주세요!');
    }else{
        $.ajax({
            url: '/api/deleteOTP',
            type: 'get',
            contentType : "application/json; charset=UTF-8",
            success: function (args) {
                if(args == 'deleteSuccess!!'){
                    alert('삭제되었습니다!');
                    location.reload();
                }else if('deleteFail!!'){
                    alert('삭제실패!');
                }

            }
        });
    }
}