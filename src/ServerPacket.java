import java.io.Serializable;
import java.util.ArrayList;

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
	
	public ArrayList<Obstacle> getObstacles() {
		
		return OBSTACLES;
		
	}
	
	public Player getMainPlayer() {
		
		return MAINPLAYER;
		
	}
	
	public ArrayList<Player> getAllOtherPlayers() {
		
		return PLAYERS;
		
	}
	
	public int getPacketNumber() {
		
		return PACKETNUM;
		
	}

}
