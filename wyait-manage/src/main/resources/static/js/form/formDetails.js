/**
 * 用户列表
 */
var pageCurr;
$(function() {
    layui.use('table', function(){
        var table = layui.table
            ,form = layui.form;

        tableIns=table.render({
            elem: '#uesrList'
            ,url:'/form/getFormList?formFieldComboId='+$("#formFieldComboId").val()
            ,method: 'get' //默认：get请求
            ,cellMinWidth: 80
            ,page: true,
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'limit' //每页数据量的参数名，默认：limit
            },response:{
                statusName: 'code' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,countName: 'totals' //数据总数的字段名称，默认：count
                ,dataName: 'list' //数据列表的字段名称，默认：data
            }
            ,cols: [[
                {type:'numbers'}
                ,{field:'formFieldName', title:'name属性'}
                ,{field:'formFieldNameValue', title:'名称内容'}
                ,{field:'formFieldType', title: '属性类型',toolbar:'#jobTpl'}
                ,{field:'formFieldIsBasis', title: '数据类型',toolbar:'#jobTpl2'}
                ,{field:'formFieldContent', title: '内容', }
                ,{fixed:'right', title:'操作', width:240,align:'center', toolbar:'#optBar'}
            ]]
            ,  done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                //console.log(curr);
                //得到数据总量
                //console.log(count);
                console.log(res);
                pageCurr=curr;
            }
        });

        //监听在职操作
        form.on('switch(isJobTpl)', function(obj){
            //console.log(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
            var data = obj.data;
            setJobUser(obj,this.value,this.name,obj.elem.checked);
        });
        //监听工具条
        table.on('tool(userTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                delUser(data,data.formFieldId);
            } else if(obj.event === 'edit'){
                //编辑
                getUserAndRoles(data,data.formFieldId);
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data);
            } else if(obj.event == 'select'){
                // 查看
                window.location.href='/dooo/doooDetails?id='+data.doooId;
            } else if(obj.event == 'put'){
                // put(data);
                openUser3(data);
            }

        });
        //监听提交
        form.on('submit(userSubmit)', function(data){
            // TODO 校验
            formSubmit(data);
            return false;
        });
        form.on('submit(userSubmit2)', function(data){
            // TODO 校验
            formSubmit2(data);
            return false;
        });

    });
    //搜索框
    layui.use(['form','laydate'], function(){
        var form = layui.form ,layer = layui.layer
            ,laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#insertTimeStart'
        });
        laydate.render({
            elem: '#insertTimeEnd'
        });
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function(data){
            //重新加载table
            load(data);
            return false;
        });

    });
});

//提交表单
function formSubmit(obj){
    submitAjax(obj);
}
function formSubmit2(obj){
    submitAjax2(obj);
}
function submitAjax(obj){
    $.ajax({
        type: "POST",
        data: $("#Form").serialize(),
        url: "/form/addFormField",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功",function(){
                    location.reload();
                });
            }else{
                layer.alert(data,function(){
                    location.reload();
                });
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",function(){
                layer.closeAll();
                //加载load方法
                load(obj);//自定义
            });
        }
    });
}
function submitAjax2(obj){
    $.ajax({
        type: "POST",
        data: $("#Form2").serialize(),
        url: "/form/addFormField",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功",function(){
                    location.reload();
                });
            }else{
                layer.alert(data,function(){
                    location.reload();
                });
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",function(){
                layer.closeAll();
                //加载load方法
                load(obj);//自定义
            });
        }
    });
}
function cleanUser(){
    $("#doooName").val("");
    $("#remark").val("");
    $("#type").val("");
    $("#doooId").val("");
    $("#condition").val("");
    // $("#email").val("");
    // $("#password").val("");
}

