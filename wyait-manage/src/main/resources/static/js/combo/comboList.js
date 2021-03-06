/**
 * 套餐列表
 */
var pageCurr;
$(function() {
    layui.use('table', function(){
        var table = layui.table
            ,form = layui.form;

        tableIns=table.render({
            elem: '#uesrList'
            ,url:'/combo/getComboList'
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
                ,{field:'comboName', title:'套餐名称'}
                ,{field:'approvalTypeName', title:'审核状态'}
                ,{field:'doooCount', title:'参与事项'}
                ,{field:'departmentCount', title: '参与部门'}
                ,{field:'newTime', title: '添加时间',align:'center',unresize: true, sort: true}
                ,{fixed:'right', title:'操作', width:300,align:'center', toolbar:'#optBar'}
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
                delUser(data,data.id,data.comboName);
            } else if(obj.event === 'edit'){
                //编辑
                // getUserAndRoles(data,data.id);
                window.location.href="/combo/comboUpdate?comboId="+data.id;
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data);
            }else if(obj.event === 'select'){
                window.location.href='/combo/comboDetails?id='+data.id;
            }else if (obj.event === 'put'){
                console.log(data)
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
    var currentUser=$("#currentUser").html();
    submitAjax(obj,currentUser);
}
function submitAjax(obj,currentUser){
    $.ajax({
        type: "POST",
        data: $("#doooForm").serialize(),
        url: "/combo/setCombo",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功",function(){
                    layer.closeAll();
                    cleanUser();
                    load(obj);
                });
            }else{
                layer.alert(data,function(){
                    layer.closeAll();
                    cleanUser();
                    load(obj);
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
// function checkRole(){
//     //选中的角色
//     var array = new Array();
//     var roleCheckd=$(".layui-form-checked");
//     //获取选中的权限id
//     for(var i=0;i<roleCheckd.length;i++){
//         array.push($(roleCheckd.get(i)).prev().val());
//     }
//     //校验是否授权
//     var roleIds = array.join(",");
//     if(roleIds==null || roleIds==''){
//         layer.alert("请您给该用户添加对应的角色！")
//         return false;
//     }
//     $("#roleIds").val(roleIds);
//     return true;
// }
//开通用户
function addUser(){
    $.get("/auth/getRoles",function(data){
        if(data!=null){
            //显示角色数据
            $("#roleDiv").empty();
            $.each(data, function (index, item) {
                var roleInput=$("<input type='checkbox' name='roleId' value="+item.id+" title="+item.roleName+" lay-skin='primary'/>");

                var div=$("<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
                    "<span>"+item.roleName+"</span><i class='layui-icon'>&#xe626;</i>" +
                    "</div>");
                $("#roleDiv").append(roleInput).append(div);
            })
            openUser(null,"新增事项");
            //重新渲染下form表单 否则复选框无效
            layui.use(['form', 'upload', 'layer'], function () {

                var form = layui.form;
                //检查项目添加到下拉框中
                $.ajax({
                    url: '/department/getDepartments',
                    dataType: 'json',
                    type: 'get',
                    success: function (data) {
                        var tmp='<option value="">请选择</option>';
                        for (var i in data){
                            tmp +='<option value="'+data[i].departmentId+'">'+data[i].departmentName+'</option>';
                        }
                        $("#departmentInsert").html(tmp);
                        layui.form.render("select");

                    }
                });
            });
        }else{
            //弹出错误提示
            layer.alert("获取角色数据有误，请您稍后再试",function () {
                layer.closeAll();
            });
        }
    });
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
    $("#doooId").val(obj.doooId);
    $("#doooName").val(obj.doooName);
    $("#remark").val(obj.remark);
    $("#condition").val(obj.doooCondition);

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
                    if (data.departmentId == id){
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
    openUser(id,"修改事项");
}
function delUser(obj,id,name) {
	var currentUser=$("#currentUser").html();
	console.log(obj);
	console.log(id);
	console.log(name);
    if(null!=id){
        layer.confirm('您确定要删除'+name+'套餐吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/combo/delCombo",{"comboId":id},function(data){
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
            url: '/combo/putApprovalType',
            data:{'comboId':obj.id,'approvalType':1},
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
        url: '/combo/putApprovalType',
        data:{'comboId':$("#comboId").val(),'approvalType':2,'approvalText':$("#approval_type").val()},
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
    $("#comboId").val(data.id);
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