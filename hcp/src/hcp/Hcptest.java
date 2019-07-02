package hcp;

public class Hcptest {

	public static void main(String[] args) {
		//创建线程对象
		Hcp h = new Hcp();
		Thread a = new Thread(h);
		a.setName("窗口一");
		Thread b = new Thread(h);
		b.setName("窗口二");
		Thread c = new Thread(h);
		c.setName("窗口三");
		
		//启动线程对象
		a.start();
		b.start();
		c.start();

	}

}
