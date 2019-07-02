package hcp;

public class Hcp implements Runnable {
	int t=100;


	@Override
	public void run() {
		while(true){
			if(t>0){
			System.out.println(Thread.currentThread().getName()+":"+t--);
		}
		}
	}
	

}
