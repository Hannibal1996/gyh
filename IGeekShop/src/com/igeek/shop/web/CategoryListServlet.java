package com.igeek.shop.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.igeek.shop.entity.Category;
import com.igeek.shop.service.ProductService;

/**
 * Servlet implementation class CategoryListServlet
 */
@WebServlet("/categoryList")
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ProductService ps=new ProductService();
		//
		List<Category> categoryList = ps.findCategoryList();
		req.setAttribute("categoryList", categoryList);
		
		 Gson gson = new Gson();
		 String json= gson.toJson(categoryList);

		 resp.setContentType("text/html;charset=utf-8");
		 resp.getWriter().write(json);

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}
       
   
	
}
