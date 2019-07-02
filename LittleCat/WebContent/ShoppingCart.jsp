 

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	 <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link href="css/fore/style.css" rel="stylesheet">

 	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script src="js/acommon.js" type="text/javascript"></script>
<script>
var deleteOrderItem = false;
var deleteOrderItemid = 0;
$(function(){

	$("a.deleteOrderItem").click(function(){
		deleteOrderItem = false;
		var oiid = $(this).attr("oiid")
		deleteOrderItemid = oiid;
		$("#deleteConfirmModal").modal('show');	   
	});
	delShopCar=function(carId){
		deleteOrderItem = false;
		//var oiid = $(this).attr("oiid")
		deleteOrderItemid = carId;
		$("#deleteConfirmModal").modal('show');	   
	};
	$("button.deleteConfirmButton").click(function(){
		deleteOrderItem = true;
		$("#deleteConfirmModal").modal('hide');
	});
	
	$('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		if(deleteOrderItem){
			var page="shopcar/delFromShopCar";
			$.post(
				    page,
				    {"carId":deleteOrderItemid},
				    function(data){
						if("success"==data.result){
						//	$("tr.cartProductItemTR[oiid="+deleteOrderItemid+"]").hide();
							findShopCarList();
						}
						else if("nologin"==data.result){
							location.href="login.jsp";
						}
				    }
				);
			
			
			
		}
	})	
	
	$("img.cartProductItemIfSelected").click(function(){
		var selectit = $(this).attr("selectit")
		if("selectit"==selectit){
			$(this).attr("src","img/site/cartNotSelected.png");
			$(this).attr("selectit","false")
			$(this).parents("tr.cartProductItemTR").css("background-color","#fff");
		}
		else{
			$(this).attr("src","img/site/cartSelected.png");
			$(this).attr("selectit","selectit")
			$(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
		}
		syncSelect();
		syncCreateOrderButton();
		calcCartSumPriceAndNumber();
	});
	
	//单选
	itemSelect=function(carId){
		var selectit = $("#img"+carId).attr("selectit")
		if("selectit"==selectit){
			$("#img"+carId).attr("src","img/site/cartNotSelected.png");
			$("#img"+carId).attr("selectit","false")
			$("#img"+carId).parents("tr.cartProductItemTR").css("background-color","#fff");
		}
		else{
			$("#img"+carId).attr("src","img/site/cartSelected.png");
			$("#img"+carId).attr("selectit","selectit")
			$("#img"+carId).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
		}
		syncSelect();
		syncCreateOrderButton();
		calcCartSumPriceAndNumber();
	}
	//全选
	$("img.selectAllItem").click(function(){
		var selectit = $(this).attr("selectit")
		if("selectit"==selectit){
			$("img.selectAllItem").attr("src","img/site/cartNotSelected.png");
			$("img.selectAllItem").attr("selectit","false")
			$(".cartProductItemIfSelected").each(function(){
				$(this).attr("src","img/site/cartNotSelected.png");
				$(this).attr("selectit","false");
				$(this).parents("tr.cartProductItemTR").css("background-color","#fff");
			});			
		}
		else{
			$("img.selectAllItem").attr("src","img/site/cartSelected.png");
			$("img.selectAllItem").attr("selectit","selectit")
			$(".cartProductItemIfSelected").each(function(){
				$(this).attr("src","img/site/cartSelected.png");
				$(this).attr("selectit","selectit");
				$(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
			});				
		}
		syncCreateOrderButton();
		calcCartSumPriceAndNumber();
		

	});
	
	$(".orderItemNumberSetting").keyup(
			function(){
		var pid=$(this).attr("pid");
	 
		var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
		
		var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
		num = parseInt(num);
		if(isNaN(num))
			num= 1;
		if(num<=0)
			num = 1;
	 
		
		syncPrice(pid,num,price);
	});

 
	changeNumber=function(pid,carId,price){
		 
	 
	
		var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
		num = parseInt(num);
		 
		if(isNaN(num))
			num= 1;
		if(num<=0)
			num = 1;
	 
		
		syncPrice(pid,num,price,carId);
	}
	numberPlus=function(pid,carId){
 
	 
		 
		var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
		//var carId= $("span.orderItemPromotePrice[oiid="+oiid+"]").text();
		 
		//alert(carId);
		var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
		 
		num++;
		 
		syncPrice(pid,num,price,carId);
	}
	 
	numberMinus=function(pid,carId){
		 
	 
		var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
		
		var num= $(".orderItemNumberSetting[pid="+pid+"]").val();
		--num;
		if(num<=0)
			num=1;
		syncPrice(pid,num,price,carId);
	}
	$("button.createOrderButton").click(function(){
		var params = "";
		$(".cartProductItemIfSelected").each(function(){
			if("selectit"==$(this).attr("selectit")){
				var oiid = $(this).attr("oiid");
				 
				params += oiid+",";
			}
		}); 
	 
		var jsonStr = {
				"params":params
				 
			 		}; //object类型
			 
			   $.ajax({
			     url:'shopcar/redayBuy',
			     data: jsonStr,
			     type: "POST",
			    //contentType: 'application/json',
			     dataType: 'json',
			     traditional: true,
			     headers: {
			       "X-Requested-With": "XMLHttpRequest"
			     },
			     xhrFields: {
			       withCredentials: true
			     },
			     success: function(data) {
			       if ("success" == data.result) { 
			    	window.location.href="buyPage.jsp";
			    	 
			       }  
			      
			     },
			     error: function(err) {
			       alert('连接错误');
			     }

			   });
		 
	});
	
	
	
})

