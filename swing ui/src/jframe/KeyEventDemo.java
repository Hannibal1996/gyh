package jframe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KeyEventDemo extends JFrame {
	
	private JPanel p;
	private JButton btnMove;
	public KeyEventDemo(){
		
	super("键盘事件-方向控制");
	p = new JPanel();
	btnMove = new JButton("走动");
	btnMove.addKeyListener(new MyListener());
	p.add(btnMove);
	this.add(p);
	this.setSize(500,300);
	this.setLocation(700, 300);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
				
	}
	class MyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO 自动生成的方法存根
	
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自动生成的方法存根
			int key = e.getKeyCode();
			int x = btnMove.getX();
			int y = btnMove.getY();
			if(key==KeyEvent.VK_RIGHT){
				btnMove.setLocation(x+5,y);
			}else if(key==KeyEvent.VK_LEFT){
				btnMove.setLocation(x-5,y);
			}else if(key==KeyEvent.VK_UP){
				btnMove.setLocation(x,y-5);
			}else if(key==KeyEvent.VK_DOWN){
				btnMove.setLocation(x,y+5);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 自动生成的方法存根
			
		}
		
	}
	
	

	public static void main(String[] args) {
		new KeyEventDemo();
	}

}
