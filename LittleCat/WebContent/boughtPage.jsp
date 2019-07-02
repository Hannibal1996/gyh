 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	 <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<link href="css/fore/style.css" rel="stylesheet">

 	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script src="js/acommon.js" type="text/javascript"></script>
 <script src="js/jquery.validate.min.js"></script>   
<script>
var deleteOrder = false;
var deleteOrderid = 0;

$(function(){
	$("a[orderStatus]").click(function(){
		var orderStatus = $(this).attr("orderStatus");
		if('all'==orderStatus){
			$("table[orderStatus]").show();	
		}
		else{
			$("table[orderStatus]").hide();
			$("table[orderStatus="+orderStatus+"]").show();			
		}
		
		$("div.orderType div").removeClass("selectedOrderType");
		$(this).parent("div").addClass("selectedOrderType");
	});
	
	$("a.deleteOrderLink").click(function(){
		deleteOrderid = $(this).attr("oid");
		deleteOrder = false;
		$("#deleteConfirmModal").modal("show");
	});
	
	$("button.deleteConfirmButton").click(function(){
		deleteOrder = true;
		$("#deleteConfirmModal").modal('hide');
	});	
	
	$('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		if(deleteOrder){
			var page="foredeleteOrder";
			var page="shopcar/delOrder";
			$.post(
				    page,
				    {"oid":deleteOrderid},
				    function(data){
						if("success"==data.result){
							var hash = window.location.hash;
							if(hash.substring(1)=="all"){
								location.href="shopcar/findAllOrders#all";
							}else if(hash.substring(1)=="nopay"){
								location.href="shopcar/findNoPay#nopay";
							}
							
						}
						else if("nologin"==data.result){
							location.href="login.jsp";
						}
				    }
				);
	 
			
		}
	})		
	
	$(".ask2delivery").click(function(){
		var link = $(this).attr("link");
		$(this).hide();
		page = link;
		$.ajax({
			   url: page,
			   success: function(result){
				alert("卖家已秒发，刷新当前页面，即可进行确认收货")
			   }
			});
		
	});
});
delOrder= function(oid){
	deleteOrderid = oid ;
	deleteOrder = false;
	$("#deleteConfirmModal").modal("show");
}


</script>

<body>
<%@include file="menu.jsp" %>
<div class="boughtDiv">
	<div class="orderType">
		<div class="selectedOrderType"><a orderStatus="all" href="shopcar/findAllOrders#all">所有订单</a></div>
		<div><a  orderStatus="waitPay" href="shopcar/findNoPay#nopay">待付款</a></div>
 
		<div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
	</div>
	<div style="clear:both"></div>
	<div class="orderListTitle">
		<table class="orderListTitleTable">
			<tr>
				<td>宝贝</td>
				<td width="100px">单价</td>
				<td width="200px">数量</td>
				<td width="120px">实付款</td>
				<td width="100px">交易操作</td>
			</tr>
		</table>
	
	</div>
	
	<div class="orderListItem">
		<c:forEach items="${orderList}" var="o">
			<table class="orderListItemTable" orderStatus="${o.state}" oid="${o.oid}">
				<tr class="orderListItemFirstTR">
					<td colspan="2">
					<b><fmt:formatDate value="${o.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/></b> 
					<span>订单号: ${o.oid} 
					</span>
					</td>
					<td  colspan="2"><img width="13px" src="img/site/orderItemTmall.png">天猫商场</td>
					<td colspan="1">
						<a class="wangwanglink" href="#nowhere">
							<div class="orderItemWangWangGif"></div>
						</a>
						
					</td>
					<td class="orderItemDeleteTD">
						<a class="deleteOrderLink" oid="${o.oid}"  onclick="delOrder('${o.oid}');">
							<span  class="orderListItemDelete glyphicon glyphicon-trash"></span>
						</a>
						
					</td>
				</tr>
												<c:set value="0" var="sum" />
												<c:forEach items="${o.orderitem}" var="oi" varStatus="st">
															<c:set value="${sum+oi.count}" var="sum" />
															</c:forEach>		
				<c:forEach items="${o.orderitem}" var="oi" varStatus="st">
															 
					<tr class="orderItemProductInfoPartTR" >
						<td class="orderItemProductInfoPartTD"><img width="80" height="80" src=" ${oi.product.pimage}"></td>
						<td class="orderItemProductInfoPartTD">
							<div class="orderListItemProductLinkOutDiv">
								<a onclick="openProductInfo(${oi.product.pid} );">${oi.product.pname}</a>
								<div class="orderListItemProductLinkInnerDiv">
											<img src="img/site/creditcard.png" title="支持信用卡支付">
											<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
											<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">						
								</div>
							</div>
						</td>
						<td  class="orderItemProductInfoPartTD" width="100px">
						
							<div class="orderListItemProductOriginalPrice"> <fmt:formatNumber type="number" value="${oi.product.marketPrice}" minFractionDigits="2"/></div>
							<div class="orderListItemProductPrice"> <fmt:formatNumber type="number" value="${oi.product.shopPrice}" minFractionDigits="2"/></div>
		
		
						</td>
						<c:if test="${st.count==1}">
						 											
							<td valign="top" rowspan="${fn:length(o.orderitem)}" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
								<span class="orderListItemNumber">${sum }</span>
							</td>
							<td valign="top" rowspan="${fn:length(o.orderitem)}" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
								<div class="orderListItemProductRealPrice"> <fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${o.total}"/></div>
								<div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
							</td>
							<td valign="top" rowspan="${fn:length(o.orderitem)}" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
							 
								<c:if test="${o.state==0 }">
									<a  onclick="payOrders('${o.oid}')" >
										<button class="orderListItemConfirm">付款</button>
									</a>								
								</c:if>
										<c:if test="${o.state==1 }">
									<a  >
										<button class="orderListItemConfirm">已完成</button>
									</a>								
								</c:if>
							 
							 
							</td>						
						</c:if>
				</c:forEach>		
				
			</table>
		</c:forEach>
		
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