import java.util.Random;


public class SendDmySrv extends Thread {
	private Update u = null;
	private Process p1;
	private Process p2;
	private GlobalTime gTime;
	private Ack ack;
	

	public SendDmySrv(Update u, Process p1, Process p2, GlobalTime gTime, Ack ack) {
		this.u = u;
		this.p1 = p1;
		this.p2 = p2;
		this.gTime = gTime;
		this.ack = ack; 
	}
	
	public SendDmySrv(Process p1, Process p2, GlobalTime gTime, Ack ack) {
		this.p1 = p1;
		this.p2 = p2;
		this.gTime = gTime;
		this.ack = ack; 
	}

	public void run() {
	    if( this.u==null && !ack.getX().equals(ack.getY())) {
	    	Random rnd = new Random();
	    	int ran = rnd.nextInt(10);
	    	ran++;
	    	try {
	    		sleep(ran*200);
	    		if(ack.getX().equals(1)){
	    			ran = rnd.nextInt(10);
	    			sleep(ran*500);
	    		}
	   		} catch (InterruptedException e) {
	   			e.printStackTrace();
	   		}
	    }
	    ack.setTick(gTime.getTick());
	    p1.getMyQueue().enqueue(ack);
	    p2.getMyQueue().enqueue(ack);
	}
}
