package com.littlecat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleDatetimeExpr;
import com.littlecat.entity.Orderitem;
import com.littlecat.entity.Orders;
import com.littlecat.entity.ShopCar;
import com.littlecat.entity.User;
import com.littlecat.service.OrdersService;
import com.littlecat.service.ProductService;
import com.littlecat.service.ShopCarService;
import com.littlecat.utils.UuidUtil;
import com.sun.org.apache.xpath.internal.operations.Or;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/shopcar")
@Controller
public class ShopCarControlle {
	@Resource(name = "shopCarServiceImpl")
	private ShopCarService shopCarService;
	@Resource(name = "ordersServiceimpl")
	private OrdersService ordersService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;
/*
 * 添加商品到购物车
 * 判断用户是否登录
 * 判断接收购物车数据是否为空
 * 判断购物车是否有此商品
 * 有则着执行更新 将数据库和接收的商品数量相加
 * 无则执行添加操作
 * 返回结果状态
 */
	@RequestMapping(value = "/addToShopCar", method = RequestMethod.POST)
	@ResponseBody
	public Object addToShopCar(@RequestBody ShopCar shopCar, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");
		//判断是否登录
		if (user != null) {
			// 判断接受到商品信息是否为空
			if (shopCar.getPid() == null || shopCar.getSumprice() == null || shopCar.getNumber() == null) {  
				map.put("result", "noBlank");
				return map;
			} else {
				ShopCar sc = shopCarService.findShopCarByPIDUID(user.getUid(), shopCar.getPid());
				// 判断购物车是否有此商品
				if (sc == null) {
					// 没有此商品执行新增
					Date time = new java.sql.Date(new java.util.Date().getTime());
					// 生成id 设置时间
					shopCar.setCarId(UuidUtil.get32UUID());
					shopCar.setUid(user.getUid());
					shopCar.setTime(time);
					// 判断添加是否为空
					if (shopCarService.addProductToShopCar(shopCar) > 0) {
						map.put("result", "success");
						return map;
					} else {
						map.put("result", "err");
						return map;
					}
				} else {
					// 购物车由此商品  执行 更新方法
					sc.setNumber(shopCar.getNumber() + sc.getNumber());
					sc.setSumprice(shopCar.getSumprice());
					if (shopCarService.updataProductToShopCar(sc) > 0) {
						map.put("result", "success");
						return map;
					} else {
						map.put("result", "err");
						return map;
					} 
				} 
			}

		} else {
			map.put("result", "nologin");
			return map;
		}

	}
   /*
    * 获取用户购物车列表
    * 判断用户是否登录session
    * 根据用户id查询
    * 返回商品数据
    * 
	*/
	@RequestMapping(value = "/findShopCarList", method = RequestMethod.POST)
	@ResponseBody
	public Object findShopCarList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		HttpSession sesion = request.getSession(true); 
		User user = (User) sesion.getAttribute("user");
		//
		if (user != null) {
			List<ShopCar> shopCar = shopCarService.findShopCarByUID(user.getUid());
			map.put("shopCar", shopCar);
			map.put("result", "success");
			return map;
		} else {
			map.put("result", "nologin");
			return map;
		}
	}
/*
 * 更新购物车商品数量
 * 判断是否登录
 * 判断接收数据是否为空
 * 更新购物车商品数据
 */
	@RequestMapping(value = "/updateNumber", method = RequestMethod.POST)
	@ResponseBody
	public Object updateNumber(@RequestBody ShopCar shopCar, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		HttpSession sesion = request.getSession(true); 
		User user = (User) sesion.getAttribute("user");
		if (user != null) {
			// 判空
			if (shopCar.getCarId() == null || shopCar.getPid() == null || shopCar.getSumprice() == null
					|| shopCar.getNumber() == null) {

				map.put("result", "noBlank");
				return map;
			} else {
				// 判断更新是否成功
				if (shopCarService.updataProductToShopCar(shopCar) > 0) {
					map.put("result", "success");
					return map;
				} else {
					map.put("result", "err");
					return map;
				}
			}

		} else {
			map.put("result", "nologin");
			return map;
		}

	}
