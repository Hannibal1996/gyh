package jframe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelDemo extends JFrame {
	
	private JPanel p;
	private JButton btnOk, btnCancel;
	public JPanelDemo(){
		super("JPanelDemo");
		p=new JPanel();
		btnOk=new JButton("确认");
		btnCancel=new JButton("取消");
		p.add(btnOk);
		p.add(btnCancel);
		this.add(p);
		this.setSize(500,300);
		this.setLocation(700, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
			
	}
	
	

	public static void main(String[] args) {
	
		new JPanelDemo();

	}

}
