package daotest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBUtil;
import modeltest.Student;

public class StudentDao {

	public void addStudent(Student s) throws Exception{
	
		Connection conn=DBUtil.getConnection();
		String sql="" +
				"insert into test" +
				"(name,sex,age)" +
				"values(?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,s.getName() );
		ptmt.setInt(2,s.getSex());
		ptmt.setInt(3, s.getAge());
		ptmt.execute();
			
	}
	public void delStudent(Integer id) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" delete from test " +
				" Where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setInt(1, id);
		ptmt.execute();	
	}
	public void updateStudent(Student s) throws SQLException{
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" update test " +
				" set name=?,sex=?,age=? " +
				" Where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,s.getName() );
		ptmt.setInt(2,s.getSex());
		ptmt.setInt(3, s.getAge());
		ptmt.setInt(4, s.getId());
		ptmt.execute();	
	}
	public Student queryStudent(Integer id) throws SQLException{
		Student s = null;
		Connection conn=DBUtil.getConnection();
		String sql="" +
				" select * from test " +
				" Where id=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1,id );
		ResultSet rs = ptmt.executeQuery();	
		while(rs.next()){
			s=new Student();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setAge(rs.getInt("age"));
			s.setSex(rs.getInt("sex"));
		}
		return s;
	}
	
	
	
	
}
