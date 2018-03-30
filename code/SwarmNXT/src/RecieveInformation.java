import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.util.Delay;

public class RecieveInformation {
	private BTConnection btc;
	
	public int recieveinformation() {
		
		
		btc = Bluetooth.waitForConnection();
		LCD.clear();
		LCD.drawString("Connected",0,0);
		DataInputStream dis = btc.openDataInputStream();
		LCD.clear();
		Delay.msDelay(500);

		try {
			int n = dis.readInt();
			LCD.drawString("send "+n, 3, 4);
			dis.close();
			Delay.msDelay(3000);
			LCD.clear();
			btc.close();
			return n;

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return 0;
		
	}
}
