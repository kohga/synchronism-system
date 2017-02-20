
public class MyQueue {
	private int queueSize;
	private int queueNum;
	private Ack[] queue;

	public MyQueue(int size) {
		this.queueSize = size;
		this.queueNum = 0;
		queue = new Ack[queueSize];
	}
	
	public int getQueueNum(){
		return this.queueNum;
	}
	
	public Ack getAck(int num){
		return queue[num];
	}

	public void enqueue(Ack a) {
		if (queueNum >= queueSize) {
			throw new QueueOverflowException();
		}
		int num = queueNum++;
		queue[num] = a;
		sort(num);
	}

	public Ack dequeue() {
		if (queueNum <= 0) {
			throw new EmptyQueueException();
		}
		Ack ret = queue[0];
		queueNum--;
		shift(queue);
		return ret;
	}
	
	public void see(Process p) {
		for (int i=0; i < this.queueNum;i++) {
			if(p.getMyId()==1) System.out.print("process" + p.getMyId() + ":que[" + i + "]"+ this.queue[i].getID() +":" + this.queue[i].getTick() + "\n");
			else System.out.print(p.buf +"process" + p.getMyId() + ":que[" + i + "]"+ this.queue[i].getID() +":" + this.queue[i].getTick() + "\n");
		}
	}
	
	public void delQue(int ackId){
		for (int i=0; i < this.queueNum;i++) {
			if(this.queue[i].getX()==ackId){
				for (int j=i; j < this.queueNum;j++) {
					this.queue[j]=this.queue[j+1];
				}
				this.queueNum--;
				i--;
			}
		}
	}
	
	private void shift(Ack[] array) {
		Ack retArray[] = new Ack[queueSize];
		int tmp=0;
		for (int i=1; i < array.length; i++,tmp++) {
			retArray[tmp] = array[i];
		}
		this.queue = retArray;
	}

	private void sort(int cnt){
		int end,i,swp=0;
		for(end=cnt;end>=0;end--){
			for(i=0;i<end-1;i++){
				if(queue[i].getTick()>queue[i+1].getTick()){
					swp++;
					swap(i,i+1);
				}
			}
			if(swp==0) break;
			swp=0;
		}
	}
	
	private void swap(int fir, int sec){
		Ack buf;
		buf = queue[fir];
		queue[fir] = queue[sec];
		queue[sec] = buf;
	}

	public class QueueOverflowException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public QueueOverflowException() {
			super("size of queue over.");
		}
	}

	public class EmptyQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public EmptyQueueException () {
			super("queue is empty.");
		}
	}
}
