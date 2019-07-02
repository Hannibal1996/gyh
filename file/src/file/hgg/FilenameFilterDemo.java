package file.hgg;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
File file = new File("C:\\Program Files\\Java\\jdk1.8.0_121");
	if(file.exists()&&file.isDirectory()){
		String[] allFileNames = file.list(); 
		for(String name:allFileNames){
			System.out.println(name);	
		}
		String[] filterFileNames = file.list(new FilenameFilter(){
			public boolean accept(File dir,String name){
				return(name.endsWith(".zip"));
			}
		});
				System.out.println("过滤后的文件为:");
				for(String name:filterFileNames){
					System.out.println(name);
				}
	}
	
	
	
	}

}
