function fresh() {
    var table = layui.table
    table.reload("list")
}

layui.use(['table', 'layer', 'form'], function () {
    // var table = layui.table
    // freshTable()
    var table = layui.table
    var layer = layui.layer
    var form = layui.form

    table.render({
        elem: "#list",
        url: config.back + "/member/getHotelMember",
        where: {
            // method:"getAdmin"
        },
        toolbar: true,  //开启工具栏
//		done:function(res,curr,count){
//			if(res.data.length == 0 && count > 0){
//				//显示上一页
//				table.reload("list",{
//					page:{
//						curr:curr - 1
//					}
//				})
//			}
//		},
        method: "post",
        escape: true, //XSS过滤
        page: true, //分页  2个参数 limit 一页几个     page 第几页
        limit: 10,
        limits: [5, 10, 15, 20, 25, 30],
        cols: [[
            //后端传过来的key
            {
                type: "checkbox",
                fixed: "left",
                width: 50,
            },
            {
                field: "memberName", title: "会员名字"
            },
            {
                field: "memberLevel", title: "会员等级"
            },

            {
                field: "experience", title: "会员经验"
            },
            {
                field: "discount", title: "优惠"
            },
            {
                title: "操作", toolbar: "#tool"
            }
        ]]

    })

    $("#select").click(function () {
        var roomClassify = $("#roomClassify").val()

        table.reload("list", {
            url: config.back + "/member/getHotelMember",
            where: {classifyName: roomClassify},
            page: {curr: 1}
        })
        return false;
    })


    $("#add").click(function () {
        layer.open({
            type: 2,
            content: "html/back/hotelClassify_manage/addClassify.html",
            area: ["480px", "540px"],
            maxmin: true,
            title: "添加房间类型"
        })
    })

    table.on("tool(list)", function (obj) {
        if (obj.event == "edit") {
            layer.open({
                type: 2,
                content: "html/back/hotelClassify_manage/editClassify.html?classifyName=" + obj.data.classifyName,
                area: ["780px", "480px"],
                maxmin: true,
                title: "编辑房间类型"
            })
        } else if (obj.event == "del") {
            var id = obj.data.classifyId;
            // console.log(obj.data)
            //提示语-->是否 删除
            //询问框
            layer.confirm('是否删除【' + obj.data.classifyName + "】！", {
                title: "删除",
                btn: ['是', '否'] //按钮
            }, function () {
                var load = layer.load(2);
                $.ajax({
                    url: config.back + "/roomClassify/delRoomClassify",
                    data: {
                        classifyId: id
                    },
                    dataType: "json",
                    type: "post",
                    success: function (res) {
                        if (res.code == 200) {
                            layer.close(load);
                            layer.msg(res.message, {icon: 1, time: 1000});
                            fresh();
                        } else if (res.code == 300) {
                            layer.close(load);
                            layer.msg(res.message, {icon: 2, time: 1000});

                        } else if (res.code == 500) {
                            layer.close(load);
                            layer.msg(res.message, {icon: 2, time: 1000});
                        }
                    }
                })
            })
        }
    })

})