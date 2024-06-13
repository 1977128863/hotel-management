layui.use(['form', 'layer'], function () {
    //提交
    var form = layui.form;
    var layer = layui.layer


    var list = getClassifyByKey()
    for (let i = 0; i < list.length; i++) {
        $("#topClassifyId").append("<option value='" + list[i].classifyId + "' >" + list[i].classifyName + "</option>")
    }
    form.render()


    form.on("submit(add)", function (obj) {
        $.ajax({
            url: config.back + "/roomClassify/addRoomClassify",
            data: obj.field,
            type: "post",
            dataType: "json",
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
                    layer.msg(res.message, {icon: 2, time: 1000})
                } else if (res.code == 500) {
                    layer.msg(res.message, {icon: 2, time: 1000})
                }
            },
            error: function () {
                layer.msg("网络错误，请稍后重试", {icon: 2, time: 1000})
            }
        })

        return false;
    })
})
