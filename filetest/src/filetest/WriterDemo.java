package filetest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriterDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
Scanner scanner = new Scanner(System.in);
FileWriter fw = null;
try{
	fw = new FileWriter("D:\\hgg.txt");
	System.out.println("请输入内容:");
	String str = scanner.nextLine();
	fw.write(str);
	System.out.println("已保存");
	
}catch(IOException e){
	e.printStackTrace();
}finally{
	try{
		fw.close();
		scanner.close();
	}catch(IOException e){
		e.printStackTrace();
	}
}
	}

}
