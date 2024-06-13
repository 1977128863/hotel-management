$.ajax({
    url: "back/roomInfo/getRoomInfo",
    type: "post",
    dataType: "json",
    success: function (res) {
        if (res.code == 0) {
            var roomList = res.data
            $.each(roomList, function (index, item) {
                $("#goods").append("<div class='col-md-4 room-sec'>" +
                    "<a href='#'><img src='fileUpload/" + item.roomImg1 + ".jpg' alt='' width='350px' height='160px'/></a>" +
                    "<div class='caption'>" +
                    "<span>&#8356; 129</span>" +
                    "<a href='/html/front/roomDetail.html?roomCode=" + item.roomCode + "'>预定</a>" +
                    "</div>" +
                    "<h4>" + item.roomTitle + "</h4>" +
                    "<p>" + item.remark + "</p>" +
                    "</div>")
            })

        } else if (res.code == -1) {

        }
    }
});

