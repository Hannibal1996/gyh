package actiontest;

import daotest.StudentDao;
import modeltest.Student;

public class StudentAction {
 
	public void add(Student student) throws Exception{
		StudentDao dao = new StudentDao();
		dao.addStudent(student);
	}
	
	public void edit(Student student) throws Exception{
		StudentDao dao = new StudentDao();
		dao.updateStudent(student);
	}
	
	public void del(Integer id) throws Exception{
		StudentDao dao = new StudentDao();
		dao.delStudent(id);
	}
	
	public Student query(Integer id) throws Exception{
		StudentDao dao = new StudentDao();
		return dao.queryStudent(id);
	}
	
	
	
	

}