function syncCreateOrderButton(){
	var selectAny = false;
	$(".cartProductItemIfSelected").each(function(){
		if("selectit"==$(this).attr("selectit")){
			selectAny = true;
		}
	});
	
	if(selectAny){
		$("button.createOrderButton").css("background-color","#C40000");
		$("button.createOrderButton").removeAttr("disabled");
	}
	else{
		$("button.createOrderButton").css("background-color","#AAAAAA");
		$("button.createOrderButton").attr("disabled","disabled");		
	}
		
}
function syncSelect(){
	var selectAll = true;
	$(".cartProductItemIfSelected").each(function(){
		if("false"==$(this).attr("selectit")){
			selectAll = false;
		}
	});
	
	if(selectAll)
		$("img.selectAllItem").attr("src","img/site/cartSelected.png");
	else
		$("img.selectAllItem").attr("src","img/site/cartNotSelected.png");
	
	
	
}
function formatMoney(num){
	num = num.toString().replace(/\$|\,/g,'');  
	if(isNaN(num))  
	    num = "0";  
	sign = (num == (num = Math.abs(num)));  
	num = Math.floor(num*100+0.50000000001);  
	cents = num%100;  
	num = Math.floor(num/100).toString();  
	if(cents<10)  
	cents = "0" + cents;  
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	num = num.substring(0,num.length-(4*i+3))+','+  
	num.substring(num.length-(4*i+3));  
	return (((sign)?'':'-') + num + '.' + cents);  
}
function calcCartSumPriceAndNumber(){
	var sum = 0;
	var totalNumber = 0;
	$("img.cartProductItemIfSelected[selectit='selectit']").each(function(){
		var oiid = $(this).attr("oiid");
		var price =$(".cartProductItemSmallSumPrice[oiid="+oiid+"]").text();
		price = price.replace(/,/g, "");
		price = price.replace(/￥/g, "");
		sum += new Number(price);	
		
		var num =$(".orderItemNumberSetting[oiid="+oiid+"]").val();
		totalNumber += new Number(num);	
		
	});
	
	$("span.cartSumPrice").html("￥"+formatMoney(sum));
	$("span.cartTitlePrice").html("￥"+formatMoney(sum));
	$("span.cartSumNumber").html(totalNumber);
}
function syncPrice(pid,num,price,carId){
	$(".orderItemNumberSetting[pid="+pid+"]").val(num);
	var cartProductItemSmallSumPrice = formatMoney(num*price); 
	$(".cartProductItemSmallSumPrice[pid="+pid+"]").html("￥"+cartProductItemSmallSumPrice);
	calcCartSumPriceAndNumber(); 
	var jsonStr = {
			"carId":carId,
			"pid": pid,
			  "sumprice": num*price,
			"number":num 
		 		}; //object类型
		  var jsonArrayFinal = JSON.stringify(jsonStr); //string类型 
		   $.ajax({
		     url:'shopcar/updateNumber',
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
		    	  
		    	 
		       } else if ("nologin" == data.result) {
		    	   alert("您还未登录，请登录！")
		    	   window.location.href = 'signin.jsp';
		         
		       } else if ("noBlank" == data.result) {
		    	   alert('参数不能为空!');
		    	 
		       } else if ("err" == data.result) {
		    	   alert('添加购物车失败!');
		    	 
		       } 
		      
		     },
		     error: function(err) {
		       alert('连接错误');
		     }

		   });

}
</script>	
<script type="text/javascript">
	window.onload=function(){

		 var flag=sessionStorage.getItem("flag");
		if(flag!="1"){
			 loginState();
		 }
	 
}
$(document).ready(function(){
	findShopCarList();

	 });
