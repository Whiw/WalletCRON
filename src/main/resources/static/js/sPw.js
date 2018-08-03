$.ajax({
    url: '/api/checkSecPw',
    type: 'get',
    contentType : "application/json; charset=UTF-8",
    success: function (args) {
        console.log(args);
        if(args == 'secPwExist!!'){
            $('#sPwCheck').prop('checked', true);
            $("#deleteSecPw").css("display", "block");
        }else{
            $("#setSecPw").css("display", "block");
        }
    }
});

function setSecPw() {
    if($('#sPwInput').val().length == 0){
        alert('입력해주세요!');
    }else if($('#sPwInput').val().length < 4
        || !/^[0-9]*$/.test($('#sPwInput').val())){
        alert('4자리 숫자를 입력해주세요!');
    }else if(!$('#sPwCheck').prop('checked')){
        alert('체크해주세요!');
    }else{

        var data = {
            sPw: $('#sPwInput').val()
        }

        $.ajax({
            url: '/api/setSecPw',
            type: 'get',
            data: data,
            contentType : "application/json; charset=UTF-8",
            success: function (args) {
                if(args == 'setSuccess!!'){
                    alert('설정완료!');
                    $('#sPwInput').val("");
                    $("#setSecPw").css("display", "none");
                    $("#deleteSecPw").css("display", "block");
                }else{
                    alert('설정실패!');
                }
            }
        });
    }
}

function deleteSecPw() {
    if($('#sPwInput').val().length == 0){
        alert('입력해주세요!');
    }else if($('#sPwInput').val().length < 4
        || !/^[0-9]*$/.test($('#sPwInput').val())){
        alert('4자리 숫자를 입력해주세요!');
    }else{
        var data = {
            sPw: $('#sPwInput').val()
        }

        $.ajax({
            url: '/api/deleteSecPw',
            type: 'get',
            data: data,
            contentType : "application/json; charset=UTF-8",
            success: function (args) {
                if(args == 'sPwDeleteSuccess!!'){
                    $('#sPwInput').val("");
                    $("#setSecPw").css("display", "block");
                    $("#deleteSecPw").css("display", "none");
                    $('#sPwCheck').prop('checked', false);
                    alert('해제되었습니다!');
                }else{
                    alert('틀린 비밀번호입니다!');
                }
            }
        });
    }
}