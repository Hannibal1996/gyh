package com.littlecat.service;

import java.util.Map;

import com.littlecat.entity.User;

public interface UserService {
	public User findUserByPK(String uid);
	public int addUser(User user);
 
	public int activeEmailBycode(String code);
	public User  loginCheck(String name );
	public  Boolean userNameCheck(String name);
	public  Boolean emailCheck(String email);
	
	 
}
