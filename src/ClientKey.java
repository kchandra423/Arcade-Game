import java.net.InetAddress;

/*

This class holds an InetAddress and port that should correspond to a client that has connected to the
server. It is also designed to be used in a HashMap hence the use of the hashCode and equals methods 
being overridden.

*/

public class ClientKey {
	
	private final InetAddress ADDRESS; // The IP address for the client
	private final int PORT; // The port that the client connected from
	
	public ClientKey (InetAddress address, int port) {
		
		ADDRESS = address;
		PORT = port;
		
	}
	
	// This method returns the InetAddress that the ClientKey was instantiated with
	public InetAddress getAddress() {
		
		return ADDRESS;
		
	}
	
	// This method returns the port that the ClientKey was instantiated with
	public int getPort() {
		
		return PORT;
		
	}

	// These two methods are overridden so that the class can be used as a key in a HashMap and can 
	// also be seen as equal to another instance of it as long as the port and InetAddress are the same
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ADDRESS == null) ? 0 : ADDRESS.hashCode());
		result = prime * result + PORT;
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
		ClientKey other = (ClientKey) obj;
		if (ADDRESS == null) {
			if (other.ADDRESS != null)
				return false;
		} else if (!ADDRESS.equals(other.ADDRESS))
			return false;
		if (PORT != other.PORT)
			return false;
		return true;
	}
	
}
