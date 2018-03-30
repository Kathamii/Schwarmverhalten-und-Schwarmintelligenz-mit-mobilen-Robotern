
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class prog {

	public static class BehaviorThread extends Thread {
		@Override
		public void run() {
			Behavior b1 = new DriveForward();
		    Behavior b2 = new HitObstacle(SensorPort.S2, SensorPort.S3);
		    Behavior b3 = new SendInformation(SensorPort.S3);
		    //Behavior b4 = new GetInformation();
		    Behavior [] behaviorarray = {b3};
		    Arbitrator arby = new Arbitrator(behaviorarray);
		    arby.start();
		    
		}
	}
	
	
	public static void main(String[] args) {
	
		Thread behaviorThread = new BehaviorThread();
		behaviorThread.start();
		
		int information = new RecieveInformation().recieveinformation();
			
		if (information == 1){
			behaviorThread.interrupt();
			Sound.twoBeeps();
		}
		
	}

}
