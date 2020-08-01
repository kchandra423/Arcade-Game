import java.awt.Polygon;

/*

This class is used to hold all the data for one obstacle.

*/

public class Obstacle {
	
	private final int X;
	private final int Y;
	
	public Obstacle(int xCord, int yCord, String imagePathName) {
		
		X = xCord;
		Y = yCord;
		
	}
	
	// This method returns the x coordinate for the obstacle
	public int getX() {
		
		return X;
		
	}
	
	// This method returns the y coordinate for the obstacle
	public int getY() {
		
		return Y;
		
	}
	
	// This method returns the polygon outlining the obstacle
	public Polygon getCollider() {
		
		return null;
		
	}

}
