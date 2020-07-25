import java.io.Serializable;
import java.util.ArrayList;

public class ServerPacket implements Serializable {
	
	private final Map GAMEMAP;
	
	private final ArrayList<Player> PLAYERS;
	
	private final Player MAINPLAYER;
	
	private final int PACKETNUM;

	private static final long serialVersionUID = 9108779363102530646L;
	
	public ServerPacket(Map theMap, ArrayList<Player> allOtherPlayers, Player mainPlayer, int packetNumber) {
		
		this.MAINPLAYER = mainPlayer;
		PLAYERS = allOtherPlayers;
		GAMEMAP = theMap;
		PACKETNUM = packetNumber;
		
	}
	
	public Map getMap() {
		
		return GAMEMAP;
		
	}
	
	public Player getMainPlayer() {
		
		return MAINPLAYER;
		
	}
	
	public ArrayList<Player> getAllOtherPlayers(){
		
		return PLAYERS;
		
	}
	
	public int getPacketNumber() {
		
		return PACKETNUM;
		
	}

}
