import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Map implements Serializable {

	private static final long serialVersionUID = -3247302376139655273L;
	
	private final int CHUNKWIDTH = 1400;
	private final int CHUNKHEIGHT = 800;
	
	private final int FACTOR = 4;
	
	private final int MAPWIDTH = CHUNKWIDTH * FACTOR;
	private final int MAPHEIGHT = CHUNKHEIGHT * FACTOR;
	
	private HashMap<Integer, ArrayList<Obstacle>> dataMap = new HashMap<Integer, ArrayList<Obstacle>>();
	private HashMap<Integer, ArrayList<Player>> playerMap = new HashMap<Integer, ArrayList<Player>>();
	
	public Map(String fileName) {
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("res/Maps/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (sc.hasNextLine()) {
			
			
			
		}
		
	}
	
	public Color getBackgroundColor() {
		
		return null;
		
	}
	
	public int getMapWidth() {
		
		return MAPWIDTH;
		
	}
	
	public int getMapHeight() {
		
		return MAPHEIGHT;
		
	}
	
	public void addPlayerData(Player p) {
		
		int chunkNum = getChunkNum(getChunkColumn(p.getX()), getChunkRow(p.getY()));
		
		playerMap.get(chunkNum).add(p);
		
	}
	
	public void updatePlayerData(Player oldP, Player p) {
		
		int chunkNum = getChunkNum(getChunkColumn(oldP.getX()), getChunkRow(oldP.getY()));
		
		playerMap.get(chunkNum).remove(oldP);
		
		chunkNum = getChunkNum(getChunkColumn(p.getX()), getChunkRow(p.getY()));
		
		playerMap.get(chunkNum).add(p);
		
	}
	
	public ArrayList<Player> getAllPlayerChunks(int x, int y) {
		
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
	
	public ArrayList<Obstacle> getAllChunks(int x, int y) {
		
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
	
	private ArrayList<Player> getPlayerChunkData(int chunkColumn, int chunkRow) {
		
		int chunkNum = getChunkNum(chunkColumn, chunkRow);
		
		if (playerMap.containsKey(chunkNum)) {
			
			return playerMap.get(chunkNum);
			
		} else {
			
			return null;
			
		}
		
	}
	
	private ArrayList<Obstacle> getChunkData(int chunkColumn, int chunkRow) {
		
		int chunkNum = getChunkNum(chunkColumn, chunkRow);
		
		if (dataMap.containsKey(chunkNum)) {
			
			return dataMap.get(chunkNum);
			
		} else {
			
			return getNullChunk(chunkColumn, chunkRow);
			
		}
		
	}
	
	private int getChunkColumn(int x) {
		
		return (int)Math.ceil(x / (double)CHUNKWIDTH);
		
	}
	
	private int getChunkRow(int y) {
		
		return (int)Math.ceil(y / (double)CHUNKHEIGHT);
		
	}
	
	private int getChunkNum(int col, int row) {
		
		return ((row - 1) * (MAPWIDTH / CHUNKWIDTH)) + col;
		
	}
	
	private ArrayList<Obstacle> getNullChunk(int chunkColumn, int chunkRow) {
		
		ArrayList<Obstacle> temp = new ArrayList<Obstacle>();
		
		temp.add(new Obstacle(chunkColumn * CHUNKWIDTH - CHUNKWIDTH / 2, chunkRow * CHUNKHEIGHT - CHUNKHEIGHT / 2, "NullBlock"));
		
		return temp;
		
	}
	
}

