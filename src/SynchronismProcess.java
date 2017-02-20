

public class SynchronismProcess {

	public static void main(String[] args) {
		try {
			/* Init */
			GlobalTime gTime = new GlobalTime();
			Process p1 = new Process(1,"New York");
			Process p2 = new Process(2,"San Francisco");
			Update u1 = new Update(1,"New York"); 
		    Update u2 = new Update(2,"San Francisco"); 
			p1.setGlobalTime(gTime);
			p2.setGlobalTime(gTime);
			p1.setProcess(p2);
			p2.setProcess(p1);
			u1.setGlobalTime(gTime);
			u2.setGlobalTime(gTime);
			u1.setProcess(p1, p2);
			u2.setProcess(p1, p2);
			u1.setVisible(true);
			u2.setVisible(true);
			p1.start();
			p2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
