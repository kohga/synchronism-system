
public class Process extends Thread {
	private int id;
	private String place;
	private GlobalTime gTime;
	private MyQueue que;
	private Process p;
	private int queSize = 10;
	private Ack[] task1 = new Ack[3];
	private Ack[] task2 = new Ack[3];
	String buf = "                                        ";
	Integer pre = 0;

	Process(int id, String place){
		this.id = id;
		this.place = place;
		que = new MyQueue(queSize);
	}
	
	public void run() {
		while(true){
			if( !pre.equals(this.que.getQueueNum()) && pre>=0 ){
				pre = this.que.getQueueNum();
				if(pre>0) {
					Ack now = this.que.getAck(pre-1);
					if(this.id==1) System.out.print("process" + this.id + ":que[" + (pre-1) + "]"+ now.getID() +":" + now.getTick() + "\n");
					else System.out.print(buf + "process" + this.id + ":que[" + (pre-1) + "]"+ now.getID() +":" + now.getTick() + "\n");
					if(now.getName().equals("REQ")) sendAck(now);
				}
			}
			if(pre>=3) checkQue(pre);
		}
	}
	
	private void sendAck(Ack req){
		Ack ack = new Ack("ACK",req.getX(),this.id);
		SendDmySrv s = new SendDmySrv(this, this.p, this.gTime, ack);
		gTime.advTick(1.0);
		s.start();
	}
	
	private void checkQue(int queNum){
		Ack now;
		int num1=0;
		int num2=0;
		for (int i=0; i < queNum;i++) {
			now = this.que.getAck(i);
			if(now.getX().equals(1)) {
				task1[num1]= now;
				num1++;
			}else if(now.getX().equals(2)){
				task2[num2]= now;
				num2++;
			}
		}
		if(num1==3) execUpdate(task1[0]);
		if(num2==3) execUpdate(task2[0]);
	}
	
	private void execUpdate(Ack ack){
		if(this.getMyId()==1) System.out.println("execute Task ->" + ack.getX());
		else System.out.println(buf + "execute Task ->" + ack.getX());
		this.que.delQue(ack.getX());
	}
	
	
	public void setGlobalTime(GlobalTime gTime){
		this.gTime = gTime;
	}
	
	public void setProcess(Process p){
		this.p = p;
	}
	
	public int getMyId(){
		return this.id;
	}
	
	public String getPlace(){
		return this.place;
	}
	
	public GlobalTime getGlobalTime(){
		return this.gTime;
	}
	
	public MyQueue getMyQueue(){
		return this.que;
	}
	
	public Process getProcess(){
		return this.p;
	}
}
