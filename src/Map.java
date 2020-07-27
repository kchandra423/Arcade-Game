import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Map implements Serializable {

	private static final long serialVersionUID = -3247302376139655273L;
	
	private final int MAPWIDTH = 4200;
	private final int MAPHEIGHT = 2400;
	
	private final int CHUNKWIDTH = 200;
	
	private HashMap<Integer, ArrayList<Obstacle>> dataMap = new HashMap<Integer, ArrayList<Obstacle>>();
	
	public Map(String fileName) {
		
		
		
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
	
	public ArrayList<Obstacle> getChunkData(int chunkColumn, int chunkRow) {
		
		if (dataMap.containsKey(chunkColumn * chunkRow)) {
			
			return dataMap.get(chunkColumn * chunkRow);
			
		} else {
			
			return getNullChunk(chunkColumn, chunkRow);
			
		}
		
	}
	
	private ArrayList<Obstacle> getNullChunk(int chunkColumn, int chunkRow) {
		
		ArrayList<Obstacle> temp = new ArrayList<Obstacle>();
		
		temp.add(new Obstacle(chunkColumn * CHUNKWIDTH - CHUNKWIDTH / 2, chunkRow * CHUNKWIDTH - CHUNKWIDTH / 2, "NullBlock"));
		
		return temp;
		
	}
	
}

