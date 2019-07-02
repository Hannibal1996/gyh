package com.igeek.shop.service;

import java.sql.SQLException;

import com.igeek.shop.dao.UserDao;
import com.igeek.shop.entity.User;

public class UserService {

	private UserDao dao=new UserDao();
	
	public boolean regist(User user) {
		int a=0;
		
		try {
			a=dao.regist(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a>0?true:false;
		
	}
	
	public boolean active(String activeCode) {
		
		try {
			dao.getEmail(activeCode);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}

		
	}

	public long checkUsername(String username) {
		// TODO Auto-generated method stub
		Long count=0L;	
		try {
			count=dao.checkUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
