 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 <script src="js/jquery-1.11.1.min.js"></script>
	<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
	<link href="css/fore/style.css" rel="stylesheet">
	<script src="js/toggles.min.js"></script>
<script src="js/retina.min.js"></script>
<script src="js/acommon.js"></script>
 <script src="js/jquery.validate.min.js"></script>
<script type="text/javascript">

window.onload=function(){
	 loginState(); 
	
}
login=function(){ 
	var username = $("input[name=username]").val(); 
	var password = $("input[name=password]").val();
	var verifyCode = $("input[name=VerifyCode1]").val(); 
	var jsonStr = {
		"username": username,
		"pwd": password,
		"verifyCode":verifyCode 
	 		};  
	 
   $.ajax({
     url:  'loginResgist/login',
     data: jsonStr,
     type: "POST",
     //contentType: 'application/json',
     dataType: 'json',
     headers: {
       "X-Requested-With": "XMLHttpRequest"
     },
     xhrFields: {
       withCredentials: true
     },
     success: function(data) {
       if ("success" == data.result) { 
    	   if(data.user.state==1){
    		   if($('#autoLogin').is(':checked')) {
    			  // document.cookie="username="+data.user.username;
    			   setCookie("username",data.user.username,3);
    			   setCookie("password",data.user.password,3);
    			   
    			}
    		   window.location.href = 'index.jsp';
    		   sessionStorage.setItem('flag', "1");
    	   }else{
    		 alert("账户未激活！");  
    	   }
          
       } else if ("ErrVerifyCode" == data.result) {
           alert('验证码错误!');
         
       } else if ("pwdErr" == data.result) {
    	   alert('密码错误!');
    	 
       } else if ("NoUserName" == data.result) {
    	   alert('用户名不存在!');
    	 
       } 
       changeImg();
     },
     error: function(err) {
       alert('连接错误');
     }

   });
}

