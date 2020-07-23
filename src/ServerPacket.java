import java.io.Serializable;
import java.util.ArrayList;

public class ServerPacket implements Serializable {
	
	private Map gameMap;
	
	private ArrayList<Player> players;
	
	private Player mainPlayer;

	private static final long serialVersionUID = 9108779363102530646L;
	
	public ServerPacket(Map theMap, ArrayList<Player> allOtherPlayers, Player mainPlayer) {
		
		this.mainPlayer = mainPlayer;
		players = allOtherPlayers;
		gameMap = theMap;
		
	}
	
	public Map getMap() {
		
		return gameMap;
		
	}
	
	public Player getMainPlayer() {
		
		return mainPlayer;
		
	}
	
	public ArrayList<Player> getAllOtherPlayers(){
		
		return players;
		
	}

}
