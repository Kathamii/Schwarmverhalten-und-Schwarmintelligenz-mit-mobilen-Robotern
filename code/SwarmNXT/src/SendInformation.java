
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.*;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.robotics.Color;
import lejos.robotics.subsumption.*;
import lejos.util.Delay;

public class SendInformation implements Behavior{
/*	  	private ColorSensor color;
	
	  public SendInformation(SensorPort colorport)
	    {
	    	color = new ColorSensor(colorport);
	     
	    }
	
	public boolean takeControl() {
	       if (color.getColorID() == Color.RED) return true;
	       return false;
	    }
*/
	
	private TouchSensor touch;
	
	  public SendInformation(SensorPort touchport)
	    {
	    	touch = new TouchSensor(touchport);
	     
	    }
	
	public boolean takeControl() {
	       //if (touch.isPressed()) return true;
	       return touch.isPressed();
	       
	    }

	    public void suppress() {
	       
	    }

	    public void action() {
	    	
	    	
	    	String name = "GD2017-3";

			LCD.drawString("Connecting...", 0, 0);
			//LCD.refresh();

			RemoteDevice btrd = Bluetooth.getKnownDevice(name);
			if (btrd == null) {
			LCD.clear();
			LCD.drawString("No such device", 0, 0);
			//LCD.refresh();
			Delay.msDelay(2000);
			System.exit(1);
			}

			BTConnection btc = Bluetooth.connect(btrd);

			if (btc == null) {
			LCD.clear();
			LCD.drawString("Connect fail", 0, 0);
			//LCD.refresh();
			Delay.msDelay(2000);
			System.exit(1);
			}

			LCD.clear();
			LCD.drawString("Connected", 0, 0);
			//LCD.refresh();

			DataOutputStream dos = btc.openDataOutputStream();

			try {
				
				// send this information
			
			dos.writeInt(1);
			dos.flush();
			
			} catch (IOException ioe) {
			
			}

			try {
			LCD.drawString("Closing... ", 0, 0);
			//LCD.refresh();
			
			dos.close();
			btc.close();
			} catch (IOException ioe) {
			
			}

			LCD.clear();
			LCD.drawString("Finished",3, 4);
			//LCD.refresh();
			Delay.msDelay(2000);
			LCD.clear();
	    }
}
