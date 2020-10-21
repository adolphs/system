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
            ,url:'/dataEnding/getDataList'
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
                ,{field:'data_ending_name', title:'材料名称'}
                // ,{field:'remarks', title:'填表须知'}
                ,{field:'dataTypeName', title: '材料类型'}
                ,{field:'isElectronicLicense', title: '是否关联电子证照'}
                // ,{field:'templateUrl', title: '示例样本'}
                // ,{field:'blankUrl', title: '空表下载'}
                ,{field:'new_time', title: '添加时间',align:'center',unresize: true, sort: true}
                ,{fixed:'right', title:'操作', width:180,align:'center', toolbar:'#optBar'}
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
                delUser(data,data.dataId,data.dataName);
            } else if(obj.event === 'edit'){
                //编辑
                getUserAndRoles(data);
                // console.log(data);
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data,data.id);
            }else if(obj.event === 'select'){
                window.location.href='/data/dataDetails?dataId='+data.dataId;
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
    var currentUser=$("#currentUser").html();
    submitAjax(obj,currentUser);
}
function submitAjax(obj,currentUser){
    $.ajax({
        type: "POST",
        data: $("#doooForm").serialize(),
        url: "/dataEnding/addDataEnding",
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
    $("#data_ending_id").val('');
    $("#blank_name").val('');
    $("#blank_url").val('');
    $("#data_ending_name").val('');
    $("#template_name").val('');
    $("#template_url").val('');
    var htm = '';
    htm = ' <option value=""></option>' +
        '<option value="1">批文</option>' +
        '   <option value="2">证件</option>';
    $("#data_type").html(htm);
    htm = ' <option value=""></option>' +
        '   <option value="1">是，已关联电子证照</option>' +
        '   <option value="2">否，未关联电子证照</option>';
    $("#is_electronic_license").html(htm);
    layui.form.render("select");
    openUser(null,"新增材料");

}
function openUser(title){
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
function getUserAndRoles(obj) {
    $("#data_ending_id").val(obj.data_ending_id);
    $("#blank_name").val(obj.blank_name);
    $("#blank_url").val(obj.blank_url);
    $("#data_ending_name").val(obj.data_ending_name);
    $("#template_name").val(obj.template_name);
    $("#template_url").val(obj.template_url);
    var htm = '';
    if (obj.data_type == 1){
        htm = ' <option value="1">批文</option>' +
            '   <option value="2">证件</option>';
    }else{
        htm = ' <option value="2">证件</option>' +
            '  <option value="1">批文</option> ';
    }
    $("#data_type").html(htm);
    if (obj.is_electronic_license == 1){
        htm = ' <option value="1">是，已关联电子证照</option>' +
            '   <option value="2">否，未关联电子证照</option>';
    }else{
        htm = ' <option value="2">否，未关联电子证照</option>' +
            '  <option value="1">是，已关联电子证照</option> ';
    }
    $("#is_electronic_license").html(htm);
    layui.form.render("select");

    //回显数据
    openUser("修改材料");
}
function delUser(obj,id,name) {
	var currentUser=$("#currentUser").html();
	console.log(obj);
	console.log(id);
	console.log(name);
    if(null!=id){
        layer.confirm('您确定要删除'+name+'材料吗？继续操作将删除与本材料相关的所有情形或事项的关系，本操作不可逆', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/data/delData",{"dataId":id},function(data){
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
function recoverUser(obj,id) {
    //console.log("需要恢复的用户id="+id);
	var version=obj.version;
	//console.log("delUser版本:"+version);
    if(null!=id){
        layer.confirm('您确定要恢复'+name+'用户吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/user/recoverUser",{"id":id,"version":version},function(data){
                if(isLogin(data)){
                    if(data=="ok"){
                        //回调弹框
                        layer.alert("恢复成功！",function(){
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