findShopCarList=function(){	
	 
    $.ajax({
      url:  'shopcar/findShopCarList', 
      type: "POST",
      contentType: 'application/json',
      dataType: 'json', 
      success: function(data) {
    	  if(data.result=="success"){
    		  var listInfoString = '' ;
         	  $(data.shopCar).each(function(i, item) { 
                   listInfoString = listInfoString 	 +  
                   '<tr oiid="'+item.carId+'" class="cartProductItemTR">'+
					'<td>'+
						'<img selectit="false" oiid="'+item.carId+'" id="img'+item.carId+'"class="cartProductItemIfSelected"  onclick="itemSelect(\''+item.carId+'\')" src="img/site/cartNotSelected.png">'+
						'<a style="display:none" href="#nowhere"><img src="img/site/cartSelected.png"></a>'+
						'<img class="cartProductImg"  src="'+item.product.pimage+'">'+
					'</td>'+
					'<td>'+
						'<div class="cartProductLinkOutDiv">'+
						'	<a onclick="openProductInfo(  '+item.product.pid+');"class="cartProductLink">'+item.product.pname+'</a>'+
						'	<div class="cartProductLinkInnerDiv">'+
								'<img src="img/site/creditcard.png" title="支持信用卡支付">'+
								'<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">'+
								'<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">'+
							'</div>'+
						'</div>'+
						
					'</td>'+
					'<td>'+
						'<span class="cartProductItemOringalPrice">￥'+item.product.marketPrice+'</span>'+
						'<span  class="cartProductItemPromotionPrice">￥'+item.product.shopPrice+'</span>'+
						
					'</td>'+
					'<td>'+
					
					'	<div class="cartProductChangeNumberDiv">'+
					  
							'<span class="hidden orderItemPromotePrice "  pid="'+item.product.pid+'">'+item.product.shopPrice+'</span>'+
						'	<a  pid="'+item.product.pid+'" onclick="numberMinus('+item.product.pid+',\''+item.carId+'\')" class="numberMinus"  >-</a>'+
							'<input pid="'+item.product.pid+'" oiid="'+item.carId+'" onchange="changeNumber('+item.product.pid+',\''+item.carId+'\','+item.product.shopPrice+')" class="orderItemNumberSetting" autocomplete="off" value="'+item.number+'">'+
						'	<a   pid="'+item.product.pid+'" onclick="numberPlus('+item.product.pid+',\''+item.carId+'\')"class="numberPlus"  >+</a>'+
					'	</div>	'+				
					 
				'	 </td>'+
				'	<td >'+
				'		<span class="cartProductItemSmallSumPrice" oiid="'+item.carId+'" pid="'+item.product.pid+'" >'+
				'		￥ '+formatMoney(item.sumprice)+
				'		</span>'+
					
				'	</td>'+
				'	<td>'+
				'		<a class="deleteOrderItem" oiid="'+item.carId+'"  onclick="delShopCar(\''+item.carId+'\');">删除</a>'+
				'	</td>'+
				'</tr>'   ;
 
                  

                 });
         	  
    	        	 
         	  $("#carList").empty(); 
         	  $(listInfoString).appendTo($("#carList")); 
    	  }else if(data.result=="nologin"){
    		  alert("您还未登录！");
    		  window.location.href="signin.jsp"
    	  }
     	
     	  
      },
      error: function(err) {
        alert('连接错误');
      }

    });
}

 
</script>
<title>购物车</title>
<body>
  <%@include file="menu.jsp" %>
<div class="cartDiv">
	<div class="cartTitle pull-right">
		<span>已选商品  (不含运费)</span>
		<span class="cartTitlePrice">￥0.00</span>
		<button class="createOrderButton" disabled="disabled">结 算</button>
	</div>
	
	
	<div class="cartProductList">
		<table class="cartProductTable">
			<thead>
				<tr>
					<th class="selectAndImage">
							<img selectit="false" class="selectAllItem" src="img/site/cartNotSelected.png">				
					全选
					
					</th>
					<th>商品信息</th>
					<th>单价</th>
					<th>数量</th>
					<th width="120px">金额</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody id="carList">
			  
			 				
			</tbody>
		
		</table>
	</div>
	
	<div class="cartFoot">
		<img selectit="false" class="selectAllItem" src="img/site/cartNotSelected.png">
		<span>全选</span>
<!-- 		<a href="#">删除</a> -->
		
		<div class="pull-right">
			<span>已选商品 <span class="cartSumNumber" >0</span> 件</span>
			
			<span>合计 (不含运费): </span> 
			<span class="cartSumPrice" >￥0.00</span>
			<button class="createOrderButton" disabled="disabled" >结  算</button>
		</div>
		
	</div>
	
</div>
<div class="modal" id="deleteConfirmModal" tabindex="-1" role="dialog" >
	<div class="modal-dialog deleteConfirmModalDiv">
       <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">确认删除？</h4>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
            <button class="btn btn-primary deleteConfirmButton" id="submit" type="button">确认</button>
          </div>
        </div>
      </div>
	</div>
</body>