/*
 *删除购物车商品
 *判断是否登录
 *判断接收数据是否为空
 *执行删除
 */
	@RequestMapping(value = "/delFromShopCar", method = RequestMethod.POST)
	@ResponseBody
	public Object delFromShopCar(@RequestParam("carId") String carId, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");
		if (user != null) {
			// 判空
			if (carId == null || carId == "") {

				map.put("result", "noBlank");
				return map;
			} else {
				// 判断更新是否成功
				if (shopCarService.delProductFromShopCar(carId, user.getUid()) > 0) {
					map.put("result", "success");
					return map;
				} else {
					map.put("result", "err");
					return map;
				}
			}

		} else {
			map.put("result", "nologin");
			return map;
		}

	}

	@RequestMapping(value = "/redayBuy", method = RequestMethod.POST)
	@ResponseBody
	public Object redayBuy(@RequestParam("params") String idArray, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		HttpSession sesion = request.getSession(true);
		System.out.println(idArray);
		User user = (User) sesion.getAttribute("user");
		if (user != null) {
			// 判空
			if (idArray == null) {

				map.put("result", "noBlank");
				return map;
			} else {
				String[] strs = idArray.split(",");
				System.out.println(strs[0]);
				List<String> arrList = Arrays.asList(strs);
				List<ShopCar> shopCarList = shopCarService.findByPKList(arrList);
				map.put("result", "success");
				map.put("list", shopCarList);
				double sum = 0;
				for (ShopCar e : shopCarList) {
					sum += e.getSumprice();
				}
				sesion.removeAttribute("total");
				sesion.removeAttribute("readybuy");
				sesion.setAttribute("total", sum);
				sesion.setAttribute("readybuy", shopCarList);
				return map;
			}

		} else {
			map.put("result", "nologin");
			return map;
		}

	}

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object addOrder(@RequestBody Orders orders, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		HttpSession sesion = request.getSession(true);

		User user = (User) sesion.getAttribute("user");
		if (user != null) {
			// 判空
			if (orders.getAddress() == null || orders.getName() == null || orders.getTelephone() == null) {

				map.put("result", "noBlank");
				return map;
			} else {
				Double total = (Double) sesion.getAttribute("total");
				List<ShopCar> shopCarlist = (List<ShopCar>) sesion.getAttribute("readybuy");
				orders.setState(0);
				orders.setTotal(total);
				Date date = new Date();
				SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date2 = temp.format(date);
				Date date3 = temp.parse(date2);
				orders.setOrdertime(date3);
				String oId = UuidUtil.get32UUID();
				orders.setOid(oId);
				orders.setUid(user.getUid());
			 
				int x = ordersService.addOrders(orders);
				for (ShopCar e : shopCarlist) {
					Orderitem orderitem = new Orderitem();
					orderitem.setCount(e.getNumber());
					orderitem.setOid(oId);
					orderitem.setPid(e.getPid());
					orderitem.setSubtotal(e.getSumprice());
					orderitem.setItemid(UuidUtil.get32UUID());
					if (ordersService.addOrdersItem(orderitem) > 0) {
						shopCarService.delShopCar(e.getCarId());
					}
				}
				if (x > 0) {
					map.put("result", "success");
					map.put("orders", orders);
					sesion.removeAttribute("orders");
					sesion.setAttribute("orders", orders);
					sesion.removeAttribute("total");
					sesion.removeAttribute("readybuy");
				} else {
					map.put("result", "erro");
				}
				return map;
			}

		} else {
			map.put("result", "nologin");
			return map;
		}

	}
	@RequestMapping(value = "/payOrders", method = RequestMethod.POST)
	@ResponseBody
	public Object payOrders(@RequestParam("oid") String oid,HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");
		if (user != null) {
			// 判空
			if (oid==null ) {

				map.put("result", "noBlank");
				return map;
			}else{
				Orders orders=ordersService.findOrdersByOID(oid);
				sesion.removeAttribute("orders");
				sesion.setAttribute("orders", orders);
				map.put("result", "success");
				return map;
			}
		} else {
			map.put("result", "nologin");
			return map;
		}
	}
	@RequestMapping(value = "/findAllOrders", method = RequestMethod.GET)
	@ResponseBody
	public Object findAllOrders(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");

		try {
			if (user != null) {
				List<Orders> orderList = ordersService.findAllOrders(user.getUid());

				for (Orders e : orderList) {
					List<Orderitem> oiList = e.getOrderitem();
					for (Orderitem oi : oiList) {

						oi.setProduct(productService.findProductById(oi.getPid()));
					}
				}
				sesion.setAttribute("orderList", orderList);
				response.sendRedirect("../boughtPage.jsp");
				map.put("orderList", orderList);
			} else {

				response.sendRedirect("../signin.jsp");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/findNoPay", method = RequestMethod.GET)
	@ResponseBody
	public Object findNoPay(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");

		try {
			if (user != null) {
				List<Orders> orderList = ordersService.findNoPay(user.getUid());

				for (Orders e : orderList) {
					List<Orderitem> oiList = e.getOrderitem();
					for (Orderitem oi : oiList) {

						oi.setProduct(productService.findProductById(oi.getPid()));
					}
				}
				sesion.setAttribute("orderList", orderList);
			 response.sendRedirect("../boughtPageNopay.jsp");
			//	map.put("orderList", orderList);
			} else {

				response.sendRedirect("../signin.jsp");

			}
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/delOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object delOrder(@RequestParam("oid") String oid, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession sesion = request.getSession(true);
		User user = (User) sesion.getAttribute("user");
		if (user != null) {

			if (oid != null && oid != "") {
				if (ordersService.delOrder(oid) > 0) {
					map.put("result", "success");

				} else {
					map.put("result", "erro");
				}
			}

		}else{
			map.put("result", "nologin");
		}

		return map;
	}

}
