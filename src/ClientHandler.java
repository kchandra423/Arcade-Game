import java.net.DatagramPacket;

/**should recieve:
    array list of buttons pressed consisting of (w,a,s,d,up,down,left,right,)
    mouse location

 **should send
    an object containing all of the the aspects of the map(including the player)

 */
public class ClientHandler 
{

	DatagramPacket packet;// = new DatagramPacket();
	public ClientHandler()
	{
		packet=new DatagramPacket()
	}

	public void receivePacket(DatagramPacket packet){

	}
	public DatagramPacket getPacket(){
		return null;
	}
}
