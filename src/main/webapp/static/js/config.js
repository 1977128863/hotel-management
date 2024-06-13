var config = {
    back: "/back",
    loginPath: "/loginUser",

}

function getDataByKey(key, value) {
    //默认异步
    var load = layer.load(1);
    var list = new Array();
    $.ajax({
        url: config.back + "/data/getDataForApply",
        data: {
            key: key, value: value
        },
        type: "post",
        dataType: "json",
        async: false,
        success: function (res) {
            layer.close(load);
            if (res.code == 200) {
                list = res.data
            } else if (res.code == 300) {
                layer.msg(res.message)
            }
        },
        error: function () {
            layer.msg("网络异常！")
        }
    })
    return list;
}

function getRoleByKey(roleId) {
    //默认异步
    var load = layer.load(1);
    var list = new Array();
    $.ajax({
        url: config.back + "/role/getLevelForApply",
        data: {
            roleId: roleId
        },
        type: "post",
        dataType: "json",
        async: false,
        success: function (res) {
            layer.close(load);
            if (res.code == 200) {
                list = res.data;
            } else if (res.code == 300) {
                layer.msg(res.message)
            }
        },
        error: function () {
            layer.msg("网络异常！")
        }
    })
    return list;
}

function getClassifyByKey() {
    //默认异步
    var load = layer.load(1);
    var list = new Array();
    $.ajax({
        url: config.back + "/roomClassify/getAllClassify",
        // data: {
        // 	roleId: roleId
        // },
        type: "post",
        dataType: "json",
        async: false,
        success: function (res) {
            layer.close(load);
            if (res.code == 200) {
                list = res.data;
            } else if (res.code == 300) {
                layer.msg(res.message)
            }
        },
        error: function () {
            layer.msg("网络异常！")
        }
    })
    return list;
}

function getMemberByKey(memberId) {
    //默认异步
    var load = layer.load(1);
    var str;
    $.ajax({
        url: config.back + "/member/getMemberForApply",
        data: {
            memberId: memberId
        },
        type: "post",
        dataType: "json",
        async: false,
        success: function (res) {
            layer.close(load);
            if (res.code == 200) {
                str = res.str;
            } else if (res.code == 300) {
                layer.msg(res.message)
            }
        },
        error: function () {
            layer.msg("网络异常！")
        }
    })
    return str;
}