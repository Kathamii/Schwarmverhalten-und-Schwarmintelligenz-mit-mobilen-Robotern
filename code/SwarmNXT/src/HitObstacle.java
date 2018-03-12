import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class HitObstacle implements Behavior{
	private TouchSensor touch;
    private UltrasonicSensor ultrasonic;
    private boolean suppressed = false;
    
    public HitObstacle(SensorPort ultraport, SensorPort touchport)
    {
    	ultrasonic = new UltrasonicSensor(ultraport);
       touch = new TouchSensor(touchport);
    }

    public boolean takeControl() {
       return touch.isPressed() || ultrasonic.getDistance() < 25;
    }

    public void suppress() {
       suppressed = true;
    }

    public void action() {
       suppressed = false;
       Motor.B.rotate(-180, true);
       Motor.C.rotate(-360, true);

       while( Motor.C.isMoving() && !suppressed )
         Thread.yield();

       Motor.B.stop();
       Motor.C.stop();
    }

}
