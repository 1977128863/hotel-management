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
        url: config.back + "/html/getPowerHtml",
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
                field: "htmlHref", title: "页面路径"
            },
            {
                field: "title", title: "所属菜单"
            },
            {
                title: "操作", toolbar: "#tool"
            }
        ]]

    })

    $("#select").click(function () {
        var htmlHref = $("#htmlHref").val()
        table.reload("list", {
            url: config.back + "/html/getPowerHtml",
            where: {htmlHref: htmlHref},
            page: {curr: 1}
        })
        return false;
    })

    $("#import").click(function () {
        $.ajax({
            url: config.back + "/html/syncPowerHtml",
            type: "post",
            dataType: "json",
            success: function (res) {
                if (res.code == 200) {
                    layer.msg(res.message, {icon: 1, time: 1000})
                    fresh()
                } else if (res.code == 300) {
                    layer.msg(res.message, {icon: 2, time: 1000})
                } else if (res.code == 500) {
                    layer.msg(res.message, {icon: 2, time: 1000})
                }
            }
        })
        return false;
    })


    table.on("tool(list)", function (obj) {
        if (obj.event == "edit") {
            layer.open({
                type: 2,
                content: "html/back/html_manage/editHtml.html?htmlHref=" + obj.data.htmlHref,
                area: ["680px", "480px"],
                maxmin: true,
                title: "编辑html"
            })
        }
    })

})