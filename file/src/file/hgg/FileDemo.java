package file.hgg;

import java.io.File;
import java.io.IOException;

public class FileDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
File file = new File(".");
System.out.println(file.getName());
System.out.println(file.getParent());
System.out.println(file.getAbsolutePath());
System.out.println(file.getAbsoluteFile().getParent());
		File newFile = new File("D:\\gyh.txt");
		System.out.println("newFile对象是否存在:"+newFile.exists());
		try{
			newFile.createNewFile();
		}catch(IOException e){
			e.printStackTrace();	
		}
	System.out.println("创建目录:"+newFile.mkdir());
	String[] fileList = file.list();
	System.out.println("=====当前路径下所有文件和路径如下=====");
	for(String fileName:fileList){
		System.out.println(fileName);
	}
		File[] roots = File.listRoots();
		System.out.println("=====系统所有根目录如下=====");
		for(File root:roots){
			System.out.println(root);
		}
		
		
	}

}
