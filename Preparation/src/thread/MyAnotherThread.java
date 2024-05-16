package thread;

//creating thread using thread class
public class MyAnotherThread extends Thread {

	public void run() {
		for(int i = 10;i>=1;i--) {
			System.out.println("another thread value of i = "+i);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
