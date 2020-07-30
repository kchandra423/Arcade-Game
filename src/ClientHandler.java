import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ClientHandler {

	private final ClientKey CLIENT;
	
	private String keyCode = "false;false;false;false"; // w;a;s;d
	
	private final String FALSECODE = "false;false;false;false";
	
	private Player player;
	
	private final int MOVELENGTH = 10;
	
	private Timer move = new Timer(1000/60, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (keyCode.equals(FALSECODE)) {
				
				move.stop();
				
			} else {
				
				String[] parsedKeyCode = keyCode.split(";");
				
				int trueCount = 0;
				
				for (int i = 0; i < parsedKeyCode.length; i++) {
					
					if (parsedKeyCode[i].equals("true")) {
						
						trueCount ++;
						
					}
					
				}
				
				if (trueCount == 2) {
					
					movePlayer((int)(MOVELENGTH / Math.sqrt(2)));
					
				} else {
					
					movePlayer(MOVELENGTH);
					
				}
				
			}
			
		}
		
	});

	
	public ClientHandler(ClientKey key, int pNum) {
		
		CLIENT = key;
		
		player = new Player(0, 0, pNum);
		
	}

	public void loadPacket(ClientPacket packet) {
		
		keyCode = packet.getKeyCode();
		
		if (!move.isRunning()) {
			move.start();
		}
		
	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
	public ClientKey getKey() {
		
		return CLIENT;
		
	}
	
	private void movePlayer(int shift) {
		
		String[] parsedKeyCode = keyCode.split(";");
		
		if (parsedKeyCode[0].equals("true")) { // w
			
			player.shiftY(shift);
			
		}
		
		if (parsedKeyCode[1].equals("true")) { // a
			
			player.shiftX(-shift);
			
		}
		
		if (parsedKeyCode[2].equals("true")) { // s
			
			player.shiftY(-shift);
			
		}
		
		if (parsedKeyCode[3].equals("true")) { // d
			
			player.shiftX(shift);
			
		}
		
	}
	
}
