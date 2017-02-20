
public class Timer extends Thread {
	public double time;

	public Timer() {
		this.time = 0.0;
	}

	public void run() {
		while(true){
			time += 0.1;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
