import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.robotics.subsumption.*;
import lejos.util.Delay;

public class GetInformation implements Behavior{
	private BTConnection btc;
	public boolean takeControl()
	{
		btc = Bluetooth.waitForConnection();
		if (btc!= null) return true;
		return false;
	}
	
	public void action() {
	LCD.clear();
	LCD.drawString("Connected",0,0);
	DataInputStream dis = btc.openDataInputStream();
	LCD.clear();
	Delay.msDelay(500);

	try {
		LCD.drawString("Lets try this",0,0);
		int n = dis.readInt();
		LCD.drawInt(n, 3, 4);
		dis.close();
		Delay.msDelay(3000);

	} catch (IOException e) {
		e.printStackTrace();
	}
	
	//btc.close();
	
	
	}
	
	public void suppress() {
	}
	
}

