//增加父节点
layui.use(['form', 'layer'], function () {
    form = layui.form,
        layer = layui.layer,
        $ = layui.$;
});


layui.use(['form', 'laypage', 'layer', 'layedit', 'laydate'], function () {
    var form = layui.form;
    // var layer = layui.layer;
//	  //监听指定开关
//	  form.on('switch(isLock)', function(data) {
//		  switchValue= this.checked ? "1" : "0";
//	  });

    //监听提交
    form.on('submit(Save)', function (data) {
        data.field.topMenuId = 0
        $.ajax({
            url: config.back + "/menu/addMenu",
            type: "post",
            dataType: "json",
            data: data.field,
            success: function (res) {
                if (res.code == 200) {
                    layer.msg(res.message, {icon: 1, time: 1000})
                    parent.zNodes2();
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
    });
});

//验证
layui.use(['form', 'layer'], function () {
    // var form = layui.form;
    // var layer = layui.layer;

    form.verify({
        orderCheck: function (value) {
            var flag = false;
            var msg = "";
            var load = layer.load(2);
            $.ajax({
                url: config.back + "/menu/orderCheck",
                data: {
                    orders: value,
                    topMenuId: 0
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
})


