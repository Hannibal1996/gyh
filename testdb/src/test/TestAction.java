package test;

import actiontest.StudentAction;
import modeltest.Student;

public class TestAction {

	public static void main(String[] args) throws Exception {
	
		StudentAction action = new StudentAction();
//	Student s = new Student();
//		s.setName("高永涵");
//		s.setAge(23);
	//	s.setSex(1);
//		s.setId(1);
//	action.add(s);
//		action.del(10);
//		action.edit(s);
Student m = action.query(3  );
System.out.println(m.toString());
	}

}
