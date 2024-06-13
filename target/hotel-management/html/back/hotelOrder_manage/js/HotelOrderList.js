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
        url: config.back + "/order/getHotelOrder",
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
                field: "userId", title: "入住人", width: 100
            },
            {
                field: "roomCode", title: "房间编号"
            },

            {
                field: "roomPrice", title: "金额 / 元"
            },
            {
                field: "roomSize", title: "房间大小 / m²"
            },
            {
                field: "createTime", title: "创建时间"
            },
            {
                field: "orderStatus", title: "订单状态", width: 100, templet: function (data) {
                    var str = getDataByKey("orderStatus", data.orderStatus)
                    return str
                }
            },
            {
                field: "userPhone", title: "用户手机"
            },
            {
                field: "contacts", title: "联系人", width: 100
            },
            {
                field: "idCard", title: "身份证", templet: function (data) {
                    var str = data.idCard.replace(/^(.{6})(?:\d+)(.{2})$/, "$1**********$2")
                    return str
                }
            },
            {
                title: "操作", toolbar: "#tool"
            }
        ]]

    })

    $("#select").click(function () {
        var idCard = $("#idCard").val()

        table.reload("list", {
            url: config.back + "/order/getHotelOrder",
            where: {idCard: idCard},
            page: {curr: 1}
        })
        return false;
    })


    $("#add").click(function () {
        layer.open({
            type: 2,
            content: "html/back/hotelOrder_manage/addOrder.html",
            area: ["500px", "540px"],
            maxmin: true,
            title: "添加订单"
        })
    })

    table.on("tool(list)", function (obj) {
        if (obj.event == "edit") {
            layer.open({
                type: 2,
                content: "html/back/hotelOrder_manage/editOrder.html?idCard=" + obj.data.idCard,
                area: ["500px", "540px"],
                maxmin: true,
                title: "编辑订单信息"
            })
        } else if (obj.event == "del") {
            var id = obj.data.orderId;
            // console.log(obj.data)
            //提示语-->是否 删除
            //询问框
            layer.confirm("是否删除该订单！", {
                title: "删除",
                btn: ['是', '否'] //按钮
            }, function () {
                var load = layer.load(2);
                $.ajax({
                    url: config.back + "/order/delOrder",
                    data: {
                        orderId: id
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