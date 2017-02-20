import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class Update extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btn;
	private int id;
	private String place;
	private Process p1;
	private Process p2;
	private GlobalTime gTime;

	/**
	 * Create the frame.
	 */
	Update(int id, String place){
		this.id = id;
		this.place = place;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 150, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn = new JButton("Send");
		btn.setBounds(34, 31, 82, 60);
		btn.addActionListener(this);
		contentPane.add(btn);
	}
	
	public void setProcess(Process p1, Process p2){
		this.p1 = p1;
		this.p2 = p2;
	}

	public void setGlobalTime(GlobalTime gTime){
		this.gTime = gTime;
	}
	
	public int getid(){
		return this.id;
	}
	
	public String getPlace(){
		return this.place;
	}
	
	public Process getProcess1(){
		return this.p1;
	}
	
	public Process getProcess2(){
		return this.p2;
	}
	
	public GlobalTime getGlobalTime(){
		return this.gTime;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Ack ack = new Ack("REQ",this.id,0);
		SendDmySrv s = new SendDmySrv(this, this.p1, this.p2, this.gTime, ack);
		gTime.advTick(1.0);
		s.start();
	}
}
