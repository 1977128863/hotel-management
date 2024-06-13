layui.use(['form', 'layer', 'laydate'], function () {
    var nickName = GetUrlParam("nickName");
    var form = layui.form;
    var layer = layui.layer

    var laydate = layui.laydate;
    laydate.render({
        elem: '#birthday'
        , type: 'datetime'
        , range: false
    });
    //获取数据，进行数据回填
    var load = layer.load(2);
    $.ajax({
        url: config.back + "/user/getUser",
        data: {
            nickName: nickName
        },
        type: "post",
        dataType: "json",
        success: function (res) {
            layer.close(load);
            if (res.code == 0) {
                layer.close(load);
                var admin = res.data;
                //数据回填   editForm双引号中的是filter
                form.val("editForm", admin[0])
                $("#idCard").val(($("#idCard").val()).replace(/^(.{6})(?:\d+)(.{2})$/, "$1**********$2"))
            } else if (res.code == -1) {
                layer.close(load);
                lay.msg(res.message);
            }
        }
    })


//  //提交
    form.on("submit(edit)", function (obj) {
        console.log(obj.field)
        var load = layer.load(2);//必须写   加载框 不然可以一直提交
        $.ajax({
            url: config.back + "/user/editUser",
            data: obj.field,
            dataType: "json",
            type: "post",
            success: function (res) {
                layer.close(load);
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