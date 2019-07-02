package com.littlecat.service.impl;
 
import javax.annotation.Resource; 
import org.springframework.stereotype.Service; 
import com.littlecat.dao.UserMapper;
import com.littlecat.entity.User;
import com.littlecat.service.UserService;
import com.littlecat.utils.MD5Utils;
import com.littlecat.utils.UuidUtil;
 

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	@Resource(name = "userDao")
	private UserMapper userDao;

	@Override
	public User findUserByPK(String uid) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(uid);
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		String id = UuidUtil.get32UUID();
		String pwd = MD5Utils.md5(user.getPassword());
		user.setUid(id);
		user.setPassword(pwd);
		 
		user.setState(0);
		return userDao.insertSelective(user);

	}
	  
	@Override
	public Boolean userNameCheck(String username) {
		// TODO Auto-generated method stub
		System.out.println( userDao.userNameCheck(username));
		return userDao.userNameCheck(username) > 0 ? true : false;
	}

	@Override
	public int activeEmailBycode(String code) {
		// TODO Auto-generated method stub
		return userDao.activeEmailBycode(code);
	}
	
	@Override
	public User loginCheck(String name ) {
	 
	User user= userDao.findUserByName(name);
 
	return user;
	}

	@Override
	public Boolean emailCheck(String email) {
		// TODO Auto-generated method stub
		  	return userDao.emailCheck(email) > 0 ? true : false; 
	}

 
//	@Override
//	public User loginCheck(String name) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
