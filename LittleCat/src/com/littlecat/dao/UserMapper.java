package com.littlecat.dao;

import org.springframework.stereotype.Repository;

import com.littlecat.entity.User;

@Repository("userDao")
public interface UserMapper {
	int deleteByPrimaryKey(String uid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String uid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	Long userNameCheck(String username);

	Long emailCheck(String email);

	int activeEmailBycode(String code);

	User findUserByName(String username);
}