var address;
var countList = 5;//한페이지 게시물수
var page = 0;//현재 페이지
var totalCount;//총 게시물 수
var totalPage;//총 페이지 수
var startPage = 0;
var startRow = 1;
var array;

$(document).ready(function(){

    $("#addressListContainer").on('click', "#address", function (event) {
        if(address != $( this ).text()) {
            address = $( this ).text();
            console.log('cl');
            getAddress();

        }
    });

    $('#page_container').on('click', 'span', function (event) {

        if(page + 1 != $(this).text()){
            page = $(this).text() -1;
        }

        showLog(array);


    });
});



function getAddress() {
    pageReset()
    getTradeLog();
}

function getTradeLog() {
    $('#click_attention').css('display', 'none');
    var data = {
        account: address,
    }
    $.ajax({
        url: '/api/getTradeLog.json',
        type: 'get',
        contentType : "application/json; charset=UTF-8",
        data: data,
        success: function (args) {
            if(args.length > 0){
                console.log('exist');
                $('#none_log_attention').css('display', 'none');
            }else{
                console.log('none');
                $('#none_log_attention').css('display', 'block');
            }
            if(args.length > 0){
                array = args;

                totalCount = array.length;//총 게시물수 구하기
                //총 페이지수 구하기
                totalPage = parseInt(totalCount / countList);

                if (parseInt(totalCount % countList) > 0) {
                    totalPage++;
                }
                $('#tradeList').show();
                showLog(array);
            }


        }
    });
}

function showLog(array){
    var table = '';
    startRow = (page) * 5 + 1;
    var endRow = startRow - 1 + countList;

    if(endRow > totalCount){
        endRow = totalCount;
    }
    for(var i = startRow - 1; i < endRow; i ++ ){
        console.log(i);
        console.log(array[i].destination);
        console.log(array[i].account);

        table += '<div class="primary_content_row" style="justify-content: space-between; align-items: center; margin-top: 12px" >' +
            '<div style="text-align: center;">' + array[i].reg_date + '</div>';
        //입금일경우
        if(array[i].destination == address){
            table += '<div class="primary_content_column"><span style="color: red">입금</span>'
                + '<span>' + array[i].account + '</span></div>';
            //송금일경우
        }else if(array[i].account == address){
            table += '<div class="primary_content_column"><span style="color: blue">송금</span>'
                + '<span>' + array[i].destination + '</span></div>';
        }
        table += '<div>' + array[i].amount + '</div></div>';
    }

    $('#tradeList').empty();
    $('#tradeList').append(table);

    createPage();
}

function createPage(){
    var pageList = '';

    for(var i = startPage + 1; i <= totalPage; i ++){
        pageList += '<span>' + i  + '</span>';
    }

    $("#page_container").empty();
    $("#page_container").append(pageList);

}

function pageReset() {
    page = 0;
    startRow = 1;
    startPage = 0;
    array = '';
    $("#page_container").empty();
    $('#tradeList').empty();
}