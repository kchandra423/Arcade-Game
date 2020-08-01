import java.io.Serializable;
import java.util.ArrayList;

/*

This class is used to hold all the data needed to be sent from the server to the client.

*/

public class ServerPacket implements Serializable {
	
	private final ArrayList<Obstacle> OBSTACLES;
	
	private final ArrayList<Player> PLAYERS;
	
	private final Player MAINPLAYER;
	
	private final int PACKETNUM;

	private static final long serialVersionUID = 9108779363102530646L;
	
	public ServerPacket(ArrayList<Obstacle> obstacles, ArrayList<Player> allOtherPlayers, Player mainPlayer, int packetNumber) {
		
		this.MAINPLAYER = mainPlayer;
		PLAYERS = allOtherPlayers;
		OBSTACLES = obstacles;
		PACKETNUM = packetNumber;
		
	}
	
	// This method returns all the obstacles that are in the client's perspective
	public ArrayList<Obstacle> getObstacles() {
		
		return OBSTACLES;
		
	}
	
	// This method returns which player being drawn is the "main" player/the client's player
	public Player getMainPlayer() {
		
		return MAINPLAYER;
		
	}
	
	// This method returns an ArrayList of all the other players that need to be drawn
	public ArrayList<Player> getAllOtherPlayers() {
		
		return PLAYERS;
		
	}
	
	// This method returns the packet number of this specific ServerPacket. The packet number is used
	// to tell whether or not a packet has been lost.
	public int getPacketNumber() {
		
		return PACKETNUM;
		
	}

}
