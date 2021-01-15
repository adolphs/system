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
            ,url:'/constructionDictionary/getConstructionDictionaryList'
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
                // ,{field:'id', title:'ID', width:80, unresize: true, sort: true}
                ,{field:'name', title:'字典表名称'}
                ,{field:'level', title:'级别',toolbar:'#jobTpl'}
                ,{field:'value', title: '值' }
                // ,{field:'insertTime', title: '添加时间',align:'center'}
                // ,{field:'isJob', title:'是否在职',width:95,align:'center',templet:'#jobTpl'}
                ,{fixed:'right', title:'操作', width:140,align:'center', toolbar:'#optBar'}
            ]]
            ,  done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                //console.log(curr);
                //得到数据总量
                //console.log(count);
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
            console.log(data);
            if(obj.event === 'del'){
                delUser(data,data.constructionDictionaryId,data.name);
            } else if(obj.event === 'edit'){
                //编辑
                console.log(data);
                getUserAndRoles(data,data.constructionDictionaryId);
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data,data.id);
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
//设置用户是否离职
function setJobUser(obj,id,nameVersion,checked){
//	var version = obj.data.version;
    var name=nameVersion.substring(0,nameVersion.indexOf("_"));
    var version=nameVersion.substring(nameVersion.indexOf("_")+1);
    //console.log("name:"+name);
    //console.log("version:"+version);
    var isJob=checked ? 0 : 1;
    var userIsJob=checked ? "在职":"离职";
    //是否离职
    layer.confirm('您确定要把用户：'+name+'设置为'+userIsJob+'状态吗？', {
        btn: ['确认','返回'] //按钮
    }, function(){
        $.post("/user/setJobUser",{"id":id,"job":isJob,"version":version},function(data){
            if(isLogin(data)){
                if(data=="ok"){
                    //回调弹框
                    layer.alert("操作成功！",function(){
                        layer.closeAll();
                        //加载load方法
                        load(obj);
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
        //加载load方法
        load(obj);
    });
}
//提交表单
function formSubmit(obj){

    submitAjax(obj);

}
function submitAjax(obj){
    $.ajax({
        type: "POST",
        data: $("#userForm").serialize(),
        url: "/constructionDictionary/addConstructionDictionary",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功",function(){
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


//开通用户
function addUser(){
    //关闭其他
    $("#fileId").val("");
    $("#fileName").val("");
    openUser(null,"新增部门");
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
            // cleanUser();
        }
    });
}
function getUserAndRoles(obj,id) {
    $("#constructionDictionaryId").val(obj.constructionDictionaryId);
    $("#name1").val(obj.name);
    $("#value").val(obj.value);
    var htm = '';
    if (obj.level == 1){
        htm='<option value="1" selected="selected">主类</option>' +
            '<option value="2">次类</option>' +
            '<option value="3">阶段</option>';
    }else if(obj.level == 2){
        htm='<option value="1">主类</option>' +
            '<option value="2" selected="selected">次类</option>' +
            '<option value="3">阶段</option>';
    }else if(obj.level == 3){
        htm='<option value="1">主类</option>' +
            '<option value="2">次类</option>' +
            '<option value="3" selected="selected">阶段</option>';
    }
    $("#level1").html(htm);

    var htm1 = '';
    if (obj.pid == 1){
       htm1='<option value="1" selected="selected">政府投资项目</option><option value="2">社会投资项目</option>';
    }else if(obj.pid == 2){
        htm1='<option value="1">政府投资项目</option><option value="2" selected="selected">社会投资项目</option>';
    }
    $("#pid").html(htm1);
    //回显数据
    layui.form.render("select");
    if (obj.level == 2){
        $("#pid_class").css("display","block");
    }else{
        $("#pid_class").css("display","none");
    }
    openUser(id,"修改字典表");

}
function delUser(obj,id,name) {
    if(null!=id){
        layer.confirm('您确定要删除'+name+'吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/constructionDictionary/delConstructionDictionary",{"constructionDictionaryId":id},function(data){
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
