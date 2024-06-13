/*
 * @Author: Lancer
 * @Date:2019/5/16
 * @Last Modified by:   Lancer
 * @Last Modified time: 2019/5/16
 */
var zNodes2;
$(function () {
//	<!--iframe适应大小-->
    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.body.scrollHeight;
            }
        }
    };
    setInterval(function () {
        setIframeHeight($('#if_2')[0]);
    }, 200)

    //当页面加载完成后，动态创建ztree菜单
    var setting2 = {
        data: {
            simpleData: {
                enable: true//启用简单json数据描述节点数据
            }
        },
        isSimpleData: true,
        //设置节点唯一标示名称
        treeNodeKey: "menuId",
        //设置节点的父节点名称
        treeNodeParentKey: "topMenuId",//pId
        //节点与节点之间的连线是否显示
        showLine: true,
        callback: {
            // 单击事件
            onClick: zTreeOnClick
        }
    };//设置ztree相关的属性
    //构造json数据
    //创建ztree
    zNodes2 = function zNodes2() {
        var menuList = getMenuList(null);
        ztreeObj = $.fn.zTree.init($("#left_nav"), setting2, menuList);
        ztreeObj.expandAll(true);
    }
    $(function () {
        zNodes2();
        //加载弹出层
        layui.use(['form', 'element'],
            function () {
                layer = layui.layer;
                element = layui.element;
            });
    });

    //节点跳转，修改节点网页
    //判断有没有下级，没有接收父节点	有是子节点
    function zTreeOnClick(event, treeId, treeNode) {
//    	var pNode = treeNode.getPath();//获取父节点
        var childrenNode = treeNode.children;
        debugger
        if (childrenNode == undefined || childrenNode.length < 1) {
            var title;
            var topMenuId;
            if (treeNode.getParentNode() == undefined || treeNode.getParentNode().length < 1) {
                title = "";
                topMenuId = "";
            } else {
                title = treeNode.getParentNode().name;
                topMenuId = treeNode.getParentNode().id;
            }
            document.querySelector('.right .right-inner iframe').src = '/html/back/menu_manage/html/edit_inner_manage_form.html?menuId=' + treeNode.id + '&title=' + title + '&topMenuId=' + topMenuId;
        } else {
            document.querySelector('.right .right-inner iframe').src = '/html/back/menu_manage/html/edit_label_manage_form.html?menuId=' + treeNode.id + '&title=' + treeNode.name;
        }
    }


});

//点击增加小类，判断有没有选中大类
function addSubNode() {
    var myArray = new Array();
    var AddzTreeObj = $.fn.zTree.getZTreeObj("left_nav");
    var selectedNodes = AddzTreeObj.getSelectedNodes();
    if (selectedNodes != null && selectedNodes != "") {
        myArray[0] = selectedNodes[0].id;
        myArray[1] = selectedNodes[0].name;
        var childrenNode = AddzTreeObj.transformToArray(selectedNodes);
//		var childrenNode=selectedNodes.children;
        //判断有没有字节点
        if (childrenNode == undefined || childrenNode.length <= 1) {//没有
            layer.msg("请选大类")
        } else {
            addherf(myArray);
        }
    } else {
        layer.msg("请先选大类");
    }
}

//增加小类跳转
function addherf(json) {
//	var Pid=AddSubnodeOnClick();
    if (json != "" && json != null && json != 0 && json != undefined) {
        document.getElementById('if_2').src = '/html/back/menu_manage/html/inner_manage_form.html?id=' + json[0] + "&name=" + json[1];
    }
}

//点击删除
function removeNodes() {
    var treeObj = $.fn.zTree.getZTreeObj("left_nav");
    //选中节点
    var nodes = treeObj.getSelectedNodes();
    var paramsArray = new Array();
    var pid;
    var flag = false;
    for (var i = 0, l = nodes.length; i < l; i++) {
        pid = nodes[i].id;

    }
    if (nodes[0].isParent) {
        paramsArray[0] = pid;
        var childNodes = nodes[0].children;
        for (var i = 0; i < childNodes.length; i++) {

        }
    }
    if (nodes[0].isParent) {
        var flag = true;
        var childNodes = nodes[0].children;
        if (flag) {
            Tips(
                '<i class="layui-icon layui-icon-tips" style="font-size: 30px; color:red;"></i>含有小类，如果删除会删除所有小类！',
                pid, nodes);
        }

    } else {
        Tips(
            '<i class="layui-icon layui-icon-tips" style="font-size: 30px; color:red;"></i>真的要删除吗？删除了不能恢复的！',
            pid, nodes);
    }
}

//提示窗
function Tips(tips, pid, nodes) {
    layer.open({
        type: 1
        , title: '警告'
        , content: '<div style="padding: 20px 100px;">' + tips + '</div>'
        , btn: ['确定', '取消']
        , btnAlign: 'c' //按钮居中
        , shade: 0 //不显示遮罩
        , yes: function () {
            layer.closeAll();
            layer.load();
            deleteNode(pid, nodes);
            layer.closeAll('loading');
        }
        , btn2: function () {
            layer.closeAll();
        }
    });
}

//删除节点方法
function deleteNode(pid, nodes) {
    var treeObj = $.fn.zTree.getZTreeObj("left_nav");
    $.ajax({
        url: config.back + "/menu/delMenu",
        type: "post",
        dataType: "json",
        data: {
            menuId: pid,
        },
        success: function (res) {
            if (res.code == 200) {
                layer.msg(res.message, {icon: 1, time: 1000}, function () {
                    for (var i = 0, l = nodes.length; i < l; i++) {
                        treeObj.removeNode(nodes[i])
                    }
                    zNodes2;
                    setTimeout(function () {
                        $("#if_2", document)[0].src = "";
                    }, 1000)
                })
            } else if (res.code == 300) {
                layer.msg(res.message, {icon: 1, time: 1000})
            }
        },
        error: function () {
            layer.msg(res.message, {icon: 1, time: 1000})
        }
    })
}


function getMenuList(topMenuId) {
    var menuList;

    $.ajax({
        url: config.back + "/menu/getMenuByTree",
        type: "post",
        data: {
            topMenuId: topMenuId
        },
        dataType: "json",
        async: false,
        success: function (data) {

            if (data.code == 200) {
                menuList = data.data;
            } else {
                layui.layer.msg(data.message, {icon: 2, time: 1000});
            }

        },
        error: function () {
            layui.layer.msg("网络错误，请稍后再试，或联系管理员！", {icon: 2, time: 1000});
        }
    });

    return menuList;

}
