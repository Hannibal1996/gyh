<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="images/favicon.png" type="image/png">

  <title>享受生活，上小天猫</title>

  <link href="css/style.default.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
 <script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/jquery.sparkline.min.js"></script>
<script src="js/jquery.cookies.js"></script>

<script src="js/toggles.min.js"></script>
<script src="js/retina.min.js"></script>

<script src="js/select2.min.js"></script>

 <script src="js/jquery.validate.min.js"></script>    
<script src="js/custom.js"></script>
<script src="js/acommon.js"></script>
<script type="text/javascript"> 

window.onload=function(){
	 loginState(); 
	
}
register=function(){

	var username = $("input[name=username]").val(); 
	var password = $("input[name=password]").val();
	var confirmpwd = $("input[name=confirmpwd]").val();
	var name = $("input[name=name]").val();
	var email = $("input[name=email]").val();
	var birthday = $("input[name=birthday]").val(); 
	var telephone = $("input[name=telephone]").val();  
	var jsonStr = {
		"username": username,
		"password": password,
		"name": name,
		"email": email,
		"birthday": birthday,
		"email": email, 
		"telephone":telephone
		 
	 		}; //object类型
	 var jsonArrayFinal = JSON.stringify(jsonStr); //string类型
	 
   $.ajax({
     url:  'loginResgist/register',
     data: jsonArrayFinal,
     type: "POST",
     contentType: 'application/json',
     dataType: 'json',
     headers: {
       "X-Requested-With": "XMLHttpRequest"
     },
     xhrFields: {
       withCredentials: true
     },
     success: function(data) {
       if ("success" == data.result) { 
          window.location.href = 'registerSuccess.jsp';
       } else if ("ErrVerifyCode" == data.result) {
           alert('验证码错误!');
         
       } else if ("err" == data.result) {

    	   window.location.href = 'registerFail.jsp';
       } else if ("noBlank" == data.result) {

           alert("输入不能为空！");
         }else if ("isExistName" == data.result) {

         alert("用户名已注册！");
       }
       changeImg();
     },
     error: function(err) {
       alert('连接错误');
     }

   });
}
$(function(){
	$("#formid").validate({
		rules:{
			"username":{
				"required":true,
				 "userNameCheck":true
			},
			"password":{
				"required":true,
				"rangelength":[6,12]
			},
			"confirmpwd":{
				"required":true,
				"rangelength":[6,12],
				"equalTo":"#password"
			},
			"email":{
				"required":true,
				"email":true,
				"emailCheck":true
			},
			"telephone":{
				"required":true,
				 
				
			},
			"name":{
				"required":true
			},
			"birthday":{
				"required":true,
				"date":true
			},
			"sex":{
				"required":true
			}
		},
		messages:{
			"username":{
				"required":"用户名不能为空"
			},
			"password":{
				"required":"密码不能为空",
				"rangelength":"密码长度在6-12位"
			},
			"confirmpwd":{
				"required":"确认密码不能为空",
				"rangelength":"确认密码长度在6-12位",
				"equalTo":"两次密码不一致"
			},
			"email":{
				"required":"邮箱不能为空",
				"email":"邮箱格式不正确"
			},
			"telephone":{
				"required":"电话不能为空",
					 
			},
			"name":{
				"required":"真实姓名不能为空"
			},
			"birthday":{
				"required":"生日不能为空",
				"date":"日期格式不正确"
			},
			"sex":{
				"required":"性别必须选择"
			}
		},
		errorPlacement: function (error, element) { //指定错误信息位置
		      if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
	
		       error.appendTo(element.parent().parent()); //将错误信息添加当前元素的父结点后面
		     } else {
		       error.insertAfter(element);
		     }
		   },
		 submitHandler:function(form){  //表单提交后要执行的内容
			  register();
	        }  
	}); 
})

 $.validator.addMethod(
			"userNameCheck",//自定义校验规则的名称
			
			function(value,element,params){//自定义校验规则的实现
				//value)表单元素值
				//element)校验的元素对象
				//params)校验规则输入的参数 
				var flag = true;
				//发送一个Ajax，到服务器进行验证用户存在
				$.ajax({
					"async":false,//同步操作
					"url":"loginResgist/userNameCheck",
					"type":"POST",
					"data":{"username":value},
					"dataType":"json",
					"success":function(data){
						flag = data.result;//true--存在  false--不存在
						//alert(flag);
					}
				});
				//需要返回值 false----该校验器校验失败    true---校验通过
				return !flag;
			}, "用户已存在!"
 )
     $.validator.addMethod(
			"emailCheck",//自定义校验规则的名称
			
			function(value,element,params){//自定义校验规则的实现
				//value)表单元素值
				//element)校验的元素对象
				//params)校验规则输入的参数 
				var flag = true;
				//发送一个Ajax，到服务器进行验证用户存在
				$.ajax({
					"async":false,//同步操作
					"url":"loginResgist/emailCheck",
					"type":"POST",
					"data":{"email":value},
					"dataType":"json",
					"success":function(data){
						flag = data.result;//true--存在  false--不存在
						//alert(flag);
					}
				});
				//需要返回值 false----该校验器校验失败    true---校验通过
				return !flag;
			}, "邮箱已注册!"
 )
    </script>
