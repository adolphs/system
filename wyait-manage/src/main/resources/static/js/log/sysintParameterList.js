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
            ,url:'/sysIntfParameter/getSysIntfParameterList'
        	,method: 'post' //默认：get请求
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
                ,{field:'userName', title:'操作人'}
                ,{field:'application', title:'应用系统'}
                ,{field:'encoded', title: '应用编码',}
                ,{field:'url', title: '路径', }
                ,{field:'ipPort', title: 'ip/端口号', }
                ,{field:'enabled', title: '是否启用',toolbar:'#jobTpl' }
                ,{field:'account', title: '账号', }
                ,{field:'password', title: '密码', }
                ,{field:'secretKey', title: '密匙', }
                ,{field:'remarks', title: '备注', }
                ,{field:'createDate', title: '添加时间',align:'center',unresize: true, sort: true}
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
                delUser(data,data.id);
            } else if(obj.event === 'edit'){
                //编辑
                getUserAndRoles(data,data.id);
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
function submitAjax(obj){
    $.ajax({
        type: "POST",
        data: $("#doooForm").serialize(),
        url: "/sysIntfParameter/addSysIntfParameter",
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
    console.log(obj);
    console.log(id);
    $("#application").val(obj.application);
    $("#encoded").val(obj.encoded);
    $("#url").val(obj.url);
    $("#ip_port").val(obj.ipPort);
    $("#account").val(obj.account);
    $("#password").val(obj.password);
    $("#secret_key").val(obj.secretKey);
    $("#remarks").val(obj.remarks);
    $("#id").val(id);
    if (obj.enabled == 1){
        var htm = '<option value="1" selected="selected">是</option>'+
            '<option value="0">否</option>';
        $("#enabled").html(htm);
        layui.form.render("select");
    }else{
        var htm = '<option value="1">是</option>'+
            '<option value="0" selected="selected">否</option>';
        $("#enabled").html(htm);
        layui.form.render("select");
    }
    layui.form.render("select");
    //回显数据
    openUser(obj.doooId,"修改事项");
}
function delUser(obj,id,name) {
	var currentUser=$("#currentUser").html();
	console.log(obj);
	console.log(id);
	console.log(name);
    if(null!=id){
        layer.confirm('您确定要删除'+name+'事项吗？本次操作将删除与本事项相关的所有情形，本操作不可逆', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/dooo/delDooo",{"doooId":id},function(data){
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

