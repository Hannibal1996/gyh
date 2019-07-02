 function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
 document.cookie = cname + "=" + cvalue + "; " + expires+"; path=/"   //这个很重要代表在那个层级下可以访问cookie
    
}
 
function logout(){
	 
	setCookie("username",0,-10);
	setCookie("password",0,-10);
	 
	 
}
function getCookie(cname)
{
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  }
  return "";
}
//自动登录检测
function loginState(){
	var cusername=getCookie("username");
	var cpwd=getCookie("password");
	if(cusername!=""&&cpwd!=""){
		var jsonStr = {
				"username": cusername,
				"pwd": cpwd 
			 
			 		};   
		   $.ajax({
		     url:  'loginResgist/autoLogin',
		     data: jsonStr,
		     type: "POST", 
		     dataType: 'json', 
		     success: function(data) {
		       if ("success" == data.result) {  
		    		   window.location.href = 'index.jsp'; 
		    	  sessionStorage.setItem('flag', "1");
		       }else if ("pwdErr" == data.result) {
		    	   alert('密码错误!');
		    	 
		       } else if ("NoUserName" == data.result) {
		    	   alert('用户名不存在!');
		    	 
		       } 
		        
		     },
		     error: function(err) {
		       alert('连接错误');
		     }

		   });
	}
}
//截取字符长度
function cutString(str, len) {
	//length属性读出来的汉字长度为1  
	if (str.length * 2 <= len) {
		return str;
	}
	var strlen = 0;
	var s = "";
	for (var i = 0; i < str.length; i++) {
		s = s + str.charAt(i);
		if (str.charCodeAt(i) > 128) {
			strlen = strlen + 2;
			if (strlen >= len) {
				return s.substring(0, s.length - 1) + "...";
			}

		} else {
			strlen = strlen + 1;

			if (strlen >= len) {

				return s.substring(0, s.length - 2) + "..."; 
			}

		} 
	} 
	return s;

}
openProductInfo = function(PID, page) {
	sessionStorage.removeItem("pid")
	sessionStorage.setItem("pid", PID);
	sessionStorage.removeItem("page")
	sessionStorage.setItem("page", page);
	window.location.href = "details.jsp";
}

payOrders= function(oid){ 
	  
	var jsonStr = {
		"oid": oid 
	 		};  
   $.ajax({
     url:  'shopcar/payOrders',
     data: jsonStr,
     type: "POST",
    
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
	    	 
       }  	
     },
     error: function(err) {
       alert('连接错误');
     }

   });
	
}
