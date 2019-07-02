package com.itheima.domain;

import java.util.Properties;

public class BeanConfig {

	private String id;
	private String className;
	
	private Properties props = new Properties();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	@Override
	public String toString() {
		return "BeanConfig [id=" + id + ", className=" + className + ", props=" + props + "]";
	}
	
	
	
	
}
