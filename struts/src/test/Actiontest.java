package test;

import com.opensymphony.xwork2.ActionSupport;

public class Actiontest extends ActionSupport{

	public String execute() {
		System.out.println("execute方法执行了");
		return NONE;
	}	
	public String add() {
		System.out.println("add方法执行了");
		return NONE;
	}
	public String delete() {
		System.out.println("delete方法执行了");
		return NONE;
	}
}
