package com.littlecat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.littlecat.entity.HistoryRecord;
import com.littlecat.entity.Product;
import com.littlecat.entity.User;
import com.littlecat.service.CategoryService;
import com.littlecat.service.ProductService;
import com.littlecat.utils.UuidUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/product")
@Controller
public class ProductController {
	@Resource(name = "categoryServiceImpl")
	private CategoryService categoryService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;

	
	//查找商品分类列表
	@RequestMapping(value = "/findCategoryList", method = RequestMethod.POST)
	@ResponseBody
	public Object findCategoryList() {

		return categoryService.findCategoryList();

	}
   //首页显示的的商品
	@RequestMapping(value = "/indexProduct", method = RequestMethod.POST)
	@ResponseBody
	public Object indexProduct() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Product> hotProductList = productService.findHotProducts(1, 0, 12);

		map.put("hotList", hotProductList);

		return map;

	}
	//通过分类id 查询商品列表
	@RequestMapping(value = "/findProductListByCid", method = RequestMethod.POST)
	@ResponseBody
	public Object findProductListByCid(@RequestParam("cid") String cid, @RequestParam("start") Integer start,
			@RequestParam("rows") Integer rows, HttpServletRequest request, HttpServletResponse response) {
		// System.out.println(cid);
		Map<String, Object> map = new HashMap<String, Object>();

		if (start != null && start > 0 && cid != null && rows != null) {
			// 计算显示页面 当前页面减一 乘以显示行数
			int offset = (start - 1) * rows;
			List<Object> cl = productService.findProductListByCid(cid, offset, rows);
			int count = productService.productCount(cid);
			int sumPage = (count + rows - 1) / rows;
			map.put("list", cl);
			map.put("sumPage", sumPage); 
			return map; 
		} 
		map.put("result", "erro");
		return map;

	}
	
	
	
	
    //通过商品名称搜索商品列表 like 模糊查询
	@RequestMapping(value = "/findProductListByName", method = RequestMethod.POST)
	@ResponseBody
	public Object findProductListByName(@RequestParam("pname") String pname, @RequestParam("start") Integer start,
			@RequestParam("rows") Integer rows, HttpServletRequest request, HttpServletResponse response) {
		 
		Map<String, Object> map = new HashMap<String, Object>();

		if (start != null && start > 0 && pname != null && rows != null) {
			// 计算显示页面 当前页面减一 乘以显示行数
			int offset = (start - 1) * rows;
			List<Object> cl = productService.findProductListByName(pname, offset, rows);
			int count = productService.productCountByName(pname);
			int sumPage = (count + rows - 1) / rows;
			map.put("list", cl);
			map.put("sumPage", sumPage); 
			return map; 
		} 
		map.put("result", "erro");
		return map; 
	}
  // 通过商品id 查询商品  商品详细信息 若用户登录则记录浏览记录
	@RequestMapping(value = "/findProductById", method = RequestMethod.POST)
	@ResponseBody
	public Object findProductById(@RequestParam("pid") String pid, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pid != null) {
			Product product = productService.findProductById(pid);
			if (product != null) {
				map.put("product", product);
				HttpSession session = request.getSession();
				session.setAttribute("product", product);
				map.put("result", "success");
				User user = (User) session.getAttribute("user");
				// 当用户登录时 记录浏览记录
				if (user != null) {

					productService.addHistoryRecored(pid, user.getUid());
				}

				return map;
			}
		}
		map.put("result", "erro");
		return map;

	}
     //商品历史列表  通过用户ID查询
	@RequestMapping(value = "/historyList", method = RequestMethod.POST)
	@ResponseBody
	public Object findhistoryList(@RequestParam("start") Integer start, @RequestParam("rows") Integer rows,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");
		if (user != null) {
			if (start != null && start > 0 && rows != null) {
				// 计算显示页面 当前页面减一 乘以显示行数
				int offset = (start - 1) * rows;
				List<HistoryRecord> cl = productService.findByUid(user.getUid(), offset, rows);
				int count = productService.hrCount(user.getUid());
				int sumPage = (count + rows - 1) / rows;
				map.put("list", cl);
				map.put("sumPage", sumPage);
				map.put("result", "success");
				return map;

			} else {
				map.put("result", "erro");
				return map;
			}

		} else {
			map.put("result", "nologin");
			return map;
		}

	}
   //删除商品历史信息
	@RequestMapping(value = "/delHistory", method = RequestMethod.POST)
	@ResponseBody
	public Object delHistory(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");
		if (user != null) {
			if (productService.delHistory(user.getUid()) > 0) {
				map.put("result", "success");
				return map;
			}
			map.put("result", "success");
			return map;

		} else {
			map.put("result", "nologin");
			return map;
		}

	}

}
