layui.use(['form', 'layer', 'table'], function () {
    var table = layui.table;
    var layer = layui.layer;
    var dataId;
    var data;
    table.render({
        elem: "#typeList",
        url: config.back + "/data/getData",
        // where : {
        // 	method : "getData"
        // },
        method: "post",
        page: true,
        cols: [[{
            type: "checkbox",
            fixed: "left",
            width: 50,
        }, {
            field: "name",
            title: "字段名称"
        }, {
            field: "remark",
            title: "描述"
        },
            {
                field: "dataId",
                title: "id",
                hide: true
            },
            {
                field: "fixed",
                title: "操作",
                toolbar: "#barDemo"
            }

        ]],
        done: function (res, curr, count) {
            $("#typeList").next().find(".layui-table-body").find("table")
                .find("tbody").children("tr").on("click", function () {
                var index = $(this).attr("data-index")
                dataId = res.data[index].dataId
                table.reload('test', {
                    url: config.back + "/dataItem/getDataItem",
                    where: {
                        dataId: dataId
                    }, //设定异步数据接口的额外参数
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            })
        }
    })


    table.render({
        elem: "#test",
        data: [],
        method: "post",
        page: true,
        cols: [[{
            type: "checkbox",
            fixed: "left",
            width: 50
        }, {
            field: "name",
            title: "字段编号"
        }, {
            field: "value",
            title: "字段值"
        }, {
            field: "fixed",
            title: "操作",
            toolbar: "#barDemo"
        },

        ]],

    })

    $("#select").click(function () {
        var name = $("#dictionaryName_query_type").val()
        table.reload('typeList', {
            url: config.back + "/data/getData",
            where: {
                name: name
            }, //设定异步数据接口的额外参数
            page: {
                curr: 1 //重新从第 1 页开始
            }
        });

    })

    $("#add").click(function () {
        var index = layui.layer.open({
            type: 2,
            title: '新增',
            maxmin: true, //开启最大化最小化按钮
            area: ['400px', '320px'],
            content: '/html/back/data_manage/add.html',
        });

    })

    //工具条事件
    table.on('tool(type)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        data = obj.data; //获得当前行数据

        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
//	  var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if (layEvent === 'edit') { //编辑
            //iframe 层
            var index = layui.layer.open({
                type: 2,
                title: '编辑',
                maxmin: true, //开启最大化最小化按钮
                area: ['400px', '320px'],
                content: '/html/back/data_manage/edit.html',
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index)
//				  // 根据formId获取form表单数据
//				  var formdata = body.find("#fromId").serializeArray();

                    var iframeWin = layero.find('iframe')[0].contentWindow
                    if (data) {
                        body.find("#dataId").val(data.dataId)
                        body.find("#name").val(data.name)
                        body.find("#remark").val(data.remark)
                        iframeWin.layui.form.render()
                    }
                }
            });
        } else if (layEvent === 'del') { //删除
            layer.confirm("确定删除此字典？", function (index) {
                $.ajax({
                    url: config.back + "/data/delData",
                    data: {
                        dataId: data.dataId
                    },
                    type: "post",
                    dataType: "json",
                    success: function (res) {
                        layer.close(index);
                        if (res.code == 200) {

                            layer.msg(res.message, {icon: 1, time: 1000}, function () {
                                table.reload('typeList');
                                table.reload('test');
                            })

                        } else if (res.code == 300) {
                            layer.msg(res.message, {icon: 2, time: 1000})
                        }
                    },
                    error: function () {
                        layer.msg("网络错误,请稍后重试")
                    }
                })

            })

        }
    })

    $("#allDel").click(function () {
        var checkStatus = table.checkStatus('typeList'); //即为基础参数 id 对应的值


        console.log(checkStatus.data) //获取选中行的数据
        console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
        console.log(checkStatus.isAll) //表格是否全选
    })


    // function deleteDataById(dataId){
    //  $.ajax({
    //     	url:config.back + config.dataPath,
    //     	data:{
    //     		dataId:dataId,method:"delDataDtoById"
    //     	},
    //     	type:"post",
    //     	dataType:"json",
    //     	success:function(res){
    //     		if(res.code==200){
    //     			layer.msg(res.message)
    //     		}else if(res.code == 300){
    //     			layer.msg(res.message)
    //     		}
    //     	},
    //     	error:function(){
    //     		layer.msg("网络错误,请稍后重试")
    //     	}
    //     })
    //     table.reload('typeList', {
    // 		  url: config.back + config.dataPath,
    // 		  where: {
    // 			  method:"getData"
    // 		  }, //设定异步数据接口的额外参数
    // 		  page: {
    // 		    curr: 1 //重新从第 1 页开始
    // 		  }
    // 		});
    //     table.reload('test', {
    // 		  url: config.back + config.dataPath,
    // 		  where: {
    // 			  method:"getDataItemById"
    // 		  }, //设定异步数据接口的额外参数
    // 		  page: {
    // 		    curr: 1 //重新从第 1 页开始
    // 		  }
    // 		});
    // }
    //

})
