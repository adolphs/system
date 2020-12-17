/**
 * 套餐列表
 */
var pageCurr;
$(function() {
    layui.use('table', function(){
        var table = layui.table
            ,form = layui.form;
        var comboId = $("#id").val();
        tableIns=table.render({
            elem: '#uesrList'
            ,url:'/situation/getSituationList'
            ,where: {'doooId':$("#doooId").val()}
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
                ,{field:'situationDetailsDescribe', title: '相应情形', }
                ,{field:'newTime', title: '添加时间',align:'center',unresize: true, sort: true}
                ,{fixed:'right', title:'操作', width:180,align:'center', toolbar:'#optBar'}
            ]]
            ,  done: function(res, curr, count){
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
                console.log("1");
                delUser(data,data.doooId,data.doooName);
            } else if(obj.event === 'edit'){
                //编辑
                // getUserAndRoles(data,data.id);
                getUserAndRoles2(data,data.id);
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data,data.id);
            } else if(obj.event === 'sel'){
                //查看
                // console.log(data)
                window.location.href = '/situation/situationDetails?situationId='+data.situationId;
            }
        });
        //监听提交
        form.on('submit(userSubmit)', function(data){

            // // TODO 校验
            formSubmit(data);
            return false;
        });
        form.on('submit(comboSubmit)',function (data) {
            formSubmit2(data);
            return false;
        })
        form.on('submit(userSubmit4)',function () {
            formSubmit5();
            return false;
        })

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
        form.on('submit(userSubmit2)', function(data){
            var doooId2 = $("#doooId2").val();
            var dataEndId = $("#dataSelect2").val();
            $.ajax({
                url: '/dataEnding/addDataEndingAndDooo',
                data:{"dataEndId":dataEndId,"doooId":doooId2},
                type: 'post',
                success: function (data) {
                    console.log(data)
                    if (data == "ok") {
                        layer.alert("关联成功",function(){
                            location.reload();
                        });
                    }else{
                        layer.alert(data,function(){

                        });
                    }
                    //重新渲染 固定写法
                }
            });
            return false;
        });

    });
});

//提交表单
function formSubmit(obj){
    submitAjax(obj);
}
//提交表单
function formSubmit2(obj){
    submitAjax2(obj);
}
function formSubmit5(){
    $.ajax({
        type: "POST",
        data: $("#programindow").serialize(),
        url: "/dooo/operatingProgramWindow",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功",function(){
                    location.reload();
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
function submitAjax(obj){

    console.log($("#doooId").val());
    console.log(obj.field.dataId);
    $.ajax({
        type: "POST",
        data: {'dataId':obj.field.dataId,'doooId':$("#doooId").val()},
        url: "/dooo/addDoooAndData",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功",function(){
                    location.reload();
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
function submitAjax2(obj,currentUser){
    $.ajax({
        type: "POST",
        data: {'dataId':$("#dataId").val(),'templateUrl':$("#templateUrl").val(),'templateName':$("#templateName").val()},
        url: "/data/addTemplateUrl",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功",function(){
                    location.reload();
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

    $("#condition").val("");
}

//开通用户
function addUser(){
    //处理下拉框
    layui.use(['form', 'upload', 'layer'], function () {

        var form = layui.form;
        //检查项目添加到下拉框中
        $.ajax({
            url: '/data/getDataAll',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                var tmp='';
                tmp +='<option value=""></option>';
                for (var i in data){
                    tmp +='<option value="'+data[i].dataId+'">'+data[i].dataName+'</option>';
                }
                $("#dataSelect").html(tmp);
                layui.form.render("select");
            }
        });


    });
    openUser(null,"绑定通用材料");
}
function addCombo() {
    $("#comboDteId").val("");
    $("#situationDescribe").val("");
    $("#situationDetailsDescribe").val("");
    openCombo(null,"新增情形");
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


//开通用户
function addDataEnding(){
    //处理下拉框
    layui.use(['form', 'upload', 'layer'], function () {

        var form = layui.form;
        //检查项目添加到下拉框中
        $.ajax({
            url: '/dataEnding/setAll',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                var tmp='';
                tmp +='<option value=""></option>';
                for (var i in data){
                    tmp +='<option value="'+data[i].data_ending_id+'">'+data[i].data_ending_name+'</option>';
                }
                $("#dataSelect2").html(tmp);
                layui.form.render("select");
            }
        });


    });
    openUser2(null,"绑定通用材料");
}
function openUser2(id,title){

    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setUser2'),
        end:function(){
            cleanUser();
        }
    });
}
function windows() {
    $.ajax({
        type: "GET",
        url: "/dooo/getProgramWindowList?doooId="+$("#winId").val(),
        success: function (data) {
            if (data != null) {
                $("#id").val(data[0].id);
                $("#tel").val(data[0].tel);
                $("#address").val(data[0].address);
                $("#worktime").val(data[0].worktime);
                $("#trafficGuide").val(data[0].trafficGuide);
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
    openUser3();
}
function openUser3(){
    layer.open({
        type:1,
        title: "咨询方式",
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#openWindow'),
        end:function(){
            cleanUser();
        }
    });
}
function getUserAndRoles(obj,id) {
    // $("#doooId").val(obj.doooId);
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
function getUserAndRoles2(obj,id) {
    $("#comboDteId").val(id);
    $("#situationDescribe").val(obj.situationDescribe);
    $("#situationDetailsDescribe").val(obj.detailsDescribe);

    //回显数据
    openUser2(id,"修改情形");
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

