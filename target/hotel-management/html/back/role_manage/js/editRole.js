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
        onCheck: onCheck

    }
};


layui.use(['form', 'layer'], function () {
    var name = GetUrlParam("name");
    var form = layui.form;
    var layer = layui.layer
    //获取数据，进行数据回填
    var load = layer.load(2);
    $.ajax({
        url: config.back + "/role/getRole",
        async: false,
        data: {
            name: name
        },
        type: "post",
        dataType: "json",
        success: function (res) {
            layer.close(load);
            if (res.code == 0) {
                layer.close(load);
                var admin = res.data;
                //数据回填   editForm双引号中的是filter
                form.val("editForm", admin[0])
            } else if (res.code == -1) {
                layer.close(load);
                lay.msg(res.message);
            }
        }
    })

    var zNode = new Array();
    var ids = new Array()
//此数据应从菜单角色关联表查，现在先拿这个做菜单回显用
    $.ajax({
        url: config.back + "/role/getHaveMenu",
        async:false,
        type: "post",
        success: function (res) {
            if (res.code == 200) {
                ids = res.data
            }
        }
    })

    var roleName = $("#roleName").val()

    $.ajax({
        url: config.back + "/html/getManagementByRoleId",
        type: 'post',
        data: {
            roleName: roleName
        },
        async: false,
        dataType: 'json',
        success: function (res) {
            if (res.code == 200) {
                var data = res.data;
                $.each(ids, function (index, item) {
                    if (data.length <= 0){
                        zNode[index]= { id:item.menuId, pId:item.topMenuId, name:item.title,icon:'<i class="layui-icon" data-icon="'+item.icon+'"> </i>', open:true,checked:false};
                    }
                    for (var i = 0; i < data.length; i++){
                        if (item.menuId == data[i].menuId){
                            zNode[index]= { id:item.menuId, pId:item.topMenuId, name:item.title,icon:'<i class="layui-icon" data-icon="'+item.icon+'"> </i>', open:true,checked:true};//item.spread.value
                            break;
                        }else {
                            zNode[index]= { id:item.menuId, pId:item.topMenuId, name:item.title,icon:'<i class="layui-icon" data-icon="'+item.icon+'"> </i>', open:true,checked:false};//item.spread.value

                        }
                    }

                });

                $(document).ready(function () {
                    $.fn.zTree.init($("#treeMultiple"), setting, zNode);

                });
            } else {
                parent.layer.closeAll("iframe");
                top.layer.msg(res.message);
            }
        }
    });




//  //提交
    form.on("submit(edit)", function (obj) {
        //vo
        var sysRoleDto = {
            menuIds : onCheck(),//JSON.stringify(onCheck()),
            roleId : obj.field.roleId
        }
        console.log(sysRoleDto)
        $.ajax({
            url: config.back + "/role/editRole/updateRoleMenu",
            async:false,
            data: sysRoleDto,//JSON.stringify(sysRoleDto),
            dataType: "json",
            // contentType: "application/json",
            type: "post",
            success: function (res) {
                if (res.code == 200) {

                } else if (res.code == 300) {
                    layer.msg(res.message, {icon: 2, time: 1000});
                }
            }
        })

        console.log(obj.field)
        var load = layer.load(2);//必须写   加载框 不然可以一直提交
        $.ajax({
            url: config.back + "/role/editRole",
            data: obj.field,
            async:false,
            dataType: "json",
            type: "post",
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
                    layer.close(load);
                    layer.msg(res.message, {icon: 2, time: 1000});
                }
            }
        })

        return false;
    })





})



function onCheck() {
    var treeObj = $.fn.zTree.getZTreeObj("treeMultiple");
    nodes = treeObj.getCheckedNodes(true);
    var into = [];
    for (var i = 0; i < nodes.length; i++) {
        into.push(nodes[i].id);
    }
    return into;
}


//根据参数名获取地址栏参数
function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");
    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;
        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");
            if (arr != null && arr[0] == paraName) {
                return decodeURI(arr[1]);
            }
        }
        return "";
    } else {
        return "";
    }

}

