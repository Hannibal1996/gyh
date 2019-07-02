
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
     
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	 
       
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"></link>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    		<script src="js/acommon.js" type="text/javascript"></script>
    		<script src="js/jquery.validate.min.js"></script>
      
  </head>
 <script type="text/javascript">
//获取产品id
var pid = null;
pid=sessionStorage.getItem("pid"); 
var price=0;
 
if (pid ==null &&id == "") { 
	window.location.href = "index.jsp";
} 

window.onload=function(){
	 var flag=sessionStorage.getItem("flag");
	 //alert(flag);
	 if(flag!="1"){
		 loginState();
	 }
}

$(document).ready(function() {
 
	queryProduct();
});
 
//查询列表数据
queryProduct  = function() { 
	var jsonStr = {
		"pid" : pid 
	}; 
	$ .ajax({
				url : 'product/findProductById',
				async : false,
				type : "POST",
				data : jsonStr,
				dataType : 'json',
				crossDomain : true,
				headers : {
					"X-Requested-With" : "XMLHttpRequest"
				},
				xhrFields : {
					withCredentials : true
				},
				success : function(data) {
					price=data.product.shopPrice;
					var listInfoString ='<div  class="col-md-5 column">'+
					'<div class="carousel slide" id="carousel-399229"> '+
					'<div class="carousel-inner"> '+
							 	'<div class="item active">'+
								 	'<img  src="'+data.product.pimage+'" style="width:100%;height:350px" class="img-responsive img-thumbnail image_css" />'+
									'<div class="carousel-caption"></div>'+
								'</div> '+
								'<div class="item">'+
									'<img  src="'+data.product.pimage+'" style="width:100%;height:350px" class="img-responsive img-thumbnail image_css"/>'+
									'<div class="carousel-caption"></div>'+
								'</div> '+
					'</div> <a class="left carousel-control" href="#carousel-399229" data-slide="prev">'+
					'<span class="glyphicon glyphicon-chevron-left"></span></a>'+
					' <a class="right carousel-control" href="#carousel-399229" data-slide="next">'+
					' <span class="glyphicon glyphicon-chevron-right"></span></a>'+
				'</div>'+
		'	</div>'+
				 
			'<div class="col-md-5 column">'+
			' <form id="cartId" action="" method="post">'+
			'	<dl class="dl-horizontal" >'+
				'	<dt >商品	</dt>'+
				'	<dd>'+data.product.pname+' </dd>'+
					'<br>'+
					'<dt>	描述	</dt>'+
					'<dd>'+data.product.pdesc+' </dd>'+
					'<br>'+
					'<dt> 	价格 	</dt>'+
					'<dd>'+price+'</dd>'+
					'<br> '+
					'<dt>数量	</dt> '+
					'<dd> <input type="text"  placeholder="请输入商品数量（至少为1）" name="quantity"/></dd>'+
				'</dl>'+
				'<br> '+
			'	<input type="submit" value="添加购物车" class="btn btn-default btn-primary btn-block"> </input>'+
				'</form>'+
			'</div>'
					
					 ;

					 
					//数据显示到页面
					$("#productInfoDiv").empty();

					$(listInfoString).appendTo($("#productInfoDiv"));
				},
				error : function(err) {
					alert('请求失败 ');
				}
			});
}

addToShopCar =function( ){
	
	var quantity = $("input[name=quantity]").val(); 
	 var sumprice=price*quantity;
	var jsonStr = {
		"pid": pid,
		"sumprice": sumprice,
		"number":quantity 
	 		}; //object类型
	  var jsonArrayFinal = JSON.stringify(jsonStr); //string类型
	 
   $.ajax({
     url:  'shopcar/addToShopCar',
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
    	   window.location.href = 'ShoppingCart.jsp';
          
       } else if ("nologin" == data.result) {
    	   alert("您还未登录，请登录！")
    	   window.location.href = 'signin.jsp';
         
       } else if ("noBlank" == data.result) {
    	   alert('参数不能为空!');
    	 
       } else if ("err" == data.result) {
    	   alert('添加购物车失败!');
    	 
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
	$("#cartId").validate({
		rules:{
			"quantity":{
				"required":true,
				"digits":true,
				"max":99
			} 
			 
			 
		},
		messages:{
			"quantity":{
				"required":"数量不能为空",
				"digits":"必须为整数",
				"max":"最大为99"
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
			 addToShopCar();
	        }  
	}); 
})
</script>

  <body>
  <%@include file="menu.jsp"%>
    <div  class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div  class="logo" width="70%"><h1>
				欢迎使用小天猫购物系统
			<img src="images/header.png"></h1></div>
			<div class="logo">               
            </div>
			<div id="productInfoDiv"class="row clearfix">
			
				<div class="col-md-6 column">
					<div class="carousel slide" id="carousel-399229"> 
						<div class="carousel-inner"> 
								 	<div class="item active">
									 	<img  src=" " style="width:100%;height:350px" class="img-responsive img-thumbnail image_css" />
										<div class="carousel-caption"></div>
									</div> 
									<div class="item">
										<img  src="" style="width:100%;height:350px" class="img-responsive img-thumbnail image_css"/>
										<div class="carousel-caption"></div>
									</div> 
						</div> <a class="left carousel-control" href="#carousel-399229" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left"></span></a>
						 <a class="right carousel-control" href="#carousel-399229" data-slide="next">
						 <span class="glyphicon glyphicon-chevron-right"></span></a>
					</div>
				</div>
					<br> <br> <br> <br> <br>  <br>
				<div class="col-md-5 column">
				 <form action="ShopServlet" method="post">
					<dl class="dl-horizontal" >
						<dt >商品	</dt>
						<dd> </dd>
						<br>
						<dt>	描述	</dt>
						<dd> </dd>
						<br>
						<dt> 	价格 	</dt>
						<dd> ${productById.price}</dd>
						<br> 
						<dt>数量	</dt> 
						<dd> <input type="text"  placeholder="请输入商品数量（至少为1）" name="pnum"/></dd>
					</dl>
					<br> 
					<input type="submit" value="添加购物车" class="btn btn-default btn-primary btn-block"> </input>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column"> <img src="images/footer.jpg" width="100%" />
        </div>
		<div class="col-md-12 column" style="text-align: center;">
		    <a href="#">关于我们</a>
            <a href="#">联系我们</a>
            <a href="#">招贤纳士</a>
            <a href="#">法律声明</a>
            <a href="#">友情链接</a>
            <a href="#">支付方式</a>
            <a href="#">配送方式</a>
            <a href="#">服务声明</a>
            <a href="#">广告声明</a>

            <br /> Copyright © 2018 小天猫商城 版权所有
		
		</div>
		</div>
	</div>
</div>
  </body>
</html>
