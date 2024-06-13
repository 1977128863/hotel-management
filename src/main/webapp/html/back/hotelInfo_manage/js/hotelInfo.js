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
        url: config.back + "/roomInfo/getRoomInfo",
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
                field: "roomCode", title: "房间编号"
            },
            {
                field: "remark", title: "描述", width: 100
            },
            {
                field: "roomTitle", title: "房间标题"
            },
            {
                field: "roomSize", title: "房间大小 / m²"
            },
            {
                field: "classifyId", title: "房间类型", templet: function (data) {
                    var str = getclassify(data.classifyId)
                    return str
                }
            },
            {
                field: "roomStatus", title: "房间状态", templet: function (data) {
                    var str = getDataByKey("roomStatus", data.roomStatus)
                    return str
                }
            },
            {
                field: "roomImg1", title: "房间图片1", templet: function (data) {

                    var filename = getImg(data.roomImg1);

                    return "<div><img src='/fileUpload/" + filename + "' width='800px',height='500px'></div>";


                }
            },
            {
                field: "createPer", title: "创建者"
            },
            {
                field: "createTime", title: "创建时间"
            },
            {
                field: "modifyPer", title: "修改人"
            },
            {
                field: "modifyTime", title: "修改时间"
            },
            {
                title: "操作", toolbar: "#tool"
            }
        ]]

    })


    $("#select").click(function () {
        var roomCode = $("#roomCode").val()

        table.reload("list", {
            url: config.back + "/roomInfo/getRoomInfo",
            where: {roomCode: roomCode},
            page: {curr: 1}
        })
        return false;
    })


    $("#add").click(function () {
        layer.open({
            type: 2,
            content: "html/back/hotelInfo_manage/addHotelInfo.html",
            area: ["1180px", "580px"],
            maxmin: true,
            title: "新增房间"
        })
    })

    table.on("tool(list)", function (obj) {
        if (obj.event == "edit") {
            layer.open({
                type: 2,
                content: "html/back/hotelInfo_manage/editHotelInfo.html?roomId=" + obj.data.roomId,
                area: ["1180px", "580px"],
                maxmin: true,
                title: "编辑房间信息"
            })
        } else if (obj.event == "del") {
            var id = obj.data.roomId;
            // console.log(obj.data)
            //提示语-->是否 删除
            //询问框
            layer.confirm("是否删除该房间！", {
                title: "删除",
                btn: ['是', '否'] //按钮
            }, function () {
                var load = layer.load(2);
                $.ajax({
                    url: config.back + "/roomInfo/delRoomInfo",
                    data: {
                        roomId: id
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

function getclassify(classifyId) {
    var classifyName;
    $.ajax({
        url: config.back + "/roomClassify/getRoomClassify",
        type: "post",
        async: false,
        data: {classifyId: classifyId},
        success: function (res) {
            if (res.code == 0) {
                classifyName = res.data[0].classifyName
            }
        }
    })
    return classifyName
}

function getImg(roomImg1) {
    var fileName;
    $.ajax({
        url: config.back + "/file/getFile",
        type: "post",
        data: {
            fileCode: roomImg1
        },
        async: false,
        dataType: "json",
        success: function (res) {
            if (res.code == 200) {
                fileName = res.data[0].fileName;
            } else if (res.code == 300) {
                return "图片丢失"
            }
        }
    });

    return fileName;
}