package ben.kn.toolbox.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Overlord {

	private boolean running;
	private ExecutorService eService;
	
	public Overlord() {
		eService = Executors.newFixedThreadPool(5);
	}

	public void run() {
		running = true;

		String formattedName = "Thread-";
		int maxThreads = 10;

		System.out.println("Starting");
		
		for ( int i = 0; i < maxThreads; i++ ) {
			MyRunnable runnable = new MyRunnable();
			runnable.initialize(formattedName+i, 50000);
			eService.submit(runnable);
		}

		System.out.println("Ending");

		running = false;
	}

	public synchronized boolean isRunning() {
		return running;
	}
}
