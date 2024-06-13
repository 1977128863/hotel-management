layui.use(['form', 'layer', 'table'], function () {
    var form = layui.form;
    var layer = layui.layer
    var table = layui.table

    form.on("submit(add)", function (data) {


        if (data.field.name == null || data.field.name == "") {

            layer.msg("请输入名称")
            return false;
        }

        if (data.field.remark == null || data.field.remark == "") {

            layer.msg("请输入描述")
            return false;
        }
        $.ajax({
            url: config.back + "/data/addData",
            data: data.field,
            type: "post",
            dataType: "json",
            success: function (res) {
                if (res.code == 200) {
                    layer.msg(res.message, {icon: 1, time: 1000}, function () {
                        //获取子页面
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.location.reload("typeList")
                        parent.layer.close(index)
                    })

                } else if (res.code == 300) {
                    layer.msg(res.message)
                } else if (res.code == 500) {
                    layer.msg(res.message)
                }
            },
            error: function () {
                layer.msg("网络错误，请稍后重试")
            }
        })
        return false;
    })


})