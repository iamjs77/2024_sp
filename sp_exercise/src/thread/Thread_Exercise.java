package thread;

/**
 * Thread 2개를 만든 후, Main함수와 Thread 2개에서 동시에 0부터 9까지 출력하시오. 
어디서 출력하였는지 구분할 수 있게 숫자 앞에 [Main], [Thread1], [Thread2] 표시하시오
 *
 */
public class Thread_Exercise {

	public static void main(String[] args) throws InterruptedException {
		Thread1 thread1 = new Thread1(); 
		Thread1 thread2 = new Thread1(); 
		
		Thread t1 = new Thread(thread1, "[Thread1]");
		Thread t2 = new Thread(thread2, "[Thread2]");
		
		t1.start();
		t2.start();
		
		for(int inx = 0 ; inx < 10; inx++) {
			System.out.println("[Main] " + inx);
				Thread.sleep(3);
		}
		
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
		
	}
}
