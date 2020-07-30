import java.util.ArrayList;
import java.util.HashMap;

public class DataHandler {
	
	private static Map gameMap = new Map("DefaultMap.txt");
	
	private static HashMap<Integer, Player> allPlayers = new HashMap<Integer, Player>();
	
	public void addPlayer(Player p) {
		
		allPlayers.put(p.getPNum(), p);
		gameMap.addPlayerData(p);
		
		ArrayList<Player> sendPlayers = gameMap.getAllPlayerChunks(p); // This includes the main player (p)
		
		for (int i = 0; i < sendPlayers.size(); i ++) {
			
			sendPlayerUpdate(sendPlayers.get(i));
			
		}
		
	}
	
	public void updatePlayer(Player p) {
		
		gameMap.updatePlayerData(allPlayers.get(p.getPNum()), p);
		allPlayers.put(p.getPNum(), p);
		
		ArrayList<Player> sendPlayers = gameMap.getAllPlayerChunks(p);
		
		for (int i = 0; i < sendPlayers.size(); i ++) {
			
			sendPlayerUpdate(sendPlayers.get(i));
			
		}
		
	}
	
	private void sendPlayerUpdate(Player p) {
		
		// Call PacketHandler here, the only thing missing is the ClientKey corresponding to the Player
		// so we need to figure that out somehow
		
	}

}
