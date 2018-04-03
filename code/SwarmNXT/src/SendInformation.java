
import java.io.DataOutputStream;
import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import lejos.nxt.*;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.util.Delay;

public class SendInformation {
	  	
	
	  public void sendinformation(int information)
	    {
		  String name = "GD2017-3";

			LCD.drawString("Connecting...", 0, 0);

			RemoteDevice remotedevice = Bluetooth.getKnownDevice(name);
			if (remotedevice == null) {
			LCD.clear();
			LCD.drawString("No such device", 0, 0);
			Delay.msDelay(2000);
			return;
			}

			BTConnection btconnection = Bluetooth.connect(remotedevice);

			if (btconnection == null) {
			LCD.clear();
			LCD.drawString("Connect fail", 0, 0);
			Delay.msDelay(2000);
			}
			else {
			LCD.clear();
			LCD.drawString("Connected", 0, 0);

			DataOutputStream dos = btconnection.openDataOutputStream();

			try {
				
				// send this information
			
			dos.writeInt(information);
			dos.flush();
			
			} catch (IOException ioe) {
			
			}

			try {
			LCD.drawString("Closing... ", 0, 0);
			
			dos.close();
			btconnection.close();
			} catch (IOException ioe) {
			
			}

			LCD.clear();
			LCD.drawString("Finished",3, 4);
			Delay.msDelay(2000);
			LCD.clear();
	     
	    }
	    }
}
	
	
	    

