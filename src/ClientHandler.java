import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**should recieve:
    array list of buttons pressed consisting of (w,a,s,d,up,down,left,right,)
    mouse location

 **should send
    an object containing all of the the aspects of the map(including the player)

 */
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
				
				if (parsedKeyCode[0].equals("true")) { // w
					
					player.shiftY(MOVELENGTH);
					
				}
				
				if (parsedKeyCode[1].equals("true")) { // a
					
					player.shiftX(-MOVELENGTH);
					
				}
				
				if (parsedKeyCode[2].equals("true")) { // s
					
					player.shiftY(-MOVELENGTH);
					
				}
				
				if (parsedKeyCode[3].equals("true")) { // d
					
					player.shiftX(MOVELENGTH);
					
				}
				
			}
			
		}
		
	});

	
	public ClientHandler(ClientKey key) {
		
		CLIENT = key;
		
		player = new Player(0, 0);
		
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
	
}
