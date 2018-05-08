
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class FindSound implements Behavior{
	private SoundSensor sound;
	private boolean suppressed;
	 public FindSound(SensorPort soundport)
	    {
	    	sound = new SoundSensor(soundport);
	    }
	 public boolean takeControl() {
		 return sound.readValue()<80;
	   }

	   public void suppress() {
		   suppressed = true;
	   }

	   public void action() {
		   suppressed = false;
		     Motor.B.forward();
		     Motor.C.forward();
		     for(int i=0; i<65 && !suppressed;i++){
		    	 Delay.msDelay(30);
		    	 Thread.yield();
		     }
		     Motor.B.stop(); 
			 Motor.C.stop();
		     if (suppressed) return;
		     
		     rotatemotor(Motor.B);
		     rotatemotor(Motor.C);
		     
	   }
	   public void rotatemotor(NXTRegulatedMotor motor){
		   int volume = 0;
			  while (volume < sound.readValue()) {
				   volume = sound.readValue();
				   motor.forward();
				   Delay.msDelay(500);
				   motor.stop();
				   Delay.msDelay(50);
			  }
			  motor.backward();
			  Delay.msDelay(500);
			  motor.stop();
			  
	   }
}
