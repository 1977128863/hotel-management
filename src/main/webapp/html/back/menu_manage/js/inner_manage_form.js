//增加父节点
layui.use(['form', 'layer'], function () {
    form = layui.form,
        layer = layui.layer,
        $ = layui.$;
});

var title = GetUrlParam('name')
var topMenuId = GetUrlParam('id');
//$(function () {
//	document.getElementById('classifyPId').innerHTML=name;
//});

layui.use(['form', 'layer'], function () {


    $("#classifyPId").html(title)

    //监听提交
    form.on('submit(Save)', function (data) {
        data.field.topMenuId = topMenuId
        $.ajax({
            url: config.back + "/menu/addMenu",
            type: "post",
            data: data.field,
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code == 200) {
                    layer.msg(data.message, {icon: 1, time: 1000});
                    parent.zNodes2();
                } else if (data.code == 300) {
                    layer.msg(data.message, {icon: 2, time: 1000});
                } else if (data.code == 500) {
                    layer.msg(data.message, {icon: 2, time: 1000});
                }

            },
            error: function () {
                layer.msg("网络错误，请稍后再试，或联系管理员！", {icon: 2, time: 1000});
            }

        });
        return false;
    });

    //验证
    form.verify({
        orderCheckForEdit: function (value) {
            var flag = false;
            var msg = "";
            var load = layer.load(2);
            $.ajax({
                url: config.back + "/menu/orderCheck",
                data: {
                    orders: value,
                    topMenuId: topMenuId
                },
                type: "post",
                async: false,
                success: function (res) {

                    if (res.code == 300) {
                        layer.close(load);
                        msg = res.message;
                    } else if (res.code == 200) {
                        layer.close(load);
                        flag = true;

                    }
                }
            })

            if (!flag) {
                return msg;
            }
        }

    })


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