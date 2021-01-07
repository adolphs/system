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
            ,url:'/formMain/getFormMainList?formMainComboId='+$("#formMainComboId").val()
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
                ,{field:'formMainId', title:'主表识别号'}
                ,{field:'formMainName', title:'主表名称'}
                ,{field:'departmentName', title:'所属部门'}
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
                // console.log(data);
                delUser(data,data.formMainId);
            } else if(obj.event === 'edit'){
                //编辑
                // console.log(data)
                getUserAndRoles(data,data.formMainId);
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data);
            } else if(obj.event == 'select'){
                // 查看
                window.location.href='/dooo/doooDetails?id='+data.doooId;
            } else if(obj.event == 'put'){
                // put(data);
                //跳转子情形页面
                window.location.href='/form/formDetails?comboId='+$("#formMainComboId").val()+'&formId='+data.formMainId
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
        url: "/formMain/addFormMainField",
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
        url: "/formMain/addFormMainField",
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
    $("#formFieldName2").val(obj.formMainName);
    $("#formMainId2").val(obj.formMainId);

    //处理下拉框
    layui.use(['form', 'upload', 'layer'], function () {

        var form = layui.form;
        //检查项目添加到下拉框中
        $.ajax({
            url: '/department/getDepartments',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                var tmp='';
                for (var i in data){
                    if (data[i].departmentId == obj.departmentId){
                        tmp +='<option value="'+data[i].departmentId+'" selected>'+data[i].departmentName+'</option>';
                    }else{
                        tmp +='<option value="'+data[i].departmentId+'">'+data[i].departmentName+'</option>';
                    }

                }
                $("#departmentInsert").html(tmp);
                layui.form.render("select");
            }
        });


    });

    //回显数据
    openUser(null,"修改主表名称");
}
function delUser(obj,id) {
    if(null!=id){
        layer.confirm('本次操作将删除该表单项，本操作不可逆', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/formMain/delFormMainField",{"formMainId":id},function(data){
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

