import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class LogHandler 
{
	static FileWriter writer;
	static 
	{
		try 
		{
			writer = new FileWriter("res\\Logs\\ClientLog.txt");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void write (String text)
	{
		try 
		{
			writer.write(String.format("[%s] %s\n", LocalDateTime.now(), text));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void write (String text, Socket socket)
	{
		try 
		{
			writer.write(String.format("[%s] {%s} %s\n ", LocalDateTime.now(), socket.getInetAddress(), text));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
		
}
