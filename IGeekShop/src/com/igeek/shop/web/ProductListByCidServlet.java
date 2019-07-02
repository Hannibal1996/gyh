package com.igeek.shop.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igeek.shop.entity.PageBean;
import com.igeek.shop.entity.Product;
import com.igeek.shop.service.ProductService;

/**
 * Servlet implementation class ProductListByCidServlet
 */
@WebServlet("/productListByCid")
public class ProductListByCidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String cid=request.getParameter("cid");
		String page=request.getParameter("currentPage");
		int currentPage=1;
		if(page!=null) {
			currentPage=Integer.parseInt(page);
		}
		int currentCount=12;
		ProductService ps=new ProductService();
		PageBean<Product> pageBean=ps.findProductListByCid(cid,currentPage,currentCount);
		request.setAttribute("cid", cid);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
