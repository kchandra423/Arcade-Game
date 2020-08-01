import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*

This class holds all the data for anything that is on the game map. This includes both obstacles and
players. It performs the all of the calculations needed to find things nearby to a given player.

*/

public class Map implements Serializable {

	private static final long serialVersionUID = -3247302376139655273L;
	
	private final static int CHUNKWIDTH = 1400; // The width of one chunk (should be the width of the 
												// screen)
	private final static int CHUNKHEIGHT = 800; // The height of one chunk (should be the height of the 
	// screen)
	
	private final static int FACTOR = 4; // How rows/columns of chunks are in the map
	
	private final static int MAPWIDTH = CHUNKWIDTH * FACTOR; // The width of the entire map
	private final static int MAPHEIGHT = CHUNKHEIGHT * FACTOR; // The height of the entire map
	
	// The HashMap that holds the data for all the obstacles in the game corresponding to the chunks
	// they are in
	private HashMap<Integer, ArrayList<Obstacle>> dataMap = new HashMap<Integer, ArrayList<Obstacle>>();
	// The HashMap that holds all of the players that are in the game corresponding to the chunk they
	// are in
	private HashMap<Integer, ArrayList<Player>> playerMap = new HashMap<Integer, ArrayList<Player>>();
	
	public Map(String fileName) {
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("res/Maps/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (sc.hasNextLine()) { // Reads through all the lines of the Map data file and stores all
								   // the data that is needed for a map
			
			
			
		}
		
	}
	
	// This method returns the background color of this map
	public Color getBackgroundColor() {
		
		return null;
		
	}
	
	// This method returns the width of the entire map
	public static int getMapWidth() {
		
		return MAPWIDTH;
		
	}
	
	// This method returns the width of the entire map
	public static int getMapHeight() {
		
		return MAPHEIGHT;
		
	}
	
	// This method adds the data for a new player to the player HashMap
	public void addPlayerData(Player p) {
		
		int chunkNum = getChunkNum(getChunkColumn(p.getX()), getChunkRow(p.getY()));
		
		playerMap.get(chunkNum).add(p);
		
	}
	
	// This method updates the data for an existing player to the player HashMap
	public void updatePlayerData(Player oldP, Player p) {
		
		int chunkNum = getChunkNum(getChunkColumn(oldP.getX()), getChunkRow(oldP.getY()));
		
		playerMap.get(chunkNum).remove(oldP);
		
		chunkNum = getChunkNum(getChunkColumn(p.getX()), getChunkRow(p.getY()));
		
		playerMap.get(chunkNum).add(p);
		
	}
	
	// This method returns all the players that are in the perspective of the given player
	public ArrayList<Player> getAllPlayerChunks(Player p) {
		
		int x = p.getX();
		int y = p.getY();
		
		int chunkColumn = getChunkColumn(x);
		int chunkRow = getChunkRow(y);
		
		ArrayList<Player> data = new ArrayList<Player>();
		
		int [] nums = {0, 1, -1, 0, 0, 1, 1, -1, -1};
		int [] nums2 = {0, 0, 0, 1, -1, 1, -1, 1, -1};
		
		Rectangle perspective = new Rectangle(x - CHUNKWIDTH / 2, y - CHUNKHEIGHT / 2, CHUNKWIDTH, CHUNKHEIGHT);
		
		for (int k = 0; k < nums.length; k ++) {
			
			for (Player i : getPlayerChunkData(chunkColumn + nums[k], chunkRow + nums2[k])) {
				
				if (i == null) {
					break;
				}
				
				if (i.getCollider().intersects(perspective)) {
					data.add(i);
				}
				
			}
		
		}
		
		return data;
		
	}
	
	// This method returns all of the obstacles in the perspective of the given player
	public ArrayList<Obstacle> getAllChunks(Player p) {
		
		int x = p.getX();
		int y = p.getY();
		
		int chunkColumn = getChunkColumn(x);
		int chunkRow = getChunkRow(y);
		
		ArrayList<Obstacle> data = new ArrayList<Obstacle>();
		
		int [] nums = {0, 1, -1, 0, 0, 1, 1, -1, -1};
		int [] nums2 = {0, 0, 0, 1, -1, 1, -1, 1, -1};
		
		Rectangle perspective = new Rectangle(x - CHUNKWIDTH / 2, y - CHUNKHEIGHT / 2, CHUNKWIDTH, CHUNKHEIGHT);
		
		for (int k = 0; k < nums.length; k ++) {
			
			for (Obstacle i : getChunkData(chunkColumn + nums[k], chunkRow + nums2[k])) {
				
				if (i == null) {
					break;
				}
				
				if (i.getCollider().intersects(perspective)) {
					data.add(i);
				}
				
			}
		
		}
		
		return data;
		
	}
	
	// This method is a helper method for the getAllPlayerChunks method and returns all the players in
	// a given chunk
	private ArrayList<Player> getPlayerChunkData(int chunkColumn, int chunkRow) {
		
		int chunkNum = getChunkNum(chunkColumn, chunkRow);
		
		if (playerMap.containsKey(chunkNum)) {
			
			return playerMap.get(chunkNum);
			
		} else {
			
			return null;
			
		}
		
	}
	
	// This method is a helper method for the getAllChunks method and returns all the obstacles in a
	// given chunk
	private ArrayList<Obstacle> getChunkData(int chunkColumn, int chunkRow) {
		
		int chunkNum = getChunkNum(chunkColumn, chunkRow);
		
		if (dataMap.containsKey(chunkNum)) {
			
			return dataMap.get(chunkNum);
			
		} else {
			
			return getNullChunk(chunkColumn, chunkRow);
			
		}
		
	}
	
	// This method is a helper method which calculates the column number for a chunk given an x
	// coordinate
	private int getChunkColumn(int x) {
		
		return (int)Math.ceil(x / (double)CHUNKWIDTH);
		
	}
	
	// This method is a helper method which calculates the row number for a chunk given a y coordinate
	private int getChunkRow(int y) {
		
		return (int)Math.ceil(y / (double)CHUNKHEIGHT);
		
	}
	
	// This method is a helper method which returns the chunk number given the column and row for a 
	// chunk
	private int getChunkNum(int col, int row) {
		
		return ((row - 1) * (MAPWIDTH / CHUNKWIDTH)) + col;
		
	}
	
	// This method is a helper method for the getChunkData method and returns a "null" chunk if the 
	// chunk requested is not in the map. This is used because when a player reaches the end of the 
	// map they will request a non-existing chunk in which case this method will be called
	private ArrayList<Obstacle> getNullChunk(int chunkColumn, int chunkRow) {
		
		ArrayList<Obstacle> temp = new ArrayList<Obstacle>();
		
		temp.add(new Obstacle(chunkColumn * CHUNKWIDTH - CHUNKWIDTH / 2, chunkRow * CHUNKHEIGHT - CHUNKHEIGHT / 2, "NullBlock"));
		
		return temp;
		
	}
	
}

