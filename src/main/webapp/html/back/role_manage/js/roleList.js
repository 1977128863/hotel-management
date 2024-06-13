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
        url: config.back + "/role/getRole",
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
                field: "name", title: "角色"
            },
            // {
            // 	field:"level",title:"等级"
            // },
            {
                field: "createPer", title: "创建者"
            },
            {
                field: "createTime", title: "创建时间"
            },
            {
                field: "modifyPer", title: "上次修改人", width: 100
            },
            {
                field: "modifyTime", title: "上次修改时间"
            },
            {
                title: "操作", toolbar: "#tool"
            }
        ]]

    })

    $("#select").click(function () {
        var name = $("#name").val()
        // $.ajax({
        // 	url:config.back + "/getAdmin",
        // 	type:"post",
        // 	dataType:"json",
        // 	data:{adminName:adminName},
        // 	success:function (res) {
        // 		if (res.code == 0){
        // 			fresh()
        // 		}else if(res.code == -1){
        // 			layer.msg(res.message,{icon:2,time:1000})
        // 		}
        // 	},
        // 	error:function () {
        // 			layer.msg("网络异常，请稍后重试")
        // 	}
        // })
        table.reload("list", {
            url: config.back + "/role/getRole",
            where: {name: name},
            page: {curr: 1}
        })
        return false;
    })


    $("#add").click(function () {
        layer.open({
            type: 2,
            content: "html/back/role_manage/addRole.html",
            area: ["680px", "600px"],
            maxmin: true,
            title: "添加管理员"
        })
    })

    table.on("tool(list)", function (obj) {
        if (obj.event == "edit") {
            layer.open({
                type: 2,
                content: "html/back/role_manage/editRole.html?name=" + obj.data.name,
                area: ["680px", "600px"],
                maxmin: true,
                title: "编辑管理员"
            })
        } else if (obj.event == "del") {
            var id = obj.data.roleId;
            // console.log(obj.data)
            //提示语-->是否 删除
            //询问框
            layer.confirm('是否删除【' + obj.data.name + "】角色！", {
                title: "删除",
                btn: ['是', '否'] //按钮
            }, function () {
                var load = layer.load(2);
                $.ajax({
                    url: config.back + "/role/delRole",
                    data: {
                        roleId: id
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


function freshTable() {
    // var gederlist = getDataByKey("gender");
    // var genderItemList;
    // var islocklist = getDataByKey("islock");
    // var islockItemList;
    // var roleidlist = getDataByKey("role_id");
    // var roleidItemList;
    // $.ajax({
    // 	url: config.back + config.dataPath,
    // 	data: {
    // 		dataId: gederlist.dataId,method:"getDataItemById"
    // 	},
    // 	async: false,
    // 	success: function (res) {
    // 		if (res.code == 0) {
    // 			genderItemList = res.data;
    // 		} else if (res.code == -1) {
    // 			layer.msg(res.msg)
    // 		}
    // 	},
    // 	error: function (res) {
    // 		layer.msg("网络异常！")
    // 	}
    // })
    // 	$.ajax({
    // 	url: config.back + config.dataPath,
    // 	data: {
    // 		dataId: islocklist.dataId,method:"getDataItemById"
    // 	},
    // 	async: false,
    // 	success: function (res) {
    // 		if (res.code == 0) {
    // 			islockItemList = res.data;
    // 		} else if (res.code == -1) {
    // 			layer.msg(res.msg)
    // 		}
    // 	},
    // 	error: function (res) {
    // 		layer.msg("网络异常！")
    // 	}
    // })
    // $.ajax({
    // 	url: config.back + config.dataPath,
    // 	data: {
    // 		dataId: roleidlist.dataId,method:"getDataItemById"
    // 	},
    // 	async: false,
    // 	success: function (res) {
    // 		if (res.code == 0) {
    // 			roleidItemList = res.data;
    // 		} else if (res.code == -1) {
    // 			layer.msg(res.msg)
    // 		}
    // 	},
    // 	error: function (res) {
    // 		layer.msg("网络异常！")
    // 	}
    // })
    var table = layui.table
    var layer = layui.layer
    var form = layui.form
    table.render({
        elem: "#list",
        url: config.back + "/getAdmin",
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
        escape: true, //XSS过滤
        page: true, //分页  2个参数 limit 一页几个     page 第几页
        limit: 10,
        limits: [5, 10, 15, 20, 25, 30],
        cols: [[
            //后端传过来的key
            {field: "name", title: "姓名"},
            {
                field: "adminName", title: "用户名"
            },
            {
                field: "gender", title: "性别", width: 70, templet: function (d) {
                    if (!genderItemList) {
                        return "数据异常"
                    }
                    for (var i = 0; i < genderItemList.length; i++) {
                        if (genderItemList[i].key == d.gender) {
                            return genderItemList[i].value;
                        }
                    }
                    return "数据异常"
                }
            },
            {field: "phone", title: "联系方式"},
            {
                field: "islock", title: "管理员状态", width: 100, templet: function (d) {
                    if (!islockItemList) {
                        return "数据异常"
                    }
                    for (var i = 0; i < islockItemList.length; i++) {
                        if (islockItemList[i].key == d.islock) {
                            return islockItemList[i].value;

                        }
                    }
                    return "数据异常"
                }
            }, {
                field: "roleId", title: "角色", width: 100, templet: function (d) {
                    if (!roleidItemList) {
                        return "数据异常"
                    }
                    for (var i = 0; i < roleidItemList.length; i++) {
                        if (roleidItemList[i].key == d.roleId) {

                            return roleidItemList[i].value;
                        }
                    }
                    return "数据异常"
                }
            }, {
                field: "createAdminPer", title: "创建者"
            }, {
                field: "createTime", title: "创建时间"
            }, {
                field: "modifyAdminPer", title: "上次修改人", width: 100
            }, {
                field: "modifyTime", title: "上次修改时间"
            }, {
                field: "loginTime", title: "登录时间"
            }, {
                title: "操作", toolbar: "#tool"
            }
        ]]

    })
}
