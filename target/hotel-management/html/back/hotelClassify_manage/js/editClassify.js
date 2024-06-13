var classifyId;
var setting = {
    check: {
        enable: true,
        chkStyle: "radio",
        // chkboxType: { "Y": "ps", "N": "ps" } 父子联动
        chkboxType: {"Y": "", "N": ""}
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeCheck: true,
        onCheck: function () {
            var treeObj = $.fn.zTree.getZTreeObj("treeMultiple");
            let nodes = treeObj.getCheckedNodes(true);
            classifyId = nodes[0].id;
            return classifyId
        }

    }
};


var ids = new Array()

layui.use(['form', 'layer'], function () {
    var classifyName = GetUrlParam("classifyName");
    var form = layui.form;
    var layer = layui.layer
    //获取数据，进行数据回填
    var load = layer.load(2);
    $.ajax({
        url: config.back + "/roomClassify/getRoomClassify",
        data: {
            classifyName: classifyName
        },
        async: false,
        type: "post",
        dataType: "json",
        success: function (res) {
            layer.close(load);
            if (res.code == 0) {
                layer.close(load);
                var classify = res.data;
                ids = res.data
                //数据回填   editForm双引号中的是filter
                form.val("editForm", classify[0])
            } else if (res.code == -1) {
                layer.close(load);
                lay.msg(res.message);
            }
        }
    })

    //  //提交
    form.on("submit(save)", function (obj) {
        //         var checkedList = $('input[type=checkbox]');
        // $.each(checkedList,function(index,item){
        //     console.log(item)
        // })
        console.log(obj.field)
        obj.field.topClassifyId = classifyId
        var load = layer.load(2);//必须写   加载框 不然可以一直提交
        $.ajax({
            url: config.back + "//roomClassify/editRoomClassify",
            data: obj.field,
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


var zNode = new Array();
$.ajax({
    url: config.back + "/roomClassify/getRoomClassify",
    type: 'post',
    async: false,
    dataType: 'json',
    success: function (res) {
        if (res.code == 0) {
            $.each(res.data, function (index, item) {
                for (var i = 0; i < ids.length; i++) {

                    if (item.classifyId == ids[i].classifyId) {
                        zNode[index] = {
                            id: item.classifyId,
                            pId: item.topClassifyId,
                            name: item.classifyName,
                            icon: '<i class="layui-icon" data-icon="' + item.icon + '"> </i>',
                            open: true,
                            checked: true
                        };
                    } else {
                        zNode[index] = {
                            id: item.classifyId,
                            pId: item.topClassifyId,
                            name: item.classifyName,
                            icon: '<i class="layui-icon" data-icon="' + item.icon + '"> </i>',
                            open: true
                        };//item.spread.value

                    }

                }

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

// function zTreeOnCheck(treeNode) {
//     var treeObj = $.fn.zTree.getZTreeObj("treeMultiple");
//     if (treeNode.checked) { //注意，这里的树节点的checked状态表示勾选之后的状态
//         treeObj.checkAllNodes(false);//取消所有节点的选中状态
//         treeObj.checkNode(treeNode, true, false, false);
//         // 重新选中被勾选的节点
//     }
// }

// function onCheck()
// {
//     var treeObj = $.fn.zTree.getZTreeObj("treeMultiple");
//     let nodes = treeObj.getCheckedNodes(true);
//     // if (node.checked){
//     //     treeObj.checkAllNodes(false);//取消所有节点的选中状态
//     //     treeObj.checkNode(node, true, false, false);
//     // }
//     // console.log(node)
//     return nodes[0].id
//     // var into = [];
//     // for(var i=0;i<nodes.length;i++)
//     // {
//     //     into.push(nodes[i].id);
//     // }
//     // return into;
// }


// function getCheckBoxValueTwo() {
//     //获取checkBox的元素
//     var ids = $('input[type=checkbox]');
//     ids.each(function () {
//         //获取当前元素的勾选状态
//         if ($(this).prop("checked")) {
//             console.log($(this))
//         }
//     });
// }

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