//开通用户
function addUser(){
    $("#application").val("");
    $("#encoded").val("");
    $("#url").val("");
    $("#ip_port").val("");
    $("#account").val("");
    $("#password").val("");
    $("#secret_key").val("");
    $("#remarks").val("");
    $("#id").val("");
    openUser(null,"新增事项");
}
function openUser(id,title){
    if(id==null || id==""){
        $("#doooId").val("");
    }
    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setUser'),
        end:function(){
            cleanUser();
        }
    });
}
function getUserAndRoles(obj,id) {
    $("#formFieldId").val(obj.formFieldId);
    $("#formFieldName2").val(obj.formFieldName);
    $("#formFieldNameValue2").val(obj.formFieldNameValue);
    $("#formFieldContent2").val(obj.formFieldContent);
    $("#formFieldAnnotation2").val(obj.formFieldAnnotation);
    var htm = '',htm2 = '';
    if (obj.formFieldType == 0){
        htm =
            ' <option value="0" selected="selected">输入框</option>' +
            ' <option value="1">下拉</option>' +
            ' <option value="2">单选</option>' +
            ' <option value="3">多选</option>' +
            ' <option value="4">文本域</option>' +
            ' <option value="5">上传</option>';
    }else if (obj.formFieldType == 1){
        htm =
        ' <option value="0">输入框</option>' +
            ' <option value="1" selected="selected">下拉</option>' +
            ' <option value="2">单选</option>' +
            ' <option value="3">多选</option>' +
            ' <option value="4">文本域</option>' +
            ' <option value="5">上传</option>';
    }else if (obj.formFieldType == 2){
        htm =
        ' <option value="0">输入框</option>' +
            ' <option value="1">下拉</option>' +
            ' <option value="2" selected="selected">单选</option>' +
            ' <option value="3">多选</option>' +
            ' <option value="4">文本域</option>' +
            ' <option value="5">上传</option>';
    }else if(obj.formFieldType == 3){
        htm =
            ' <option value="0">输入框</option>' +
            ' <option value="1">下拉</option>' +
            ' <option value="2">单选</option>' +
            ' <option value="3" selected="selected">多选</option>' +
            ' <option value="4">文本域</option>' +
            ' <option value="5">上传</option>';
    }else if(obj.formFieldType == 4){
        htm =
            ' <option value="0">输入框</option>' +
            ' <option value="1">下拉</option>' +
            ' <option value="2">单选</option>' +
            ' <option value="3">多选</option>' +
            ' <option value="4" selected="selected">文本域</option>' +
            ' <option value="5">上传</option>';
    }else if(obj.formFieldType == 5){
        htm =
            ' <option value="0">输入框</option>' +
            ' <option value="1">下拉</option>' +
            ' <option value="2">单选</option>' +
            ' <option value="3">多选</option>' +
            ' <option value="4">文本域</option>' +
            ' <option value="5" selected="selected">上传</option>';
    }
    $("#formFieldType2").html(htm);
    if (obj.formFieldIsBasis == 0){
       htm2 = '<option value="1" selected="selected">基础数据</option>' +
           '   <option value="2">其他</option>'
    }else{
        htm2 = '<option value="1" >基础数据</option>' +
            '   <option value="2" selected="selected">其他</option>'
    }
    $("#formFieldIsBasis2").html(htm2);
    layui.form.render("select");
    //回显数据
    openUser(obj.doooId,"修改事项");
}
function delUser(obj,id) {
    if(null!=id){
        layer.confirm('本次操作将删除该表单项，本操作不可逆', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/form/delFormField",{"formFieldId":id},function(data){
                if(isLogin(data)){
                    if(data=="ok"){
                        //回调弹框
                        layer.alert("删除成功！",function(){
                            layer.closeAll();
                            //加载load方法
                            load(obj);//自定义
                        });
                    }else{
                        layer.alert(data,function(){
                            layer.closeAll();
                            //加载load方法
                            load(obj);//自定义
                        });
                    }
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}
function recoverUser(obj) {
    if (confirm("是否确定退订审核？")){
        $.ajax({
            url: '/dooo/putApprovalType',
            data:{'doooId':obj.doooId,'approvalType':1},
            // dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data == "ok"){
                    layer.alert("退订成功！",function(){
                        layer.closeAll();
                        //加载load方法
                        load(obj);//自定义
                    });
                }else{
                    layer.alert(data,function(){
                        layer.closeAll();
                        //加载load方法
                        load(obj);//自定义
                    });
                }
            }
        });
    }
}
//解锁用户
function nolockUser(){
    //TODO 给个输入框，让用户管理员输入需要解锁的用户手机号，进行解锁操作即可
    layer.alert("TODO");
}

function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function put() {
    $.ajax({
        url: '/dooo/putApprovalType',
        data:{'doooId':$("#doooId3").val(),'approvalType':2,'approvalText':$("#approval_type").val()},
        // dataType: 'json',
        type: 'post',
        success: function (data) {
            if (data == "ok"){
                alert("提交成功!");
                layer.closeAll();
                //加载load方法
                // load(obj);//自定义

            }else{
                layer.alert(data,function(){
                    layer.closeAll();
                    //加载load方法
                    // load(obj);//自定义
                });
            }
        }
    });

}
function openUser3(data){
    $("#doooId3").val(data.doooId);
    $("#approval_type").val("");
    layer.open({
        type:1,
        title: '提交审核',
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setUser2'),
        end:function(){
        }
    });
}

