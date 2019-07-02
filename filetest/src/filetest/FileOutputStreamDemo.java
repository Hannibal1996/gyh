package filetest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileOutputStreamDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
Scanner scanner = new Scanner(System.in);
	FileOutputStream fos = null;
	try{
		fos = new FileOutputStream("D:\\mytest.txt");
	System.out.println("请输入内容:");
	String str = scanner.nextLine();
	fos.write(str.getBytes());
	System.out.println("已保存");
	}catch(IOException e){
		e.printStackTrace();
	}finally{
		try{
			fos.close();
			scanner.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	}

}
