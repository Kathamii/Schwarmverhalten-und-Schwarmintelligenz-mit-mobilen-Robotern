
import lejos.nxt.Motor;
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
		 return sound.readValue()<90;
	   }

	   public void suppress() {
		   suppressed = true;
	   }

	   public void action() {
		   suppressed = false;
		     Motor.B.forward();
		     Motor.C.forward();
		     for(int i=0; i<50 && !suppressed;i++){
		    	 Delay.msDelay(60);
		    	 Thread.yield();
		     }
		     Motor.B.stop(); 
			 Motor.C.stop();
		     if (suppressed) return;
		     
		     int volume = 0;
			   while (volume < sound.readValue()) {
				   volume = sound.readValue();
				   Motor.B.forward();
				   Delay.msDelay(200);
				   Motor.B.stop();
		
		     }
		     
	   }
}
