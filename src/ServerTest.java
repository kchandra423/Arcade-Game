import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Timer;

public class ServerTest {
	
	public static void addClient() {
		
		new Timer (100, new ActionListener() {

			int port = genPort();
			InetAddress address = genAddress();
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ClientPacket packet = genPacket();
				
				PacketHandler.fakeReceive(packet, address, port);
				
			}
			
		}).start();;
		
	}
	
	private static ClientPacket genPacket() {
		
		return new ClientPacket(ThreadLocalRandom.current().nextBoolean() + "" + ThreadLocalRandom.current().nextBoolean() + "" + ThreadLocalRandom.current().nextBoolean() + "" + ThreadLocalRandom.current().nextBoolean(), ThreadLocalRandom.current().nextDouble(0, 360));
		
	}
	
	private static InetAddress genAddress() {
		
		try {
			return InetAddress.getByName(ThreadLocalRandom.current().nextInt(256) + "." + ThreadLocalRandom.current().nextInt(256) + "." + ThreadLocalRandom.current().nextInt(256) + "." + ThreadLocalRandom.current().nextInt(256));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static int genPort() {
		
		return ThreadLocalRandom.current().nextInt(0, 65536);
		
	}

}


