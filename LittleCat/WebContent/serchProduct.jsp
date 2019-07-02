<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
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

	var pname = sessionStorage.getItem("pname");
	if (pname != null & pname != "") {
		//alert(cid);

	} else {
		window.location.href = "index.jsp";
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
		var categ=  '<a href="index.jsp" class="amenu">首页</a>'+
        ' <a   class="amenu">搜索结果</a> ';
    	$("#category").empty();//清空

		$(categ).appendTo($("#category"));//注入
		//page(nowPage, totalPage);
		page2(nowPage, totalPage);
		//历史记录
	 

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
			"pname" : pname,
			"start" : start, //当前页数
			"rows" : rows,
		};

		$ .ajax({   //通过ajax发送post请求
			       url : 'product/findProductListByName',
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
						totalPage = data.sumPage;
						var listInfoString = '';
						 
			           $(data.list).each(
										function(i, item) {
											var name = cutString(item.pname, 24);//设置显示字符长度，最长为24个字符串
											//拼接信息
											listlengh++;
											listInfoString = listInfoString+										   
											  '  <div class="col-md-3 column">'+
									   			' <a onclick="openProductInfo(\''+item.pid+'\',\''+start+'\');" >'+
									             '<img src="'+item.pimage+'"  style="width:240px;height:200px" class="img-responsive img-thumbnail image_css"/></a>'+

									   			'<br><br><br>	<br><br><br><div class="carousel-caption">'+
									   					 	'	<h5 style="color:#000;font-size:12px" >'+name+'<br>'+
									   							'<font class="center" color="red">￥'+item.shopPrice+'</font> '+
									   							'</h5> '+
									   						'</div>'+
									   	'	</div>';

										});
						//数据显示到页面
						$("#listDiv").empty();
						  if (listInfoString == '' || listInfoString == null) {
		                      listInfoString =  '<div  style ="margin-top:20;text-align: center;">'+
		                      '搜索不到！'+
		                      
		                      '<div>'
		                      ;
		                        

		                    }
						$(listInfoString).appendTo($("#listDiv"));//注入到页面中
					},
					error : function(err) {
						alert('请求失败 ');
					}
				});
	}
	 

</script>
</head>

<body>
	<%@include file="menu.jsp"%>
	    
	<div class="container">
	<div id="category" style="background-color:#fcfafa; height: 30px;">
           
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
