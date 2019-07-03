<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 请假申请 </title>
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
                        <h5>请假审批</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/leave/edits">
                             <input type="hidden" id="id" name="id" value="${leave.id}">
                             	<div class="form-group">
                                <label class="col-sm-3 control-label">请假类型：</label>
                                <div class="col-sm-8">
                                    <select name="status" class="form-control" >
  												<option value="1" >同意</option>
  												<option value="2" >不同意</option>
										</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注信息：</label>
                                <div class="col-sm-8">
                                    <textarea name="remark" id="remark" class="form-control"  style="width:525px;height:200px;"  >
 									</textarea>
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
    	 laydate({
 	        elem: '#startTime' ,
 	      //  event: 'focus',
 	       	type: 'datetime'
 	        });
 	    laydate({
 	        elem: '#stopTime' ,
 	     //   event: 'focus',
 	      	type: 'datetime'
 	        });
 	   
	    $("#frm").validate({
    	    rules: {
    	    	name: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 20
    	      },
    	      	sourceKey: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 40
    	      },
    	      	type: {
    	        required: true
    	      },
    	      	sourceUrl: {
    	        required: true
    	      },
    	      	level: {
    	        required: true,
    	        number:true
    	      },
    	      	sort: {
    	      	number:true,
    	        required: true
    	      },
    	      	icon: {
    	        maxlength: 40
    	      },
    	      	isHide: {
    	        required: true
    	      },
    	      	description: {
    	        required: true,
    	        maxlength: 40
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/leave/edits",
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
    
    function message(){
        var start = document.getElementById("startTime").value;
        var stop = document.getElementById("stopTime").value;
        console.log(start);
        console.log(stop);
        getHoliday(start, stop);
        console.log(getHoliday(start, stop));
        $("#duration").trigger("input"); 
    	$("#duration").val(getHoliday(start, stop) + "天");
    }
    function getHoliday(sdate, edate) {
        var num = datedifference(sdate, edate);
        var lastday = num % 7;
        var weeknum = 0;
        if (num >= 7) {
            weeknum = parseInt(num / 7);
        }

        var weekday = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var result = 0;
        for (var i = 0; i < lastday; i++) {
            var dd = new Date(addDate(sdate, i)).getDay();
            if (weekday[dd] != "星期六" && weekday[dd] != "星期日") {
                result++;
            }
        }
        return result + weeknum * 5 + 1;
      //  return result + weeknum * 5;
    }

    //两个时间相差天数 兼容firefox chrome
    function datedifference(sDate1, sDate2) { //sDate1和sDate2是2006-12-18格式  
        var dateSpan, tempDate, iDays;
        sDate1 = Date.parse(sDate1);
        sDate2 = Date.parse(sDate2);
        dateSpan = sDate2 - sDate1;
        dateSpan = Math.abs(dateSpan);
        iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
        return iDays
    };

    function addDate(date, days) {
        var d = new Date(date);
        d.setDate(d.getDate() + days);
        var m = d.getMonth() + 1;
        return d.getFullYear() + '-' + m + '-' + d.getDate();
    }
    </script>

</body>

</html>
