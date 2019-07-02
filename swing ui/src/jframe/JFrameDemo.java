package jframe;

import javax.swing.JFrame;

public class JFrameDemo extends JFrame {
	
	public JFrameDemo(){
		super("涵哥测试");
		this.setSize(500,300);
		this.setLocation(700, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);		
	}
	
	public static void main(String[] args) {
		
		new JFrameDemo();
		
		

	}

}