//表单验证
$(function(){
	$("#loginform").validate({
		rules:{
			"username":{
				"required":true
			},
			"password":{
				"required":true, 
			},
			"VerifyCode1":{
				"required":true, 
			}
			 
			 
		},
		messages:{
			"username":{
				"required":"用户名不能为空"
			},
			"password":{
				"required":"密码不能为空",
				 
			} ,
			"VerifyCode1":{
				"required":"验证码",
				 
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
			 login();
	        }  
	}); 
})

function changeImg(){
	var img = document.getElementById("img");  
	img.src = "loginResgist/authImage?date=" + Math.random();
} 
</script>
 <body>
<div id="loginDiv" style="position: relative">

	<div class="simpleLogo">
		<a href="${contextPath}"><img src="img/site/simpleLogo.png"></a>
	</div>

	
	<img id="loginBackgroundImg" class="loginBackgroundImg" src="img/site/loginBackground.png">
	
	<form class="loginForm" id="loginform"action="" method="post">
		<div id="loginSmallDiv" class="loginSmallDiv">
			<div class="loginErrorMessageDiv">
				<div class="alert alert-danger" >
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
				  	<span class="errorMessage"></span>
				</div>
			</div>
				
			<div class="login_acount_text">账户登录</div>
			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-user"></span>
				</span>
				<input id="username" name="username" placeholder="手机/会员名/邮箱" type="text">			
			</div>
			
			<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-lock"></span>
				</span>
				<input id="password" name="password" type="password" placeholder="密码" type="text">
			</div>
				<div class="loginInput " >
				<span class="loginInputIcon ">
					<span class="glyphicon glyphicon-question-sign "></span>
				</span>
				  <input  type="text"   name="VerifyCode1" id="VerifyCode1"
									placeholder="请输入验证码">	
			</div>
		 <div>
			<img class="pull-right"  id="img"onclick="changeImg();" src="loginResgist/authImage" />
              <br> 
                <br>         
          </div>        
			
			
			<div>
			 
			 
				
				<a href="signup.jsp" class="pull-right">免费注册</a> 
				<input type="checkbox"  id="autoLogin"> 自动登录
			</div>
			<div style="margin-top:20px">
				<button class="btn btn-block redButton" type="submit">登录</button>
			</div>
		</div>	
	</form> 

</div>
<div id="footer"  class="footer" style="display: block;">
	
    <div id="footer_ensure" class="footer_ensure">
            <a href="#nowhere">
            	<img src="img/site/ensure.png">
            </a>
    </div>
    
    <div id="footer_desc" class="footer_desc">
            <div class="descColumn">
			    <span class="descColumnTitle">购物指南</span>
			    <a href="#nowhere" >免费注册</a> 
			    <a href="#nowhere" >开通支付宝</a> 
			    <a href="#nowhere" >支付宝充值</a>
			</div>
            <div class="descColumn">
			    <span class="descColumnTitle">天猫保障</span>
			    <a href="#nowhere" >发票保障</a> 
			    <a href="#nowhere" >售后规则</a> 
			    <a href="#nowhere" >缺货赔付</a>
			</div>
            <div class="descColumn">
			    <span class="descColumnTitle">支付方式</span>
			    <a href="#nowhere" >快捷支付</a> 
			    <a href="#nowhere" >信用卡</a> 
			    <a href="#nowhere" >蚂蚁花呗</a>
			    <a href="#nowhere" >货到付款</a>
			</div>
            <div class="descColumn">
			    <span class="descColumnTitle">商家服务</span>
			    <a href="#nowhere" >商家入驻</a> 
			    <a href="#nowhere" >商家中心</a> 
			    <a href="#nowhere" >天猫智库</a>
			    <a href="#nowhere" >天猫规则</a>
			    <a href="#nowhere" >物流服务</a>
			    <a href="#nowhere" >喵言喵语</a>
			    <a href="#nowhere" >运营服务</a>
			</div>
            <div class="descColumn">
			    <span class="descColumnTitle">手机天猫</span>
			    <a href="#nowhere" ><img src="img/site/ma.png"></a> 
			</div>
			
    </div>
    
    <div style="clear:both"></div>
    
    <img id="cateye" class="cateye" src="img/site/cateye.png">
    
    <div id="copyright" class="copyright">
		<div class="white_link" >
			<a href="#nowhere" >关于天猫</a>
			<a href="#nowhere" > 帮助中心</a>
			<a href="#nowhere" >开放平台</a>
			<a href="#nowhere" >  诚聘英才</a>
			<a href="#nowhere" >联系我们</a>
			<a href="#nowhere" >网站合作</a>
			<a href="#nowhere" >法律声明</a>
			<a href="#nowhere" >知识产权</a>
			<a href="#nowhere" >  廉正举报	</a>
		</div>
		<div class="white_link" >
			<a href="#nowhere" > 阿里巴巴集团</a><span class="slash">|</span>
			<a href="#nowhere" > 淘宝网</a><span class="slash">|</span>
			<a href="#nowhere" >天猫 </a><span class="slash">|</span>
			<a href="#nowhere" >  聚划算</a><span class="slash">|</span>
			<a href="#nowhere" >全球速卖通</a><span class="slash">|</span>
			<a href="#nowhere" >阿里巴巴国际交易市场</a><span class="slash">|</span>
			<a href="#nowhere" >1688</a><span class="slash">|</span>
			<a href="#nowhere" >阿里妈妈</a><span class="slash">|</span>
			<a href="#nowhere" >  阿里旅行·去啊	</a><span class="slash">|</span>
			<a href="#nowhere" >  阿里云计算	</a><span class="slash">|</span>
			<a href="#nowhere" >  阿里通信 	</a><span class="slash">|</span>
			<a href="#nowhere" >  YunOS	</a><span class="slash">|</span>
			<a href="#nowhere" >  阿里旅行·去啊	</a><span class="slash">|</span>
			<a href="#nowhere" >   万网	</a><span class="slash">|</span>
			<a href="#nowhere" >  高德	</a><span class="slash">|</span>
			<a href="#nowhere" > 优视 	</a><span class="slash">|</span>
			<a href="#nowhere" >  友盟	</a><span class="slash">|</span>
			<a href="#nowhere" >  虾米	</a><span class="slash">|</span>
			<a href="#nowhere" >  天天动听	</a><span class="slash">|</span>
			<a href="#nowhere" >  来往	</a><span class="slash">|</span>
			<a href="#nowhere" >  钉钉	</a><span class="slash">|</span>
			<a href="#nowhere" >  支付宝 		</a>
		</div>
	
		<div class="license">
			<span>增值电信业务经营许可证： 浙B2-20110446</span>
			<span>网络文化经营许可证：浙网文[2015]0295-065号</span>
			<span>互联网医疗保健信息服务 审核同意书 浙卫网审【2014】6号 </span>
			<span>互联网药品信息服务资质证书编号：浙-（经营性）-2012-0005</span>
		    <div class="copyRightYear">&copy; 2003-2016 TMALL.COM 版权所有</div>
		    <div>
		    	<img src="img/site/copyRight1.jpg">
		    	<img src="img/site/copyRight2.jpg">
		    </div>
		</div>
    </div>
</div>	
</body>