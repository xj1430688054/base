<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 表单验证 </title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
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
                        
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>完整验证表单</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/salary/edit">
                        	<input type="hidden" id="id" name="id" value="${salary.id}">
                        	<div class="form-group">
                                <label class="col-sm-3 control-label">账套名字：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="${salary.name}"  >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">基本工资：</label>
                                <div class="col-sm-8">
                                    <input id="basicsalary" name="basicsalary" class="form-control" type="text" value="${salary.basicsalary}"  >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">交通补助：</label>
                                <div class="col-sm-8">
                                    <input id="trafficsalary" name="trafficsalary" class="form-control" type="text" value="${salary.trafficsalary}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">午餐补助：</label>
                                <div class="col-sm-8">
                                    <input id="lunchsalary" name="lunchsalary" class="form-control" type="text" value="${salary.lunchsalary}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">奖金：</label>
                                <div class="col-sm-8">
                                    <input id="bonus" name="bonus" class="form-control" type="text" value="${salary.bonus}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">养老金比率：</label>
                                <div class="col-sm-8">
                                    <input id="pensionper" name="pensionper" class="form-control" type="text" value="${salary.pensionper}">
                                </div>
                                <label class="col-sm-3 control-label">养老金基数：</label>
                                <div class="col-sm-8">
                                    <input id="pensionbase" name="pensionbase" class="form-control" type="text" value="${salary.pensionbase}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">医疗基金比率：</label>
                                <div class="col-sm-8">
                                    <input id="medicalper" name="medicalper" class="form-control" type="text" value="${salary.medicalper}">
                                </div>
                                <label class="col-sm-3 control-label">医疗基金基数：</label>
                                <div class="col-sm-8">
                                    <input id="medicalbase" name="medicalbase" class="form-control" type="text" value="${salary.medicalbase}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公积金比率：</label>
                                <div class="col-sm-8">
                                    <input id="accumulationfundper" name="accumulationfundper" class="form-control" type="text" value="${salary.accumulationfundper}">
                                </div>
                                <label class="col-sm-3 control-label">公积金基数：</label>
                                <div class="col-sm-8">
                                    <input id="accumulationfundbase" name="accumulationfundbase" class="form-control" type="text" value="${salary.accumulationfundbase}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">启用时间：</label>
                                <div class="col-sm-8">
                                    <input id="createdate" name="createdate" class="laydate-icon form-control layer-date" type="text" value="${salary.createdate}">
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
	        elem: '#createdate' ,//目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	        event: 'focus'
	        });
	    $("#frm").validate({
    	    rules: {
    	    	name: {
    	        required: true,
    	        minlength: 2,
    	    	maxlength: 10
    	      },
    	     	basicsalary: {
    	        required: true,
    	        digits: true
    	      },
    	      	trafficsalary: {
    	        required: true,
    	        digits: true
    	      },
    	      	lunchsalary: {
    	        required: true,
    	        digits: true
    	      },
    	    	bonus: {
    	        required: true,
    	        digits: true
    	      },
    	     	basicsalary: {
    	        required: true,
    	        digits: true
    	      } ,
    	      	pensionbase: {
    	        required: true,
    	        digits: true
    	      },
    	      	accumulationfundbase: {
      	        required: true,
      	        digits: true
      	      },
      	    	medicalbase: {
    	        required: true,
    	        digits: true
    	      },
    	      	pensionper: {
      	        required: true,
      	      	number: true
      	      },
      	    	accumulationfundper: {
    	        required: true,
    	        number: true
    	      },
    	      	medicalper: {
      	        required: true,
      	      	number: true
      	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/salary/edit",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
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
