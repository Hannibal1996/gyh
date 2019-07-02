package filetest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
BufferedReader br = null;
try{
	br = new BufferedReader(new FileReader("src\\filetest\\FileDemo.java"));
	String result = null;
	while((result = br.readLine())!=null){
		System.out.println(result);
	}
}catch(IOException e){
	e.printStackTrace();
	
}finally{
	try{
		br.close();
	}catch(IOException ex){
		ex.printStackTrace();
	}
}		
	}
}
