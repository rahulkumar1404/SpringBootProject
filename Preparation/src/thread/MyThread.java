package thread;

//creating our thread using Runnable Interface
public class MyThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 1;i<=10;i++) {
			System.out.println("value of i is "+i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void main(String[] args) {
//		MyThread t1 = new MyThread();
//		
//		Thread thread = new Thread(t1);
//		MyAnotherThread t2 = new MyAnotherThread();
//		t2.start();
//		
//		thread.start();
		
		System.out.println("Preogram started");
		
		int sum = 10+20;
		
		System.out.println("Sum is "+sum);
		
		Thread thread = Thread.currentThread();
		String tname = thread.getName();
		System.out.println("Current running thread is "+tname);
		thread.setName("Ka re");
		System.out.println("Current running thread is "+thread.getName());
		System.out.println("Current running thread Id is "+thread.getId());
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Program ended");
		
		
	}

}
