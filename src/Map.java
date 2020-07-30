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

