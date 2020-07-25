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
	
	private Timer move = new Timer(1000/60, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (keyCode.equals(FALSECODE)) {
				
				move.stop();
				
			} else {
				
				String[] parsedKeyCode = keyCode.split(";");
				
				if (parsedKeyCode[0].equals("true")) { // w
					
					
					
				}
				
				if (parsedKeyCode[1].equals("true")) { // a
					
					
					
				}
				
				if (parsedKeyCode[2].equals("true")) { // s
					
					
					
				}
				
				if (parsedKeyCode[3].equals("true")) { // d
					
					
					
				}
				
			}
			
		}
		
	});

	
	public ClientHandler(ClientKey key) {
		
		CLIENT = key;
		
		player = genPlayer();
		
	}

	public void loadPacket(ClientPacket packet) {
		
		keyCode = packet.getKeyCode();
		
		if (!move.isRunning()) {
			move.start();
		}
		
	}
	
	// Should generate a player in a valid position (
	private Player genPlayer() {
		
		return null;
		
	}
	
}
