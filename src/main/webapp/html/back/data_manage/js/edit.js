layui.use(['form', 'layer', 'table'], function () {
    var form = layui.form;
    var layer = layui.layer
    var table = layui.table

    form.on("submit(edit)", function (data) {
        //获取子页面
        var index = parent.layer.getFrameIndex(window.name);

        if (data.field.name == null || data.field.name == "") {

            layer.msg("请输入名称")
            return false;
        }

        if (data.field.remark == null || data.field.remark == "") {

            layer.msg("请输入描述")
            return false;
        }
        $.ajax({
            url: config.back + "/data/editData",
            data: data.field,
            type: "post",
            dataType: "json",
            success: function (res) {
                if (res.code == 200) {
                    layer.msg(res.message, {
                        icon: 1, time: 1000
                    }, function () {
                        parent.layui.table.reload("typeList")
                        parent.layer.close(index)
                    })

                } else if (res.code == 300) {
                    layer.msg(res.message)
                } else {
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