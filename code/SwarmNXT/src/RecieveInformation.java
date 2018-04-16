import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.util.Delay;

public class RecieveInformation {
	private BTConnection btconnection;
	
	public int recieveinformation() {
		
		
		btconnection = Bluetooth.waitForConnection();
		LCD.clear();
		LCD.drawString("Connected",0,0);
		DataInputStream dis = btconnection.openDataInputStream();
		LCD.clear();
		Delay.msDelay(500);

		try {
			int n = dis.readInt();
			LCD.drawString("Recieved int: "+n, 0, 0);
			dis.close();
			Delay.msDelay(3000);
			LCD.clear();
			btconnection.close();
			return n;

		} catch (IOException e) {
			
		}
		
		
		return 0;
		
	}
}
