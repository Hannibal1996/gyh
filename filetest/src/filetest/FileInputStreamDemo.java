package filetest;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
FileInputStream fis = null;
try{
	fis = new FileInputStream("src\\filetest\\FileDemo.java");
	byte[] bbuf = new byte[1024];
	int hasRead = 0;
	while((hasRead=fis.read(bbuf))>0){
		System.out.print(new String(bbuf,0,hasRead));
	}
}catch(IOException e){
	e.printStackTrace();
}finally{
	try{
		fis.close();
	}catch(IOException e){
		e.printStackTrace();
	}
}






	}

}
