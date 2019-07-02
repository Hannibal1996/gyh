package com.igeek.shop.dao;



import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.igeek.common.utils.DataSourceUtils;
import com.igeek.shop.entity.User;

public class UserDao {

	public int regist(User user) throws SQLException {
		
		QueryRunner runner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		
		int a=runner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),
			  user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
			  user.getSex(),user.getState(),user.getCode());
		return a;

	}

	public void getEmail(String activeCode) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runnaer=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="update user set state=1 where code='"+activeCode+"'";
		runnaer.update(sql);
	}

	public Long checkUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runner =  new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select count(*) from user where username = ?" ;
		Long count = (Long)runner.query(sql, new ScalarHandler(), username);
		return count;

	}
}
