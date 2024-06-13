var title = GetUrlParam('title');
var menuId = GetUrlParam('menuId');
var topMenuId = GetUrlParam('topMenuId');

layui.use(['form', 'laypage', 'layer', 'transfer', 'util'], function () {
    $("#classifyPId").html(title)
    var form = layui.form
    var layer = layui.layer
    //数据回填
    $.ajax({
        url: config.back + "/menu/getMenuForEcho",
        data: {
            menuId: menuId
        },
        type: "post",
        dataType: "json",
        success: function (res) {
            if (res.code == 200) {
                var menu = res.data;
                //数据回填   editForm双引号中的是filter
                form.val("editForm", menu[0])
            } else if (res.code == 300) {
                layer.msg(res.message);
            }
        }
    })


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
                    topMenuId: topMenuId,
                    menuId: menuId
                },
                type: "post",
                async: false,// 同步
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

    //监听提交
    form.on('submit(Save)', function (data) {
        $.ajax({
            url: config.back + "/menu/editMenu",
            type: "post",
            data: data.field,
            dataType: "json",
            // async : false,
            success: function (data) {

                if (data.code == 200) {
                    layui.layer.msg(data.message, {icon: 1, time: 1000});
                    parent.zNodes2();
                } else {
                    layui.layer.msg(data.message, {icon: 2, time: 1000});
                }

            },
            error: function () {
                layui.layer.msg("网络错误，请稍后再试，或联系管理员！", {icon: 2, time: 1000});
            }
        });
        return false

    });
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

