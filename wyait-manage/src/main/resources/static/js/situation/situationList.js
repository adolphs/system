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
            ,url:'/situation/getSituationList'
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
                // ,{field:'situationId', title:'ID', width:80, }   
                ,{field:'situationDescribe', title:'情形名称'}
                ,{field:'doooName', title:'事项名称'}
                ,{field:'departmentName', title:'所属部门'}
                // ,{field:'situationDetailsDescribe', title: '相应情形', }
                ,{field:'newTime', title: '添加时间',align:'center',unresize: true, sort: true}
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
                delUser(data,data.situationId,data.situationDescribe);
            } else if(obj.event === 'edit'){
                //编辑
                getUserAndRoles(data,data.situationId);
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data,data.id);
            }else if(obj.event === 'sel'){
                //查看
                window.location.href='/situation/situationDetails?situationId='+data.situationId;
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
    console.log("提交");
    $.ajax({
        type: "POST",
        data: $("#situationForm").serialize(),
        url: "/situation/getSituation",
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

}

//开通用户
function addUser(){
    $.get("/auth/getRoles",function(data){
        if(data!=null){
            //显示角色数据
            $("#roleDiv").empty();
            $.each(data, function (index, item) {
                // <input type="checkbox" name="roleId" title="发呆" lay-skin="primary"/>
                var roleInput=$("<input type='checkbox' name='roleId' value="+item.id+" title="+item.roleName+" lay-skin='primary'/>");
                //未选中
                /*<div class="layui-unselect layui-form-checkbox" lay-skin="primary">
                    <span>发呆</span><i class="layui-icon">&#xe626;</i>
                    </div>*/
                //选中
                // <div class="layui-unselect layui-form-checkbox layui-form-checked" lay-skin="primary">
                // <span>写作</span><i class="layui-icon">&#xe627;</i></div>
                var div=$("<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
                    "<span>"+item.roleName+"</span><i class='layui-icon'>&#xe626;</i>" +
                    "</div>");
                $("#roleDiv").append(roleInput).append(div);
            })
            openUser(null,"开通用户");
            //重新渲染下form表单 否则复选框无效
            layui.form.render('checkbox');
        }else{
            //弹出错误提示
            layer.alert("获取角色数据有误，请您稍后再试",function () {
                layer.closeAll();
            });
        }
    });
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
    console.log(obj);
    console.log(id);
    $("#situationDescribe").val(obj.situationDescribe);
    $("#situationId").val(obj.situationId);
    $("#situationDetailsDescribe").val(obj.situationDetailsDescribe);

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
        $.ajax({
            url: '/dooo/getDoooList',
            dataType: 'json',
            data:{"departmentId":obj.departmentId},
            type: 'get',
            success: function (data1) {
                var tmp='';
                for (var i in data1){
                    if (data1[i].doooId == obj.doooId){
                        tmp +='<option value="'+data1[i].doooId+'" selected>'+data1[i].doooName+'</option>';
                    }else{
                        tmp +='<option value="'+data1[i].doooId+'">'+data1[i].doooName+'</option>';
                    }
                }
                console.log("tmp="+tmp);
                $("#doooInsert").html(tmp);
                layui.form.render("select");

                //重新渲染 固定写法
            }
        });
    });
    //回显数据
    openUser(id,"修改情形");
}
function delUser(obj,id,name) {
	var currentUser=$("#currentUser").html();
	console.log(obj);
	console.log(id);
	console.log(name);
    if(null!=id){
        layer.confirm('您确定要删除'+name+'情形吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/situation/delSituation",{"situationId":id},function(data){
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

