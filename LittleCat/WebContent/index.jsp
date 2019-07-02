<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>小天猫</title>
     
<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    	<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script src="js/acommon.js" type="text/javascript"></script>
<script type="text/javascript">

window.onload=function(){
	 findCategoryList();
	  indexProduct();

	 var flag=sessionStorage.getItem("flag");
	 //alert(flag);
	 if(flag!="1"){
		 loginState();
	 }
		
}

findCategoryList=function(){	  
    $.ajax({
      url:  'product/findCategoryList ', 
      type: "POST",
      contentType: 'application/json',
      dataType: 'json',
      
      success: function(data) {
     	 var listInfoString = '<a href="#" class="list-group-item active">分类</a>';
     	  $(data).each(function(i, item) {
             
               listInfoString = listInfoString +
              
			'	<div class="list-group-item">'+
				'	<a onclick="openList(\''+item.cid+'\',\''+item.cname+'\');">'+item.cname+'</a> '+
			'	</div>';

             });
     	  $("#divCategory").empty();

           
 $(listInfoString).appendTo($("#divCategory"));
      
      },
      error: function(err) {
        alert('连接错误');
      }

    });
}

indexProduct=function(){	 
    $.ajax({
      url:  'product/indexProduct', 
      type: "POST",
      contentType: 'application/json',
      dataType: 'json',
      
      success: function(data) {
     	 var listInfoString = '' 
     	 ;
     	  $(data.hotList).each(function(i, item) {
     		   var name=cutString(item.pname,28);
               listInfoString = listInfoString +
             '  <div class="col-md-3 column">'+
   			' <a >'+
             '<img src="'+item.pimage+'" onclick="openProductInfo(  '+item.pid+')"; style="width:240px;height:200px" class="img-responsive img-thumbnail image_css"/></a>'+

   			'<br><br><br>	<br><br><br><div  onclick="openProductInfo(  '+item.pid+')" class="carousel-caption">'+
   					 	'	<h5 style="color:#000;font-size:12px" >'+name+'<br>'+
   							'<font class="center" color="red">￥'+item.shopPrice+'</font> '+
   							'</h5> '+
   						'</div>'+
   	'	</div>'
              ;

             });
     	 
	        	 
 
     	  $("#hotDiv").empty(); 
     	  $(listInfoString).appendTo($("#hotDiv"));
     	 
      },
      error: function(err) {
        alert('连接错误');
      }

    });
}

openList=function( cid,cname){
	 sessionStorage.removeItem("cid")
	 sessionStorage.setItem("cid", cid);
	 sessionStorage.removeItem("cname")
	 sessionStorage.setItem("cname", cname);
	 window.location.href="receiveProduct.jsp";
	 
}
</script>
  </head>
 
  <body>
  <%@include file="menu.jsp" %>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">
			<div id="divCategory" class="list-group">
				 
			</div>
		</div>
		<div class="col-md-10 column">
			<div class="carousel slide" id="carousel-205265">
				<ol class="carousel-indicators">
				<c:forEach var="i" begin="0" end="5" >
					<li data-slide-to="${i }" data-target="#carousel-205265"></li>
				</c:forEach>
					<li data-slide-to="6" data-target="#carousel-205265" class="active"></li>
				</ol>
				<div class="carousel-inner">
					<c:forEach var ="j" begin="0" end="5">
						<div class="item">
							<a href=""><img src="images/first/${j }.jpg"  style="width:100%;height:600px" class="img-responsive img-thumbnail"/></a>
							<div class="carousel-caption">
								<h1>
									<font color="blue"></font>
								</h1>
								<h4>
									<font color="red"></font>
								</h4>
							</div>
						</div>
					</c:forEach>
					<div class="item active">
						<img alt="" src="images/first/1.jpg" style="width:100%;height:600px" class="img-responsive img-thumbnail image_css"/>
						<div class="carousel-caption">
							<h4>
								
							</h4>
						</div>
					</div>
				</div> <a class="left carousel-control" href="#carousel-205265" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-205265" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
		</div>
		
	</div>
	<div id="hotDiv" maiclass="row clearfix">
	 
 
	</div>
	
</div>

 </body>
</html>
