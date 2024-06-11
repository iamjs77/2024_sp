package thread;

public class Thread2 implements Runnable {

	@Override
	public void run() {
		for (int inx = 0; inx < 10; inx++) {
			System.out.println(Thread.currentThread().getName() + " " + inx);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
