import java.net.InetAddress;

public class ClientKey {
	
	private final InetAddress ADDRESS;
	private final int PORT;
	
	public ClientKey (InetAddress address, int port) {
		
		ADDRESS = address;
		PORT = port;
		
	}
	
	public InetAddress getAddress() {
		
		return ADDRESS;
		
	}
	
	public int getPort() {
		
		return PORT;
		
	}

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
