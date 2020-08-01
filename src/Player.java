import java.awt.Rectangle;

/*

This class represents a player and is used to store the data about any one player.

*/

public class Player {
	
	private int x;
	private int y;
	private final int PNUM;
	private int width = 20;
	
	public Player(int x, int y, int playerNumber) {
		
		this.x = x;
		this.y = y;
		PNUM = playerNumber;
		
	}
	
	// This method returns a clone of this player 
	public Player clone() {
		
		return new Player(x, y, PNUM);
		
	}
	
	// This method returns the width of the player
	public int getWidth() {
		
		return width;
		
	}
	
	// This method returns the x coordinate of the player
	public int getX() {
		
		return x;
		
	}
	
	// This method returns the y coordinate of the player
	public int getY() {
		
		return y;
		
	}
	
	// This method shifts the player's x coordinate by the given shift amount
	public void shiftX(int shift) {
		
		x += shift;
		
	}
	
	// This method shifts the player's y coordinate by the given shift amount
	public void shiftY(int shift) {
		
		y += shift;
		
	}
	
	// This method returns the player number of this player
	public int getPNum() {
		
		return PNUM;
		
	}
	
	// This method returns the outlining rectangle of this player
	public Rectangle getCollider() {
		
		return new Rectangle(x, y, width, width);
		
	}

	// These two methods are overridden so the methods called on a HashMap would work
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + PNUM;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (PNUM != other.PNUM)
			return false;
		return true;
	}

}
