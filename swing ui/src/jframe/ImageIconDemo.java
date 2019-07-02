package jframe;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ImageIconDemo extends JFrame {
	
	public ImageIconDemo(){
		
		super("ImangeIcon图标");
		ImageIcon ic = new ImageIcon("imanges\\IMG_0023.PNG");
		this.setIconImage(ic.getImage());
		this.setSize(500,300);
		this.setLocation(700, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void paint(Graphics g){
		ImageIcon bi = new ImageIcon("imanges\\IMG_0005.JPG");
		g.drawImage(bi.getImage(), 0, 30, this);
	
	
	}
	
	
	

	public static void main(String[] args) {
		
		// TODO 自动生成的方法存根
		new ImageIconDemo();
	}

}
