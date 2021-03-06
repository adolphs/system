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
            ,url:'/combo/getComboSituationList?comboId='+comboId
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
                ,{field:'situationDescribe', title:'套餐情形名称'}
                ,{field:'newTime', title: '添加时间',align:'center',unresize: true, sort: true}
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
                console.log(data);
                delUser(data,data.id,data.situationDescribe);
            } else if(obj.event === 'edit'){
                //编辑
                console.log(data);
                getUserAndRoles2(data,data.id);
            } else if(obj.event === 'recover'){
                //恢复
                recoverUser(data,data.id);
            } else if(obj.event === 'sel'){
                window.location.href='/combo/comboSituationDetails?id='+data.id;
            }
        });
        //监听提交
        form.on('submit(userSubmit)', function(data){
            // TODO 校验
            formSubmit(data);
            return false;
        });
        form.on('submit(comboSubmit)',function (data) {
            formSubmit2(data);
            return false;
        });
        form.on('submit(dataSubmit)',function (data) {
            formSubmit3(data);
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
        form.on('submit(searchSubmit)', function(data){
            //重新加载table
            // xxx(data);
            console.log(data.field.comboName);
            var comboId = $("#id").val();
            var type = $("#type5").val();
            $.ajax({
                url: '/combo/updateComboDooo',
                data:{"comboName":data.field.comboName,"id":comboId,"type":type},
                type: 'post',
                success: function (data) {
                    console.log(data)
                    if (data == "ok") {
                        layer.alert("修改成功",function(){
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

        form.on('submit(userSubmit6)', function(data){
            var comboId2 = $("#comboId2").val();
            var dataEndId = $("#dataSelect2").val();
            $.ajax({
                url: '/dataEnding/addDataEndingAndCombo',
                data:{"dataEndId":dataEndId,"comboId":comboId2},
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
    var currentUser=$("#currentUser").html();
    submitAjax(obj,currentUser);
}
//提交表单
function formSubmit2(obj){
    submitAjax2(obj);
}
function formSubmit3(obj){
    submitAjax3(obj);
}
function submitAjax(obj){
    $.ajax({
        type: "POST",
        data: $("#programindow").serialize(),
        url: "/combo/addDooo",
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
    var id = $("#id1").val();
    var comboId = $("#comboId1").val();
    var situationLevel = $("#situationLevel").val();
    var situationDescribe = $("#situationDescribe").val();
    var nodesType = $("#nodesType").val();
    var type = $("#type").val();
    console.log(id);
    console.log(comboId);
    console.log(situationLevel);
    console.log(situationDescribe);
    console.log(nodesType);
    console.log(type);
    $.ajax({
        type: "POST",
        data: {"comboId":comboId,"id":id,"situationDescribe":situationDescribe,"situationLevel":situationLevel,"nodesType":nodesType,"type":type},
        url: "/combo/addComboSituationAndData",
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
function submitAjax3(obj){
    var dataId = $("#dataSelect").val();
    var comboId = $("#id").val();
    console.log(dataId);
    $.ajax({
        type: "POST",
        data: {"comboId":comboId,'dataId':dataId},
        url: "/data/setDataAndCombo",
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
	// $("#doooName").val("");
	// $("#remark").val("");
    // $("#type").val("");
    // $("#doooId").val("");
    // $("#condition").val("");
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
function addCombo() {
    $("#id1").val("");
    $("#situationDescribe").val("");
    $("#situationDetailsDescribe").val("");
    openCombo(null,"新增情形");
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
function openUser2(id,title){
    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setComboDte'),
        end:function(){
            cleanUser();
        }
    });
}

function openCombo(id,title) {

    if(id==null || id==""){
        $("#comboDteId").val("");
    }
    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setComboDte'),
        end:function(){
            cleanUser();
        }
    });
}
function getUserAndRoles(obj,id) {
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
function getUserAndRoles2(obj,id) {
    $("#id1").val(id);
    $("#situationDescribe").val(obj.situationDescribe);
    var type = obj.type;
    var nodesType = obj.nodesType;
    var htp='',hmp='';
    if (nodesType == 1){
        htp+= '  <option value="0">最终情形</option>' +
            '  <option value="1" selected>节点情形</option>'+
            '  <option value="2">材料情形</option>'  ;
    }else if (nodesType == 0){
        htp+= '  <option value="0" selected>最终情形</option>' +
            '  <option value="1" >节点情形</option>'+
            '  <option value="2">材料情形</option>'  ;
    }else if(nodesType == 2){
        htp+= '  <option value="0">最终情形</option>' +
            '  <option value="1" >节点情形</option>'+
            '  <option value="2"  selected>材料情形</option>'  ;
    }
    if (type == 1){
        hmp += '<option value="1" selected>单选</option>' +
            ' <option value="2" >多选</option>';
    }else{
        hmp += '<option value="1" >单选</option>' +
            ' <option value="2" selected>多选</option>';
    }
    $("#nodesType").html(htp);
    $("#type").html(hmp);
    layui.form.render("select");
    //回显数据
    openUser2(id,"修改情形");
}

function delUser(obj,id,name) {

    if(null!=id){
        layer.confirm('您确定要删除'+name+'事项吗？本次操作将删除与本事项相关的所有情形，本操作不可逆', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/combo/delComboSituation",{"id":id},function(data){
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

function addData(){
    openData("新增材料");
}
function openData(name) {
    layer.open({
        type:1,
        title: name,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setData'),
        end:function(){
            // cleanUser();
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
    addDataEnding2(null,"绑定通用材料");
}
function addDataEnding2(id,title){

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
