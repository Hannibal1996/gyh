package com.littlecat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.littlecat.entity.ShopCar;

 

@Repository("shopCarDao")
public interface ShopCarMapper {
    int deleteByPrimaryKey(String carId);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(String carId);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
    
	List<ShopCar>  findShopCarByUID(String uid);
	
	int delProductFromShopCar(String carId,String uId);
	
	ShopCar findShopCarByPIDUID(String uid,String pid);
	
	List<ShopCar> findByPKList(List<String>  carIds);
}