package filetest;

import java.io.File;

public class FileDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
File file = new File("C:\\QMDownload\\gyh");
	//System.out.println(file.exists());
	if(!file.exists())
		file.mkdirs();
	else
		file.delete();
	//是否是一个文件 
	System.out.println(file.isFile());
	//是否是一个目录
	System.out.println(file.isDirectory());
	
	
	
	
	}

}
