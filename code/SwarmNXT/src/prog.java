
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class prog {
	
	public static void main(String[] args) {
		
		Behavior b1 = new DriveForward();
	    Behavior b2 = new HitObstacle(SensorPort.S2, SensorPort.S3);
	    Behavior b3 = new SendInformation(SensorPort.S1);
	    Behavior b4 = new GetInformation();
	    Behavior [] behaviorarray = {b1, b2, b3, b4};
	    Arbitrator arby = new Arbitrator(behaviorarray);
	    arby.start();
	}

}
