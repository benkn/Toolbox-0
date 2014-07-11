package ben.kn.toolbox.concurrent;

public class MyRunnable implements Runnable {
	private int n = 0;
	private String name;

	public void initialize(String name, int n) {
		this.n = n;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Exception thrown for " + name + ": " + e.getMessage());
		}
		for ( int i = 0; i < n; i++ ) {

		}
		System.out.println("Finishing " + name);
	}
}