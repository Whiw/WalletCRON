var reg = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;


function showPwChange() {
    $('#changePwBtn').removeClass("button_primary").addClass("button_dark_fixed");
    $('#changeHpBtn').removeClass("button_dark_fixed").addClass("button_primary");
    $("#changePwBtn").prop('disabled', true);
    $("#changeHpBtn").prop('disabled', false);
    $('#changePhoneForm').hide();
    $('#changePwForm').show();
}

function showHpChange() {
    $('#changePwBtn').removeClass("button_dark_fixed").addClass("button_primary");
    $('#changeHpBtn').removeClass("button_primary").addClass("button_dark_fixed");
    $("#changeHpBtn").prop('disabled', true);
    $("#changePwBtn").prop('disabled', false);
    $('#changePwForm').hide();
    $('#changePhoneForm').show();
}

function changePw() {
    var pw = $("#pw").val();
    var pwChk = $("#pwChk").val();
    var data = {
        password: pw

    }

    if(pw != pwChk){
        alert("패스워드가 일치하지 않습니다!");
    }else if(!reg.test(pw)){
        alert('영문/숫자 조합의 패스워드 6 ~ 14자리를 입력해주세요!');
    }else{
        $.ajax({
            url: '/api/changePw',
            type: 'get',
            data: data,
            contentType : "application/json; charset=UTF-8",
            success: function (args) {
                console.log(args);
                if(args == 'pwChangeSuccess!!'){
                    alert('수정되었습니다!!');
                    $(location).attr('href', '/');
                }else{
                    alert('수정실패!!')
                }
            }
        });
    }
}


function changeHP() {

    if($("#phone").val().length == 0){
        alert('휴대폰 번호를 입력해주세요!');
    }else if($('#auth_btn').prop('disabled') == false){
        alert('휴대폰 인증을 진행해주세요!');
    }else{
        console.log('success!');
        var data = {
            phone: $("#phone").val()
        }
        $.ajax({
            url: '/api/changeHp',
            type: 'get',
            data: data,
            contentType : "application/json; charset=UTF-8",
            success: function (args) {
                console.log(args);
                if(args == 'HpChangeSuccess!!'){
                    alert('수정되었습니다!!');
                    $(location).attr('href', '/');
                }else{
                    alert('수정실패!!')
                }
            }
        });
    }
}

function sendSms() {
    var data;

    if($("#phone").val().length == 0){
        alert('휴대폰 번호를 입력해주세요!');
    }else{
        $.getJSON('https://api.ipify.org?format=jsonp&callback=?', function(data) {
            var result = JSON.parse(JSON.stringify(data, null, 2));
            this.ip =  result.ip;
            this.data = {
                num: $("#phone").val(),
                ip_addr: this.ip
            };

        }).then(function(){
            $.ajax({
                url: '/api/checkHp',
                type: 'post',
                data: JSON.stringify(this.data),
                contentType : "application/json; charset=UTF-8",
                success: function (args) {
                    console.log(args);
                    if(args == 'HpCheckComplete!!') {
                        alert('전송되었습니다!');
                        $('#sms_code').prop('disabled', false);
                        $('#sms_code').val('');
                        $('#auth_btn').prop('disabled', false);
                        $('#phone').prop('disabled', true);
                    }else if('HpDuplicated'){
                        alert('중복되는 번호입니다!!');
                    }else{
                        alert('전송실패!');
                    }
                }
            });
        });
    }
}

function checkCode() {
    var sms_code = $("#sms_code").val();
    var num = $('#phone').val();

    if ( sms_code.length > 0 && sms_code != "Default text" ){

        var data = {
            code: sms_code,
            num: num
        }

        $.ajax({
            url: '/api/chkCode',
            type: 'post',
            data: JSON.stringify(data),
            contentType : "application/json; charset=UTF-8",
            success: function (args) {
                console.log(args);
                if(args == 'codeChkSuccess!!'){
                    alert('인증완료!');
                    $('#sms_code').prop('disabled', true);
                    $('#sms_code').val('인증완료!');
                    $('#auth_btn').prop('disabled', true);
                }else{
                    alert('잘못된 인증번호입니다!');
                }
            }
        });
    }else{
        alert("인증코드를 입력해주세요!");

    }
}