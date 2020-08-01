import java.io.Serializable;

/*

This class is used to hold all the data the client will send in one object so data transfer is a lot
easier and more organized.

*/
/* Keycode Abbreviations (saves on memory~ish)
	 * RP --> right pressed
	 * RR --> right released
	 * UP --> up pressed
	 * UR --> up released
	 * LP --> left pressed
	 * LR --> left released
	 * DP --> down pressed
	 * DR --> down released
*/
	

public class ClientPacket implements Serializable {

	private static final long serialVersionUID = 4789217797082144125L;
	
	private final String KEYCODE; // The key code that represents which keys are being pressed on the 
								  // client side
	
	private final double MOUSEANGLE; // The angle of the mouse in relation to the center of the screen on
								  // the client side
	
	
	public ClientPacket(String keyCode, double mouseAngle) {
		
		KEYCODE = keyCode;
		MOUSEANGLE = mouseAngle;
		
	}
	
	// This method returns the key code that was given on instantiation
	public String getKeyCode() {
		
		return KEYCODE;
		
	}
	
	// This method returns the mouse angle that was given on instantiation
	public double getMouseAngle() {
		
		return MOUSEANGLE;
		
	}

}
