layui.use(['jquery', 'form', 'laydate'], function () {
    var form = layui.form;
    var $ = layui.$;
    var layer = layui.layer;

    var list = getClassifyByKey()
    for (let i = 0; i < list.length; i++) {
        $("#classifyId").append("<option value='" + list[i].classifyId + "' >" + list[i].classifyName + "</option>")
    }
    form.render()
    // var options = {
    //     el: '#demo1',
    //     name: 'xmselectName',//表单的name属性
    //     layVerify: 'required',//必填项
    //     //layVerType: 'tips',//提示类型 同layui
    //     // tips: '请选择商品标签',
    //     toolbar: {//工具条,全选,清空,反选,自定义
    //         show: true,
    //         list: [
    //             'ALL',
    //             'CLEAR',
    //             'REVERSE',
    //             {
    //                 name: '自定义',
    //                 icon: 'el-icon-star-off',
    //                 method(data) {
    //                     alert('我是自定义的');
    //                 }
    //             }
    //         ]
    //     },
    //     data: [],
    //     //initValue: ['shuiguo','shucai'],//默认初始化,也可以数据中selected属性
    //     //language: 'zn',//语言包
    //     //filterable: true,//搜索功能
    //     //autoRow: true,//选项过多,自动换行
    //     // repeat: true,//是否支持重复选择
    //     //max: 2,//最多选择2个
    //     // template({ item, sels, name, value }){
    //     //    //template:自定义下拉框的模板
    //     //     return item.name  + '<span style="position: absolute; right: 10px; color: #8799a3">'+value+'</span>'
    //     // },
    // };
    //标签
    // var demo1 = xmSelect.render(options)
    // setTimeout(function () {
    //     //假设data是ajax 异步获取的
    //     var data = getLabel();
    //     //模拟通过ajax 获取json数据,异步更新多选下拉框的值
    //     demo1.update({data: data})
    // }, 100);

    //大小类
    // getClassify(0);
    // form.on("select(classify)", function (obj) {
    //     getSubClassify(obj.value)
    // })

    form.on("submit(add)", function (obj) {
        var index = parent.layer.getFrameIndex(window.name);
        var loading = top.layer.msg('数据提交中，请稍后', {
            icon: 16,
            time: false,
            shape: 0.3
        });
        var roomTitle = $("#roomTitle").val();
        var roomSize = $("#roomSize").val();
        var classifyId = $("#classifyId").val();
        var roomStatus = $("#roomStatus").val();
        var integral = $("#integral").val();
        // var str = obj.field.xmselectName.split(",");
        // console.log(obj.field.xmselectName)
        // var label = [];
        // for (var i = 0; i < str.length; i++) {
        //     label.push(str[i])
        // }
        var remark = $("#remark").val();
        var roomImg1 = $("#oneImg").val();
        var roomCode = $("#roomCode").val();
        $.ajax({
            url: config.back + "/roomInfo/addRoomInfo",
            data: {
                roomTitle: roomTitle,
                roomSize: roomSize,
                classifyId: classifyId,
                // label: JSON.stringify(label),
                roomStatus: roomStatus,
                integral: integral,
                remark: remark,
                roomImg1: roomImg1,
                roomCode: roomCode
            },
            type: "post",
            dataType: "json",
            success: function (res) {
                top.layer.close(loading);
                if (res.code == 200) {
                    parent.layer.msg(res.message, {
                        icon: 1,
                        time: 2000
                    }, function () {
                        parent.layer.close(index);
                        parent.fresh()
                    });
                } else {
                    parent.layer.msg(res.message, {
                        icon: 2,
                        time: 2000
                    }, function () {
                        location.reload();
                    });
                }
            },
            error: function () {
                top.layer.close(loading);
                parent.layer.msg('网络错误，请稍后再试，或联系管理员', {
                    icon: 2,
                    time: 2000
                }, function () {
                    parent.layer.close(index);
                });
            }
        })
        return false;
    })

    // var demo1 = xmSelect.render(options);
    // setTimeout(function () {
    //     var data = getLabel();
    //     demo1.update({data: data})
    // }, 100)
})

// function getClassify(topClassifyId) {
//     $.ajax({
//         url: config.backPath + config.classifyPath,
//         type: "post",
//         data: {
//             topClassifyId: topClassifyId, method: "getShopClassifyByCondition"
//         },
//         dataType: "json",
//         async: false,
//         success: function (data) {
//             var str = ` <option value="">请选择商品大类</option>`;
//             $.each(data.data, function (index, item) {
//                 str += ` <option value="` + item.id + `">` + item.name + `</option>`;
//             });
//             $("#classify").html(str)
//             layui.form.render();
//         },
//         error: function () {
//             layer.msg("网络错误，请稍后再试，或联系管理员！", {icon: 2, time: 1000});
//         }
//     });
//
// }
//
// function getSubClassify(topClassifyId) {
//     $.ajax({
//         url: config.backPath + config.classifyPath,
//         type: "post",
//         data: {
//             topClassifyId: topClassifyId, method: "getShopClassifyByCondition"
//         },
//         dataType: "json",
//         async: false,
//         success: function (data) {
//             var str = ` <option value="">请选择商品小类</option>`;
//             $.each(data.data, function (index, item) {
//                 str += ` <option value="` + item.id + `">` + item.name + `</option>`;
//             });
//             $("#subClassify").html(str)
//             layui.form.render();
//         },
//         error: function () {
//             layer.msg("网络错误，请稍后再试，或联系管理员！", {icon: 2, time: 1000});
//         }
//     });
//
// }


function selectImg() {
    var formData = new FormData();
    formData.append("file", $("#chooseImgOne")[0].files[0])
    $.ajax({
        url: config.back + "/uploadFile",
        type: "post",
        data: formData,
        processData: false,
        contentType: false,
        async: false,
        success: function (data) {
            // console.log(data.fileName)
            if (data.code == 200) {
                $("#roomImg1").attr("src", "/fileUpload/" + data.fileName);
                $("#oneImg").val(data.imgCode);
                layer.msg(data.message, {icon: 1, time: 1000})
            } else {
                layer.msg(data.message, {icon: 2, time: 1000})
            }
        },
        error: function (data) {
            layer.msg("上传失败", {icon: 2, time: 1000})
        }
    })

}

function imgClickOne() {
    $("#chooseImgOne").click();
}
