import java.util.ArrayList;
import java.util.HashMap;

/*

This class handles the data from all individual ClientHandlers in one central place. It allows for 
calculations as to which clients need to receive updates and when. All updates that a ClientHandler
requests have to go through this class before being sent to the PacketHandler.

*/

public class DataHandler {
	
	private static Map gameMap = new Map("DefaultMap.txt"); // The map for the game, this holds all the
															// obstacles and other things needed to 
															// render the map
	
	private static HashMap<Integer, Player> allPlayers = new HashMap<Integer, Player>(); // A HashMap
																						 // which 
																						 // contains 
																						 // all 
																						 // connected
																						 // players
																						 // sorted by
																						 // their 
																						 // player 
																						 // numbers
	
	// This method adds a new player to the data list of all players currently in the game
	public void addPlayer(Player p) {
		
		allPlayers.put(p.getPNum(), p);
		gameMap.addPlayerData(p);
		
		ArrayList<Player> sendPlayers = gameMap.getAllPlayerChunks(p); // This includes the main player (p)
		
		for (int i = 0; i < sendPlayers.size(); i ++) {
			
			sendPlayerUpdate(sendPlayers.get(i));
			
		}
		
	}
	
	// This method updates the location/state of a player that is already in the game
	public void updatePlayer(Player p) {
		
		gameMap.updatePlayerData(allPlayers.get(p.getPNum()), p);
		allPlayers.put(p.getPNum(), p);
		
		ArrayList<Player> sendPlayers = gameMap.getAllPlayerChunks(p);
		
		for (int i = 0; i < sendPlayers.size(); i ++) {
			
			sendPlayerUpdate(sendPlayers.get(i));
			
		}
		
	}
	
	// This method gets the corresponding ClientKey for the player and sends a ServerPacket with all 
	// the updated information in it through the PacketHandler
	private void sendPlayerUpdate(Player p) {
		
		// Call PacketHandler here, the only thing missing is the ClientKey corresponding to the Player
		// so we need to figure that out somehow
		
	}

}
