
public class Player {
	
	private boolean mainPlayer = false;
	private int x;
	private int y;
	private int width = 20;
	
	public Player(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	public Player clone() {
		
		return new Player(x, y);
		
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

}
