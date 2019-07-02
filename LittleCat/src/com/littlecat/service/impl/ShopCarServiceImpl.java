package com.littlecat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.littlecat.dao.ShopCarMapper;
import com.littlecat.entity.ShopCar;
import com.littlecat.service.ShopCarService;

 
@Service("shopCarServiceImpl")
public class ShopCarServiceImpl implements ShopCarService {
	@Resource(name = "shopCarDao")
	private ShopCarMapper shopCarDao;
	@Override
	public int addProductToShopCar(ShopCar shop) {
		// TODO Auto-generated method stub
		return shopCarDao.insertSelective(shop);
	}
	@Override
	public 	List<ShopCar>  findShopCarByUID(String uid) {
		// TODO Auto-generated method stub
		return shopCarDao.findShopCarByUID(uid);
	}
	@Override
	public int updataProductToShopCar(ShopCar shop) {
		// TODO Auto-generated method stub
		return shopCarDao.updateByPrimaryKeySelective(shop);
	}
	@Override
	public int delProductFromShopCar(String carId, String uid) {
		// TODO Auto-generated method stub
		return shopCarDao.delProductFromShopCar(carId, uid);
	}
 
	@Override
	public ShopCar findShopCarByPIDUID(String uid, String pid) {
		// TODO Auto-generated method stub
		return shopCarDao.findShopCarByPIDUID(uid, pid);
	}
	@Override
	public List<ShopCar> findByPKList(List<String> carIds) {
		// TODO Auto-generated method stub
		return shopCarDao.findByPKList(carIds);
	}
	@Override
	public int delShopCar(String id) {
		// TODO Auto-generated method stub
		return shopCarDao.deleteByPrimaryKey(id);
	}

}
