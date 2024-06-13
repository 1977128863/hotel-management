layui.use(['form', 'layer'], function () {
    var roomId = GetUrlParam("roomId");
    var form = layui.form;
    var layer = layui.layer

    var list = getClassifyByKey()
    for (let i = 0; i < list.length; i++) {
        $("#classifyId").append("<option value='" + list[i].classifyId + "' >" + list[i].classifyName + "</option>")
    }
    form.render()
    //获取数据，进行数据回填
    var load = layer.load(2);
    $.ajax({
        url: config.back + "/roomInfo/getRoomInfo",
        data: {
            roomId: roomId
        },
        type: "post",
        dataType: "json",
        success: function (res) {
            layer.close(load);
            if (res.code == 0) {
                layer.close(load);
                var rooms = res.data;
                //数据回填   editForm双引号中的是filter
                $("#roomTitle").val(rooms[0].roomTitle);
                $("#roomSize").val(rooms[0].roomSize);
                $("#classifyId").val(rooms[0].classifyId);
                $("#roomStatus").val(rooms[0].roomStatus);
                $("#integral").val(rooms[0].integral);
                $("#remark").val(rooms[0].remark);
                $("#oneImg").val(rooms[0].roomImg1);
                $("#roomCode").val(rooms[0].roomCode);
                $("#roomId").val(rooms[0].roomId);
                $("#roomImg1").attr("src", "/fileUpload/" + rooms[0].roomImg1 + ".jpg");
                // form.val("editForm",rooms[0])
            } else if (res.code == -1) {
                layer.close(load);
                lay.msg(res.message);
            }
        }
    })


//  //提交
    form.on("submit(edit)", function (obj) {
        var load = layer.load(2);//必须写   加载框 不然可以一直提交
        obj.field.roomImg1 = $("#oneImg").val();
        $.ajax({
            url: config.back + "/roomInfo/editRoomInfo",
            data: obj.field,
            dataType: "json",
            type: "post",
            success: function (res) {
                if (res.code == 200) {
                    layer.msg(res.message, {
                        icon: 1, time: 1000
                    }, function () {
                        //关闭弹窗
                        var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
                        parent.layer.close(index);
                        //parent.xxx调用父页面函数  -->刷新页面
                        parent.fresh();
                    });
                } else if (res.code == 300) {
                    layer.close(load);
                    layer.msg(res.message, {icon: 2, time: 1000});
                }
            }
        })
        return false;
    })
})

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

function selectImg() {
    var formData = new FormData();
    formData.append("file", $("#chooseImgOne")[0].files[0])
    $.ajax({
        url: config.back + "/uploadFile",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            // console.log(data.fileName)
            if (data.code == 200) {
                $("#roomImg1").attr("src", "/fileUpload/" + data.fileName);
                $("#oneImg").val(data.imgCode);
                layer.msg(data.message, {icon: 1, time: 1000})
            } else {
                layer.msg(data.message, {icon: 2, time: 1000})
            }
        },
        error: function (data) {
            layer.msg("上传失败", {icon: 2, time: 1000})
        }
    })

}

function imgClickOne() {
    $("#chooseImgOne").click();
}
