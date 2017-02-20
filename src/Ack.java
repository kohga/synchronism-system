
public class Ack {
	private String ID;
	private String name;
	private Integer x;
	private Integer y;
	private double tick;

	public Ack(String name, int x, int y, double tick) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.tick = tick;
		if(y!=0) 
			this.ID=name + x + "-" + y;
		else 
			this.ID=name + x ;
	}
	
	public Ack(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
		if(y!=0) 
			this.ID=name + x + "-" + y;
		else 
			this.ID=name + x ;
	}
	
	public void setTick(double tick){
		this.tick = tick;
	}
	
	public String getID(){
		return this.ID;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Integer getX(){
		return this.x;
	}
	
	public Integer getY(){
		return this.y;
	}
	
	public double getTick(){
		return this.tick;
	}
}
