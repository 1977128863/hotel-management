var roomCode = GetUrlParam("roomCode");
$.ajax({
    url: "back/roomInfo/getRoomInfo",
    type: "post",
    data: {
        roomCode: roomCode
    },
    dataType: "json",
    success: function (res) {
        if (res.code == 0) {
            var room = res.data[0]
            $("#room").html("欢迎预定我们酒店" + room.roomTitle)
            $("#reservation").append("<div class='t_img'>" +
                "<img src='fileUpload/" + room.roomImg1 + ".jpg' alt='' width='650px' height='400px'/>" +
                "</div>" +
                "<div class='t_wen'>" +
                "<h1>" + room.roomTitle + "</h1>" +
                "<div class='t_cen'>" +
                "<p>床型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;席梦思</p>" +
                "<p>宽带:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;免费WiFi</p>" +
                "<p>标准价:&nbsp;&nbsp;&nbsp;&nbsp;" + "价格暂未关联" + "</p>" +
                "<p>简介:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + room.remark + "</p>" +
                "</div>" +
                "<div class='yud'>" +
                "<a href='/html/front/reservation.html?roomTitle=" + room.roomTitle + "'><span>立即预定</span></a>" +
                "</div>" +
                "</div>")

        } else if (res.code == -1) {

        }
    }
});

//根据参数名获取地址栏参数
function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");
    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;
        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");
            if (arr != null && arr[0] == paraName) {
                return decodeURI(arr[1]);
            }
        }
        return "";
    } else {
        return "";
    }

}
