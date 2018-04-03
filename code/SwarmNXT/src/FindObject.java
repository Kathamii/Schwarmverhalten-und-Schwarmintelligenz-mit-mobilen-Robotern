import lejos.nxt.*;
import lejos.robotics.Color;
import lejos.robotics.subsumption.*;

public class FindObject implements Behavior{
	  	private ColorSensor color;
	
	  public FindObject(SensorPort colorport)
	    {
	    	color = new ColorSensor(colorport);
	     
	    }
	
	public boolean takeControl() {
	      
	       return color.getColorID() == Color.RED;
	    }

	    public void suppress() {
	       
	    }

	    public void action() {
	    	
	    	new SendInformation().sendinformation(1);
	    	
	    	
	    	Sound.playTone(1000, 30000, 100);
	        while (true);
	    }
}
