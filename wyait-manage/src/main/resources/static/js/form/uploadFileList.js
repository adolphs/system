/**
 * 用户列表
 */
var pageCurr;
$(function() {
    layui.use('table', function(){
        var table = layui.table
            ,form = layui.form;
        tableIns=table.render({
            elem: '#uploadFile'
            ,url:'/uploadFile/getUploadFileList?comboId='+$("#comboId").val()
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
                ,{field:'fileName', title:'文件名称'}
                ,{field:'departmentName', title:'所属部门'}
                ,{field:'uploadSystem', title:'上传系统'}
                ,{field:'createTime', title: '添加时间',align:'center',unresize: true, sort: true}
                ,{fixed:'right', title:'操作', width:200,align:'center', toolbar:'#optBar'}
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
        table.on('tool(departmentList)', function(obj){
            var data = obj.data;
            console.log(data);
            if(obj.event === 'del'){
                console.log(data);
                delUser(data,data.fileId,data.fileName);
            } else if(obj.event === 'edit'){
                //编辑
                console.log(data.departmentId);
                getUserAndRoles(data);
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
        data: $("#departmentForm").serialize(),
        url: "/uploadFile/addUploadFile",
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
    var htm = '<option value="">请选择</option><option value="一网通办">一网通办</option>\n' +
        '<option value="建设工程">建设工程</option>\n' +
        '<option value="番禺区一站式审批系统">番禺区一站式审批系统</option>';
    $("#uploadSystem").html(htm);
    layui.form.render("select");
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
    $("#fileId").val(obj.fileId);
    $("#fileName").val(obj.fileName);
    var htm = '';
    if(obj.uploadSystem == "一网通办"){
        htm = '<option value="一网通办" selected="selected">一网通办</option>\n' +
            '<option value="建设工程">建设工程</option>\n' +
            '<option value="番禺区一站式审批系统">番禺区一站式审批系统</option>';
    }else if (obj.uploadSystem == "建设工程"){
        htm = '<option value="一网通办">一网通办</option>\n' +
            '<option value="建设工程"  selected="selected">建设工程</option>\n' +
            '<option value="番禺区一站式审批系统">番禺区一站式审批系统</option>';
    }else if(obj.uploadSystem == "番禺区一站式审批系统"){
        htm = '<option value="一网通办">一网通办</option>\n' +
            '<option value="建设工程" >建设工程</option>\n' +
            '<option value="番禺区一站式审批系统" selected="selected">番禺区一站式审批系统</option>';

    }

    $("#uploadSystem").html(htm);
    layui.form.render("select");
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

    //回显数据
    openUser(id,"修改部门");

}
function delUser(obj,id,name) {

    //console.log("delUser版本:"+version);
    if(null!=id){
            layer.confirm('您确定要删除'+obj.fileName+'附件吗？', {
                btn: ['确认','返回'] //按钮
            }, function(){
                $.post("/uploadFile/deleteFile",{"fileId":obj.fileId},function(data){
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

