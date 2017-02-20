import java.math.BigDecimal;


public class GlobalTime {
	private double tick;
	private Timer timer;
	
	GlobalTime(){
		tick = 0;
		timer = new Timer();
		timer.start();
	}
	
	public double getTick(){
		BigDecimal bi = new BigDecimal(String.valueOf(this.tick+this.timer.time));
		return bi.setScale(1,BigDecimal.ROUND_DOWN).doubleValue();
	}

	public Timer getTimer(){
		return this.timer;
	}
	
	public void advTick(double tick){
		this.tick += tick;
	}
}
