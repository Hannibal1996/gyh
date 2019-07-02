<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link
 
	rel="stylesheet">
 
<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script src="js/acommon.js" type="text/javascript"></script>
	 
		<script src="js/jquery.pagination.js" type="text/javascript"></script>
		<link rel="stylesheet" href="js/pagination.css" type="text/css" />
 <style type="text/css">
 .signup-footer {
    border-top: 1px solid #ddd;
    margin-top: 30px;
    padding-top: 10px;
    font-size: 12px;
}
.signup-footer .pull-left,
 .signup-footer .pull-right {
        float: none !important;
        text-align: center;
    }
 
 </style>
<script type="text/javascript">
window.onload = function() {
	 var flag=sessionStorage.getItem("flag");
	 //alert(flag);
	 if(flag!="1"){
		 loginState();
	 }
	 

}  
	var rows = 12; //每页行数
	var nowPage = 1; //当前页数
	var totalPage; //总页数 queryProductList 方法执行后设置值
	var listlengh; //条目长度
	var page1 = sessionStorage.getItem("page");
 
	if (page1 != null&&page1!="undefined") {
		nowPage = page1;
	}
 
	
	$(document).ready(function() {

		//首次打开 初始化页面
		queryProductList(nowPage);
	 

	 
		//page(nowPage, totalPage);
		 
		page2(nowPage, totalPage);
		//历史记录
		//queryHistoryList();

	});
 
	page2 = function(cp, tp) {
	$('#pagination2').pagination({
	    pageCount: tp,
	    current:cp, 
	    coping: true,
	    jump: true,
	    homePage: '首页',
	    endPage: '末页',
	    prevContent: '上页',
	    nextContent: '下页',
	    callback: function (api) {
	    	nowPage = api.getCurrent();
	    	queryProductList(api.getCurrent());
	    }
	})
	}
	//查询列表数据
	queryProductList = function(start) {
		var jsonStr = {
		 
			"start" : start, //当前页数
			"rows" : rows,
		}; 
		$ .ajax({   
			       url : 'product/historyList',
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
						if(data.result=="success"){
							totalPage = data.sumPage;
							var listInfoString = '';
							listlengh = 0; 
					

				           $(data.list).each(
											function(i, item) {
												var name = cutString(item.product.pname, 24);
												listlengh++;
												listInfoString = listInfoString+										   
												  '  <div class="col-md-3 column">'+
										   			' <a onclick="openProductInfo(\''+item.product.pid+'\',\''+start+'\');" >'+
										             '<img src="'+item.product.pimage+'"  style="width:240px;height:200px" class="img-responsive img-thumbnail image_css"/></a>'+

										   			'<br><br><br>	<br><br><br><div class="carousel-caption">'+
										   					 	'	<h5 style="color:#000;font-size:12px" >'+name+'<br>'+
										   							'<font class="center" color="red">￥'+item.product.shopPrice+'</font> '+
										   							'</h5> '+
										   						'</div>'+
										   	'	</div>';

											});
							//数据显示到页面
							$("#listDiv").empty();

							$(listInfoString).appendTo($("#listDiv"));
						}else if(data.result=="nologin"){
							 alert("您还未登录，请登录！")
					    	   window.location.href = 'signin.jsp';
							
						}
						
					},
					error : function(err) {
						alert('请求失败 ');
					}
				});
	}
	 
	 
	delHistory = function(start) {
	 
		$ .ajax({   
			       url : 'product/delHistory',
					async : false,
					type : "POST",
				 
					dataType : 'json',
					crossDomain : true,
					headers : {
						"X-Requested-With" : "XMLHttpRequest"
					},
					xhrFields : {
						withCredentials : true
					},
					success : function(data) {
						if(data.result=="success"){
							 alert("删除成功！");
							  window.location.href = 'index.jsp';
						}else if(data.result=="nologin"){
							 alert("您还未登录，请登录！");
					    	   window.location.href = 'signin.jsp';
							
						}
						
					},
					error : function(err) {
						alert('请求失败 ');
					}
				});
	}
	 

</script>
</script>
</head>

<body>
	<%@include file="menu.jsp"%>
	    
	<div class="container">
	<div id="category" style="background-color:#fcfafa; height: 35px;">
       
        <a href="#"class="pull-right" onclick="delHistory();">一键清空</a>  
     </div>
		<div id="listDiv" class="row clearfix">
		 
		</div>	 
 
	<div id="pagination2"   style="text-align: center;" > </div>
	</div>
		   <div class="signup-footer">
            <div style="color: #000"class="pull-left">
                &copy; 2018. All Rights Reserved. TMall corporation.
            </div>
            <div style="color: #000" class="pull-right"> 
                Created By:wit
            </div>
        </div>
			
 
   
</body>
</html>
