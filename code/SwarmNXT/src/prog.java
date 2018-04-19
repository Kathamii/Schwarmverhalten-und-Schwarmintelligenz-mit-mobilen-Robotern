
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class prog {

	public static class FindObjectBehavior extends Thread {
		@Override
		public void run() {
			Behavior driveforward = new DriveForward();
		    Behavior hitobstacle = new HitObstacle(SensorPort.S2, SensorPort.S3);
		    Behavior findobject = new FindObject(SensorPort.S1);
		    Behavior [] behaviorarray = {driveforward, hitobstacle, findobject};
		    Arbitrator arby = new Arbitrator(behaviorarray);
		    arby.start();
		}
	}
	
	public static class FindSoundBehavior extends Thread {
		@Override
		public void run() {
		    Behavior hitobstacle = new HitObstacle(SensorPort.S2, SensorPort.S3);
		    Behavior findsound = new FindSound(SensorPort.S4);
		    Behavior [] behaviorarray = {findsound, hitobstacle};
		    Arbitrator arby = new Arbitrator(behaviorarray);
		    arby.start();
		}
	}
	
	
	public static void main(String[] args) {
	
		Thread findobjectbehavior = new FindObjectBehavior();
		findobjectbehavior.start();
		LCD.clear();
		LCD.drawString("findObject", 0, 0);
		Thread findsoundbehavior = new FindSoundBehavior();
		
		int information = new RecieveInformation().recieveinformation();
			
		if (information == 1){
			findobjectbehavior.interrupt();
			while  (!findobjectbehavior.isInterrupted());
			findsoundbehavior.start();
			LCD.clear();
			LCD.drawString("findSound", 0, 0);
		}
		
	}

}