</head>

<body class="signin">





<section>
  
    <div class="signuppanel">
        
        <div class="row">
            
            <div class="col-md-6">
                
                <div class="signup-info">
                    <div class="logopanel">
                        <h1><span> TMall </span></h1>
                    </div><!-- logopanel -->
                
                    <div class="mb20"></div>
                
                    <h3><strong>感谢选择小天猫！</strong></h3>
                    <p>小天猫 - 专业线上综合购物平台，全球消费者挚爱的品质购物之城！商品涵盖服饰箱包、美妆个护、家电数码、母婴玩具、美食酒水、家装家居等各大品类，旨在引领中国消费者第一时间发现和体验全球化、更优质的生活方式，提前开启趋势中的理想生活！理想生活，上小天猫！</p>
                    
                    <div class="mb20"></div>
                    <div class="mb20"></div>
                    <img src="images/tmalllogo.jpg">
<!--                    <img src="images/tamllcode.jpg">-->
<!--
                    <div class="feat-list">
                        <i class="fa fa-wrench"></i>
                        <h4 class="text-success">Easy to Customize</h4>
                        <p></p>
                    </div>
                    
                    <div class="feat-list">
                        <i class="fa fa-compress"></i>
                        <h4 class="text-success">Fully Responsive Layout</h4>
                        <p>Bracket is design to fit on all browser widths and all resolutions on all mobile devices. Try to scale your browser and see the results.</p>
                    </div>
                    
                    <div class="feat-list mb20">
                        <i class="fa fa-search-plus"></i>
                        <h4 class="text-success">Retina Ready</h4>
                        <p>When a user load a page, a script checks each image on the page to see if there's a high-res version of that image. If a high-res exists, the script will swap that image in place.</p>
                    </div>
-->
                    
<!--                    <h4 class="mb20">and much more...</h4>-->
                
                </div><!-- signup-info -->
            
            </div><!-- col-sm-6 -->
            
            <div class="col-md-6">
                
                <form id="formid" method="post" action="" >
                
                    <h3 class="nomargin">注册</h3>
                    <p class="mt5 mb20">已经注册? <a href="signin.jsp"><strong>登录</strong></a></p>
                    <label class="control-label">用户名</label>
                    <div class="row mb10">
<!--                        <div class="col-sm-6">-->
                        <div>
                            <input type="text" class="form-control" placeholder="姓名" name="name"/>
                        </div>
 
                    </div>
                    
                    <div class="mb10">
                        <label class="control-label">账号</label>
                        <input type="text" class="form-control" placeholder="账号" name="username"/>
                    </div>
                    
                    <div class="mb10">
                        <label class="control-label">密码</label>
                        <input id="password" type="password" class="form-control" placeholder="密码" name="password"/>
                    </div>
                    
                    <div class="mb10">
                        <label class="control-label">确认密码</label>
                        <input id="confirmpwd" type="password" name="confirmpwd" class="form-control" />
                    </div>
                    
                    <label class="control-label">出生年月</label>
                    <div class="row mb10">
                         	 
							<input type="date" class="form-control" name="birthday">
						 
                    </div>
                    
 
                    <div class="mb10">
                        <label class="control-label"> 邮箱</label>
                        <input type="text" name="email" id="email" class="form-control" />
                    </div>
 
                    
                    <div class="mb10">
                        <label class="control-label">手机号</label>
                        <input type="text" name="telephone"class="form-control" />
                    </div>
                   
                    <br />
                    
                    <input type="submit" class="btn btn-success btn-block" value="注册" onclick=" ">
                </form>
            </div><!-- col-sm-6 -->
            
        </div><!-- row -->
        
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2018. All Rights Reserved. TMall corporation
            </div>
            <div class="pull-right">
                Created By: Hugh
            </div>
        </div>
        
    </div><!-- signuppanel -->
  
</section>



<script>
    jQuery(document).ready(function(){
        
        jQuery(".select2").select2({
            width: '100%',
            minimumResultsForSearch: -1
        });
        
        jQuery(".select2-2").select2({
            width: '100%'
        });
        
        
        // Please do not use the code below
        // This is for demo purposes only
        var c = jQuery.cookie('change-skin');
        if (c && c == 'greyjoy') {
            jQuery('.btn-success').addClass('btn-orange').removeClass('btn-success');
        } else if(c && c == 'dodgerblue') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        } else if (c && c == 'katniss') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        }
        
    });
</script>

</body>
</html>
