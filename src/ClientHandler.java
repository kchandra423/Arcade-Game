import sun.rmi.runtime.Log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;

import javax.swing.Timer;

/*
 
	This class handles an individual client that connects to the server and manages the updates
	received from the client and send the needed updates to the client as well. The main purpose of 
	this class is to handle the movement and positioning of each client.
 
*/

public class ClientHandler {

	private final ClientKey CLIENT; // Holds the address and port to the client

	private final String address;

	private String keyCode = "false;false;false;false"; // w;a;s;d, the key code will come from the 
														// client and tells the server which keys are
														// being pressed
	
	private final String FALSECODE = "false;false;false;false"; // This is just a variable that 
																// represents the keys when nothing 
																// is being pressed
	
	private final Player player; // This is the player object that holds the positioning data for the 
						   		 // client's player
	
	private final int MOVELENGTH = 10; // This is how many pixels the player will move every frame
	
	private Timer move = new Timer(1000/60, new ActionListener() { // This timer will run almost all
																   // the time (look at the code for
																   // when it stops) and will move the
																   // player

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (keyCode.equals(FALSECODE)) {
				
				move.stop();
				LogHandler.write("(Client Handler) Timer Stopped", address);
				
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
		
		player = new Player(Map.getMapWidth() / 2, Map.getMapHeight() / 2, pNum); // Makes the player
																				  // at the center of
																				  // the screen

		address = key.getAddress().toString();
		LogHandler.write("(Client Handler) Client Handler created", address);
	}

	// This method should take a ClientPacket, extract all the data from it and call actions based on
	// the data received
	public void loadPacket(ClientPacket packet) {
		
		keyCode = packet.getKeyCode();
		LogHandler.write("(Client Handler) Packet Loaded, values are : "+keyCode, address);
		if (!move.isRunning()) {
			move.start();
			LogHandler.write("(Client Handler) Timer started", address);
		}
		
	}
	
	// This method returns the player that is handled by this ClientHandler
	public Player getPlayer() {
		
		return player;
		
	}
	
	// This method returns the ClientKey that was assigned to the ClientHandler on instantiation
	public ClientKey getKey() {
		
		return CLIENT;
		
	}
	
	// This method moves the player a given amount (the value of "shift")
	private void movePlayer(int shift) {
		
		String[] parsedKeyCode = keyCode.split(";");
		
		if (parsedKeyCode[0].equals("true")) { // w
			
			player.shiftY(shift);
			LogHandler.write("(Client Handler) Player moved up", address);
			
		}
		
		if (parsedKeyCode[1].equals("true")) { // a
			
			player.shiftX(-shift);
			LogHandler.write("(Client Handler) Player moved left", address);
			
		}
		
		if (parsedKeyCode[2].equals("true")) { // s
			
			player.shiftY(-shift);
			LogHandler.write("(Client Handler) Player moved down", address);
		}
		
		if (parsedKeyCode[3].equals("true")) { // d
			
			player.shiftX(shift);
			LogHandler.write("(Client Handler) Player moved right", address);
			
		}
		if (shift>0){
			DataHandler.updatePlayer(player);
//			LogHandler.write("Player location updated", address);
		}
	}
	
}
