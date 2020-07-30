import java.awt.Rectangle;

public class Player {
	
	private boolean mainPlayer = false;
	private int x;
	private int y;
	private final int PNUM;
	private int width = 20;
	
	public Player(int x, int y, int playerNumber) {
		
		this.x = x;
		this.y = y;
		PNUM = playerNumber;
		
	}
	
	public Player clone() {
		
		return new Player(x, y, PNUM);
		
	}
	
	public void setAsMainPlayer() {
		
		mainPlayer = true;
		
	}
	
	public boolean isMainPlayer() {
		
		return mainPlayer;
		
	}
	
	public int getWidth() {
		
		return width;
		
	}
	
	public int getX() {
		
		return x;
		
	}
	
	public int getY() {
		
		return y;
		
	}
	
	public void shiftX(int shift) {
		
		x += shift;
		
	}
	
	public void shiftY(int shift) {
		
		y += shift;
		
	}
	
	public Rectangle getCollider() {
		
		return new Rectangle(x, y, width, width);
		
	}

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
