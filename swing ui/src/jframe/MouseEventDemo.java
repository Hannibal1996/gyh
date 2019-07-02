package jframe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseEventDemo extends JFrame {
	private JPanel p;
	int pre_x = -1,pre_y = -1;
	int x,y;
	public MouseEventDemo(){
		super("画板");
		p = new JPanel();
		p.addMouseMotionListener(new PaintListener());
		p.addMouseListener(new ReseListener());
		this.add(p);
    	this.setSize(500,300);
		this.setLocation(700, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
			
	}
	//重写JFrame的paint()方法，此方法用于在窗体中画图
	public void paint(Graphics g){
		g.setColor(Color.red);
		if(pre_x>0&&pre_y>0){
			//绘制一条线段，从上一次鼠标拖动事件点到本次鼠标拖动事件点
			g.drawLine(pre_x, pre_y, x, y);
		}
		//保存当前鼠标坐标，称为上一次的历史坐标
		pre_x = x;
		pre_y = y;
		
	}
	//定义鼠标拖动监听类
	class PaintListener implements MouseMotionListener{
	


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		//获取鼠标当前的坐标
		x = e.getX();
		y = e.getY();
		//重画，repaint()触发paint()
		MouseEventDemo.this.repaint();	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
	}
	//定义鼠标监听类
	class ReseListener implements MouseListener{
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	//鼠标按下事件
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		//获取鼠标按键，判断是否是右键
		if(e.getButton()==MouseEvent.BUTTON3){
			//重画画板（擦出原来的痕迹）
			MouseEventDemo.this.p.repaint();
		}
			
	}

	@Override
	//鼠标松开事件处理，重置历史坐标
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		//鼠标松开时，将历史坐标重设为-1（重置）
		pre_x = -1;
		pre_y = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	  }

	public static void main(String[] args) {
		
		new MouseEventDemo();
		
	}


}
