import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/*

This class... isn't finished.

*/

public class Main {
	
    public static void main(String[] args){
        
    	new Timer(10000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ServerTest.addClient();
				
			}
    		
    	});

    }

}
