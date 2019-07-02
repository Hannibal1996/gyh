 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	 <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"><link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
 <link href="css/fore/style.css" rel="stylesheet">

 	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script src="js/acommon.js" type="text/javascript"></script>
		 <script src="js/jquery.validate.min.js"></script>   
	<body>
	<%@include file="menu.jsp" %>
	<script type="text/javascript">
	
	
	$(function(){

		
		$("a.productDetailTopReviewLink").click(function(){
			$("div.productReviewDiv").show();
			$("div.productDetailDiv").hide();
		});
		$("a.productReviewTopPartSelectedLink").click(function(){
			$("div.productReviewDiv").hide();
			$("div.productDetailDiv").show();		
		});
		
		$("span.leaveMessageTextareaSpan").hide();
		$("img.leaveMessageImg").click(function(){
			
			$(this).hide();
			$("span.leaveMessageTextareaSpan").show();
			$("div.orderItemSumDiv").css("height","100px");
		});
		
		$("div#footer a[href$=#nowhere]").click(function(){
			alert("模仿天猫的连接，并没有跳转到实际的页面");
		});
		

		$("a.wangwanglink").click(function(){
			alert("模仿旺旺的图标，并不会打开旺旺");
		});
		$("a.notImplementLink").click(function(){
			alert("这个功能没做，蛤蛤~");
		});
		

	});

	
	confirmOrder=function(){

		var address = $("#address").val(); 
		var post = $("input[name=post]").val(); 
		var receiver = $("input[name=receiver]").val();
		var mobile = $("input[name=mobile]").val();
		var userMessage = $("#userMessage").val(); 
		 
		var jsonStr = {
			"address": address,
			"mail": post,
			"telephone": mobile,
			"name": receiver,
			"userMessage": userMessage
			 
			 
		 		}; //object类型
		 var jsonArrayFinal = JSON.stringify(jsonStr); //string类型
		 
	   $.ajax({
	     url:  'shopcar/addOrder',
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
	      if(data.result=="success"){
	    	 window.location.href="pay.jsp"; 
	      }  else if("nologin"==data.result){
				location.href="login.jsp";
	      }else if ("noBlank" == data.result) {
	    	   alert('参数不能为空!');
		    	 
	       } else if ("err" == data.result) {
	    	   alert('提交失败!');
	    	 
	       } 	
	     },
	     error: function(err) {
	       alert('连接错误');
	     }

	   });
		
	}
	
	$(function(){
		$("#formId").validate({
			rules:{
				"address":{
					"required":true,
					  
				},
				"post":{
					"required":true,
					 
				},
				"receiver":{
					"required":true,
					 
				},
				"mobile":{
					"required":true,
					 
				}
				 
				 
			},
			messages:{
				"address":{
					"required":"地址不能为空"
				},
				"post":{
					"required":"邮编不能为空",
					 
				},
				"receiver":{
					"required":"收件人不能为空",
					 
				},
			 
				"mobile":{
					"required":"电话不能为空",
					 
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
				 confirmOrder();
		        }  
		}); 
	})
	</script>
<div class="buyPageDiv">

 
  <form action="" id="formId"method="post">
  
	<div class="buyFlow">
		<img class="pull-left" src="img/site/simpleLogo.png">
		<img class="pull-right" src="img/site/buyflow.png">
		<div style="clear:both"></div>
	</div>
	<div class="address">
		<div class="addressTip">输入收货地址</div>
		<div>
		
			<table class="addressTable">
				<tr>
					<td class="firstColumn">详细地址<span class="redStar">*</span></td>
					
					<td><textarea id="address"name="address" placeholder="建议您如实填写详细收货地址，例如接到名称，门牌好吗，楼层和房间号等信息"></textarea></td>
				</tr>
				<tr>
					<td>邮政编码</td>
					<td><input  id="post"name="post" placeholder="如果您不清楚邮递区号，请填写000000" type="text"></td>
				</tr>
				<tr>
					<td>收货人姓名<span class="redStar">*</span></td>
					<td><input id="receiver" name="receiver"  placeholder="长度不超过25个字符" type="text"></td>
				</tr>
				<tr>
					<td>手机号码 <span class="redStar">*</span></td>
					<td><input name="mobile" id="mobile" placeholder="请输入11位手机号码" type="text"></td>
				</tr>
			</table>
			
		</div>




		
		
		
	
	</div>
	<div class="productList">
		<div class="productListTip">确认订单信息</div>
		
		
		<table class="productListTable">
			<thead>
				<tr>
					<th colspan="2" class="productListTableFirstColumn">
						<img class="tmallbuy" src="img/site/tmallbuy.png">
						<a class="marketLink" href="#nowhere">店铺：天猫店铺</a>
						<a class="wangwanglink" href="#nowhere"> <span class="wangwangGif"></span> </a>
					</th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
					<th>配送方式</th>
				</tr>
				<tr class="rowborder">
					<td  colspan="2" ></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody class="productListTableTbody">
				<c:forEach items="${readybuy}" var="oi" varStatus="st" >
					<tr class="orderItemTR">
						<td class="orderItemFirstTD"><img class="orderItemImg" src="${oi.product.pimage}"></td>
						<td class="orderItemProductInfo">
						<a  onclick="openProductInfo(${oi.product.pid} );" class="orderItemProductLink">
							${oi.product.pname}
						</a>
						
						
							<img src="img/site/creditcard.png" title="支持信用卡支付">
							<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
							<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">
						
						</td>
						<td>
						
						<span class="orderItemProductPrice">￥${oi.product.shopPrice}<fmt:formatNumber type="number" value=" " minFractionDigits="2"/></span>
						</td>
						<td>
						<span class="orderItemProductNumber">${oi.number}</span>
						</td>
						<td><span class="orderItemUnitSum">
						￥${oi.number*oi.product.shopPrice}<fmt:formatNumber type="number" value="${oi.number*oi.product.shopPrice}" minFractionDigits="2"/>
						</span></td>
						<c:if test="${st.count==1}">
						<td rowspan="5"  class="orderItemLastTD">
						<label class="orderItemDeliveryLabel">
							<input type="radio" value="" checked="checked">
							普通配送
						</label>
						
						<select class="orderItemDeliverySelect" class="form-control">
							<option>快递 免邮费</option>
						</select>

						</td>
						</c:if>
						
					</tr>
				</c:forEach>				
				
			</tbody>
			
		</table>
		<div class="orderItemSumDiv">
			<div class="pull-left">
				<span class="leaveMessageText">给卖家留言:</span>
				<span>
					<img class="leaveMessageImg" src="img/site/leaveMessage.png">
				</span>
				<span class="leaveMessageTextareaSpan">
					<textarea name="userMessage" class="leaveMessageTextarea"></textarea>
					<div>
						<span>还可以输入200个字符</span>
					</div>
				</span>
			</div>
			
			<span class="pull-right">店铺合计(含运费): ￥${total}<fmt:formatNumber type="number" value="${total}" minFractionDigits="2"/></span>
		</div>
		

				
	
	</div>

	<div class="orderItemTotalSumDiv">
		<div class="pull-right"> 
			<span>实付款：</span>
			<span class="orderItemTotalSumSpan">￥${total}<fmt:formatNumber type="number" value="${total}" minFractionDigits="2"/></span>
		</div>
	</div>
	
	<div class="submitOrderDiv">
			<button type="submit" class="submitOrderButton">提交订单</button>
	</div>
  </form>		
</body>