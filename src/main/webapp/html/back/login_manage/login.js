layui.use(['form', 'layer'], function () {
    var form = layui.form;

    form.on("submit(login)", function (obj) {
        var load = layer.load(2);
        $.ajax({
            url: config.back + "/admin/loginUser",
            type: "post",
            data: obj.field,
            dataType: "json",
            success: function (res) {
                if (res.code == 200) {
                    layer.close(load);
                    layer.msg(res.message, {
                        icon: 1, time: 500
                    }, function () {
                        location.href = "/html/back/index.html"
                    })

                } else if (res.code == 300) {
                    layer.close(load);
                    layer.msg(res.message, {icon: 2, time: 500})
                }
            },
            error: function () {
                layer.msg("网络错误，请重试", {icon: 2, time: 500})
            }

        })

        return false;


    })
})

