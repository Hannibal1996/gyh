package com.igeek.shop.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.igeek.common.utils.CommonUtils;
import com.igeek.common.utils.MailUtils;
import com.igeek.shop.entity.User;
import com.igeek.shop.service.UserService;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		User user=new User();
		boolean flag;
		ConvertUtils.register(new Converter() {
			
			@Override
			public Object convert(Class calzz, Object value) {
				// TODO Auto-generated method stub
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
				Date desc=null;
				try {
					desc=sf.parse((String)value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return desc;
			}
		}, Date.class);
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.setUid(CommonUtils.getUUID());
		user.setCode(CommonUtils.getUUID());
		String activeCode=user.getCode();
		UserService us=new UserService();
		flag=us.regist(user);
		if(flag==true) {
			String emailMsg="恭喜您，注册成功！请点击下面验证码激活账户。<br>"
					+"<a href='http://localhost:8080/IGeekShop/active?activeCode="+activeCode+"'>"
					+ "http://localhost:8080/IGeekShop/active?activeCode="+activeCode+" </a>";
			 try {
				MailUtils.sendMail(user.getEmail(),emailMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else {
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
