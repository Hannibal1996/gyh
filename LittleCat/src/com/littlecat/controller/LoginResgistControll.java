package com.littlecat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 
import com.littlecat.entity.User;
import com.littlecat.service.UserService;
import com.littlecat.utils.MD5Utils;
import com.littlecat.utils.MailUtils;
import com.littlecat.utils.UuidUtil;
import com.littlecat.utils.VerifyCodeUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/loginResgist")
@Controller
public class LoginResgistControll {
	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	//用户名验证
	@RequestMapping(value = "/userNameCheck", method = RequestMethod.POST)
	@ResponseBody
	public Object userNameCheck(@RequestParam("username") String username) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.userNameCheck(username);
		//返回是否存在userName
		map.put("result", flag);
		return map;

	}
	//邮箱验证
	@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
	@ResponseBody
	public Object emailCheck(@RequestParam("email") String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.emailCheck(email);
		//返回是否存邮箱
		map.put("result", flag);
		return map;

	}
	//注册
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Object addUser(@Valid @RequestBody User user, BindingResult bindResult, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "";
		// System.out.println(user.getBirthday());
		HttpSession sesion = request.getSession(true); 
		// 判空   使用 hibernate.validator 验证实体类属性
		if (bindResult.hasErrors()) { 
			List<ObjectError> errorList = bindResult.getAllErrors();
			for (ObjectError error : errorList) {
				result = error.getDefaultMessage();
				System.out.println(error.getDefaultMessage());
			}
			map.put("result", "noBlank");
			return map;
			
			//用户名是否存在
		} else  if(!userService.userNameCheck(user.getName())) {
				
				String activeCode = UuidUtil.get32UUID();
				user.setCode(activeCode);
				//判断插入成功
				if (userService.addUser(user) > 0) {
					try {// 添加成功

						String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
								+ "<a href='http://localhost:8080/LittleCat/loginResgist/active?activeCode="
								+ activeCode + "'>"
								+ "http://localhost7:8080/LittleCat/loginResgist/active?activeCode=" + activeCode
								+ "</a>";

						map.put("result", "success");
						
						//发送页面
						MailUtils.sendMail(user.getEmail(), emailMsg);

					} catch (AddressException e) {
						 
					} catch (MessagingException e) {
						 
					}
					return map;
				} else {
					// 修改失败
					map.put("result", "err");
					return map;
				}

			} else {
				//用户名存在
				map.put("result", "isExistName");
				return map;
			}

		 

	}
	//邮箱激活
	@RequestMapping(value = "/active", method = RequestMethod.GET) 
	public void activeEmail(@RequestParam("activeCode") String activeCode, HttpServletRequest request,
			HttpServletResponse response) { 
		int x = userService.activeEmailBycode(activeCode);
		try {	if (x == 0) {
			response.sendRedirect("http://localhost:8080/LittleCat/activeErro.jsp");
		
		} else {  
			response.sendRedirect("http://localhost:8080/LittleCat/signin.jsp"); 
		}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	//登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(  String username,   String pwd,   String verifyCode,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		String result = "";
		if(username!=null&&pwd!=null&&verifyCode!=null){
			HttpSession session = request.getSession(true);
			String code=(String) session.getAttribute("verifyCode");
			System.out.println(code);
			System.out.println(verifyCode);  
			if(verifyCode.equalsIgnoreCase(code)){ 
				User user = userService.loginCheck(username );  
				if (user != null) {
					if (user.getPassword().equals(MD5Utils.md5(pwd))) {
						result = "success";
						if(user.getState()!=0){
							 session.removeAttribute("user");	
							 session.setAttribute("user", user);
								map.put("user", user); 
								} 
					} else {
						result = "pwdErr";
					}
				} else {
					result = "NoUserName";
				}	
			}else{
				result = "ErrVerifyCode";	
			}
			
		}
		
		map.put("result", result);
		return map;

	}
   //自动登录
	@RequestMapping(value = "/autoLogin", method = RequestMethod.POST)
	@ResponseBody
	public Object autoLogin(  String username,   String pwd,   
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
	 
		String result = "";
		if(username!=null&&pwd!=null  ){
			HttpSession session = request.getSession(true); 
				User user = userService.loginCheck(username );  
				if (user != null) {
					if (user.getPassword().equals(pwd )) {
						result = "success";
						session.removeAttribute("user");	
				 
						session.setAttribute("user", user);
						map.put("user", user);
						

					} else {
						result = "pwdErr";
					}
				} else {
					result = "NoUserName";
				}	
			 
			
		}
		
		map.put("result", result);
		return map;

	}
//验证码生成
	@RequestMapping(value = "/authImage", method = RequestMethod.GET) 
	public void Verifycode(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		// 删除以前的
		session.removeAttribute("verifyCode");
		session.setAttribute("verifyCode", verifyCode.toLowerCase());
		// 生成图片
		int w = 100, h = 30;
		try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//退出
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		// 删除以前的
	System.out.println("logout");
		session.removeAttribute("user");
		try {
			response.sendRedirect("../index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

}
