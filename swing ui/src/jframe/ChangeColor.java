package jframe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChangeColor extends JFrame {
	
    JPanel p;
    JButton btnRed,btnGreen,btnYellow;
    public ChangeColor(){
    	super("事件测试--改变颜色");
    	p = new JPanel();
    	btnRed = new JButton("红色");
    	btnGreen = new JButton("绿色");
    	btnYellow = new JButton("黄色");
    	ButtonListener btnlistener = new ButtonListener();
    	btnRed.addActionListener(btnlistener);
    	btnGreen.addActionListener(btnlistener);
    	btnYellow.addActionListener(btnlistener);
    	p.add(btnRed);
    	p.add(btnGreen);
    	p.add(btnYellow);
    	this.add(p);
    	this.setSize(500,300);
		this.setLocation(700, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
    	
    }
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			//获取事件源
			Object source = e.getSource();
			//判断事件源，进行相应处理
			if(source==btnRed){
				p.setBackground(Color.red);
				
			}else if(source==btnGreen){
				p.setBackground(Color.green);
				
			}else{
				p.setBackground(Color.yellow);
				
			}
			
			
			
		}
		
	}
	
	
	

	public static void main(String[] args) {
		
		new ChangeColor();
		
	}

}
