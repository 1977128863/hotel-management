var menuIds = new Array();
var setting = {
    check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: {"Y": "ps", "N": "ps"} //父子联动
        // chkboxType: { "Y": "", "N": "" }
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeCheck: true,
        onCheck:
            function () {
                var treeObj = $.fn.zTree.getZTreeObj("treeMultiple");
                let nodes = treeObj.getCheckedNodes(true);
                menuIds = []
                // for (let i = 0; i < nodes.length;i++){
                // 	let node = treeObj.getNodeByParam("id",nodes[i])//根据ID找到该节点
                // 	if (node != null){
                // 		node.checked = true;
                // 		// treeObj.checkNode(node, true,true);//根据该节点选中
                // 		// treeObj.updateNode(node);//更新
                // 	}
                // }
                for (var i = 0; i < nodes.length; i++) {
                    menuIds.push(nodes[i].id);
                }
                return menuIds
            }

    }


};
var zNode = new Array();

$.ajax({
    url: config.back + "/html/getManagementByRoleId",
    type: 'post',
    async: false,
    dataType: 'json',
    success: function (res) {
        if (res.code == 200) {
            $.each(res.data, function (index, item) {
                zNode[index] = {
                    id: item.menuId,
                    pId: item.topMenuId,
                    name: item.title,
                    icon: '<i class="layui-icon" data-icon="' + item.icon + '"> </i>',
                    open: true
                };//item.spread.value
            });
        } else {
            parent.layer.closeAll("iframe");
            top.layer.msg(res.message);
        }
    }
});

$(document).ready(function () {
    $.fn.zTree.init($("#treeMultiple"), setting, zNode);
});

// function onCheck()
// {
// 	var treeObj = $.fn.zTree.getZTreeObj("treeMultiple");
// 	let nodes = treeObj.getCheckedNodes(true);
// 	menuIds = nodes;
//
// 	// $.each(nodes,function (index,item) {
// 	// 	let node = treeObj.getNodeByParam("id",item)//根据ID找到该节点
// 	// 	if (node != null){
// 	// 		treeObj.checkNode(node, true, false);//根据该节点选中
// 	// 	}
// 	// })
// 	for (let i = 0; i < nodes.length;i++){
// 		let node = treeObj.getNodeByParam("id",nodes[i])//根据ID找到该节点
// 		if (node != null){
// 			treeObj.checkNode(node, true, false);//根据该节点选中
// 		}
// 	}
// 	console.log(menuIds)
// 	return menuIds
// }


layui.use(['form', 'layer'], function () {
    //提交
    var form = layui.form;
    var layer = layui.layer
    form.on("submit(add)", function (obj) {
        $.ajax({
            url: config.back + "/role/addRole",
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

