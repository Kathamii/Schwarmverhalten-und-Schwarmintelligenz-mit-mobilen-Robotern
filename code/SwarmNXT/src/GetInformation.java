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
	String connected = "Connected";
	String closing = "Closing...";

	LCD.clear();
	LCD.drawString(connected,0,0);
	DataInputStream dis = btc.openDataInputStream();

	try {
		int n = dis.readInt();
		LCD.drawInt(n, 0, 0);
		dis.close();
		Delay.msDelay(1000);

	} catch (IOException e) {
		e.printStackTrace();
	}
	
	LCD.clear();
	LCD.drawString(closing,0,0);
	btc.close();
	LCD.clear();
	
	}
	
	public void suppress() {
	}
	
}

