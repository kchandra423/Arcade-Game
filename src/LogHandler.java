import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/*

This class handles all the writing to the server log. Any major calculation/event should be logged and
will come through this class to be written to the text file.

*/

public class LogHandler 
{
	private static FileWriter writer;
	static {
		try {
			writer = new FileWriter("res/Logs/ClientLog.txt");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// This method writes any general event that the server performed to the log
	public static void write (String text) {
		try {
			writer.write(String.format("[%s] %s\n", LocalDateTime.now(), text));
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// This method writes an individual client event that the server processed to the log
	public static void write (String text, String address) {
		try {
			writer.write(String.format("[%s] {%s} %s\n", LocalDateTime.now(), address, text));
			writer.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
		
}
