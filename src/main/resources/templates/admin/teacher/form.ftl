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
						<p>教师信息</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>教师编辑</h5>
					</div>
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="frm" method="post"
							action="${ctx!}/admin/student/edit">
							<input type="hidden" id="id" name="id" value="${student.id}">
							<div class="form-group">
								<label class="col-sm-3 control-label">姓名：</label>
								<div class="col-sm-8">
									<input id="name" name="name" class="form-control"
										value="${student.name}">
								</div>
							</div>
						
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								<div class="col-sm-8">
									<select name="sex" class="form-control">
										<option value="0"<#if student.sex ==
											0>selected="selected"</#if>>女</option>
										<option value="1"<#if student.sex ==
											1>selected="selected"</#if>>男</option>
									</select>
								</div>
							</div>
					
							<div class="form-group">
								<label class="col-sm-3 control-label">地址：</label>
								<div class="col-sm-8">
									<input id="address" name="address" class="form-control"
										value="${student.address}">
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-8">
									<input id="email" name="email" class="form-control"
										value="${student.email}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">电话：</label>
								<div class="col-sm-8">
									<input id="phone" name="phone" class="form-control"
										value="${student.phone}">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">民族：</label>
								<div class="col-sm-8">
									<input id="nation" name="nation" class="form-control"
										value="${student.nation}">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">籍贯：</label>
								<div class="col-sm-8">
									<input id="nativeplace" name="nativeplace" class="form-control"
										value="${student.nativeplace}">
								</div>
							</div>
					
						
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
   	    		   url: "${ctx!}/admin/student/edit",
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
