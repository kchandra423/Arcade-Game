import java.io.Serializable;

public class ClientPacket implements Serializable {

	private static final long serialVersionUID = 4789217797082144125L;
	
	private final String KEYCODE;
	
	private final int MOUSEANGLE;
	
	
	public ClientPacket(String keyCode, int mouseAngle) {
		
		KEYCODE = keyCode;
		MOUSEANGLE = mouseAngle;
		
	}
	
	public String getKeyCode() {
		
		return KEYCODE;
		
	}
	
	public int getMouseAngle() {
		
		return MOUSEANGLE;
		
	}

}
