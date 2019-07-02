package com.littlecat.service;

import java.util.List;

import com.littlecat.entity.ShopCar;

 
public interface ShopCarService {
public int addProductToShopCar(ShopCar shop);
public int updataProductToShopCar(ShopCar shop);
public int delProductFromShopCar(String  carId,String uid);
public int delShopCar(String id);
public 	List<ShopCar>  findShopCarByUID(String uid);
public ShopCar findShopCarByPIDUID(String uid,String pid);
public List<ShopCar> findByPKList(List<String> str);
}
