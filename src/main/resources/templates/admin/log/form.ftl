<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>- 表单验证 </title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">
<link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
<link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						
					</div>
					<div class="ibox-content">
						<p>职工信息</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>职工编辑</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="frm" method="post"
							action="${ctx!}/admin/employee/edit">
							<input type="hidden" id="id" name="id" value="${employee.id}">
							<div class="form-group">
								<label class="col-sm-3 control-label">姓名：</label>
								<div class="col-sm-8">
									<input id="name" name="name" class="form-control"
										value="${employee.name}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">职位：</label>
								<div class="col-sm-8">
									<select name="posid" class="form-control" >
 				  						<#if map2?exists>
   											<#list map2?keys as key>
  												<option value="${key} " <#if employee.posid == "${key}">selected="selected"</#if>>
                                                    ${map2[key]?default("未能找到对应数据"+"${key}")} 
                                                  </option>
  											 </#list>
  										</#if>
									</select>
								</div>
							</div>	
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								<div class="col-sm-8">
									<select name="sex" class="form-control">
										<option value="0"<#if employee.sex ==
											0>selected="selected"</#if>>女</option>
										<option value="1"<#if employee.sex ==
											1>selected="selected"</#if>>男</option>
									</select>
								</div>
							</div>
							<div class="form-group">
                               <label class="col-sm-3 control-label">部门：</label>
                                <div class="col-sm-8">
                                	<select name="departmentid" class="form-control" >
 				  						<#if map3?exists>
   											<#list map3?keys as key>
  												<option value="${key} " <#if employee.departmentid == "${key}">selected="selected"</#if>>
                                                    ${map3[key]?default("未能找到对应数据"+"${key}")} 
                                                  </option>
  											 </#list>
  											 </#if>
										</select>
                                </div>
                            </div>
							<div class="form-group">
								<label class="col-sm-3 control-label">地址：</label>
								<div class="col-sm-8">
									<input id="address" name="address" class="form-control"
										value="${employee.address}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-8">
									<input id="email" name="email" class="form-control"
										value="${employee.email}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">电话：</label>
								<div class="col-sm-8">
									<input id="phone" name="phone" class="form-control"
										value="${employee.phone}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">学历：</label>
								<div class="col-sm-8">
									<input id="tiptopdegree" name="tiptopdegree" class="form-control"
										value="${employee.tiptopdegree}">
								</div>
							</div>
							<#if !employee?exists>
							<div class="form-group">
								<label class="col-sm-3 control-label">出生年月：</label>
								<div class="col-sm-8">
										<input id="birthday" name="birthday"  class="laydate-icon form-control layer-date" value="${employee.birthday}" <#if employee?exists> readonly="readonly"</#if> />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">入职年月：</label>
								<div class="col-sm-8">
										<input id="begincontract" name="begincontract"  class="laydate-icon form-control layer-date" value="${employee.begincontract}" <#if employee?exists> readonly="readonly"</#if> />
								</div>
							</div>
							</#if>
							</div>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button class="btn btn-primary" type="submit">提交</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- 全局js -->
	<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

	<!-- 自定义js -->
	<script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

	<!-- jQuery Validation plugin javascript-->
	<script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
	<script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>
	<script type="text/javascript">
	
    $(document).ready(function () {
    	//外部js调用
	    laydate({
	        elem: '#birthday' ,//目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	        event: 'focus'
	        });
	    laydate({
	        elem: '#begincontract' ,//目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	        event: 'focus'
	        });
	  	
	    $("#frm").validate({
    	    rules: {
    	    	name: {
    	        required: true,
    	        minlength: 1,
    	    	maxlength: 20
    	      },
    	    	posid: {
    	        required: true,
    	        minlength: 1,
    	    	maxlength: 40
    	      },
    	        sex: {
    	        required: true
    	      },
    	        departmentid: {
    	        required: true
    	      },
    	        address: {
    	        required: true,
    	    	minlength: 1,
    	    	maxlength: 1000
    	      },
    	        phone: {
    	      	number:true,
    	        required: true
    	      },
    	        tiptopdegree: {
    	        maxlength: 40
    	      },
    	      	birthday: {
      	      	date:true,
      	        required: true
    	      },
    	     	 birthday: {
      	      	date:true,
      	        required: true
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/employee/edit",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
   	    		        alert(msg.message)
	   	    			layer.msg(msg.message, {time: 0.1},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    });
    </script>

</body>

</html>
