import java.io.*;
import java.net.*;
import java.util.HashMap;

/*

This class is a "static" class which is used to send an receive data on the server's port.

*/

public class PacketHandler {
	
	private static HashMap<ClientKey, ClientHandler> addressBook; // A HashMap which contains all the 
																  // ClientHandlers mapped to a 
																  // ClientKey
	private static DatagramSocket socket; // The socket for the server side
	private static final int PORT_NUMBER = 25565; // The port the socket is attached to
	private static int playerCount = 0; // The total number of connected players, also used to assign
										// player numbers

	static {
		addressBook = new HashMap<ClientKey, ClientHandler>();
		try {
			socket = new DatagramSocket(PORT_NUMBER);
		} catch (SocketException e) {
			LogHandler.write("(Packet Handler) Something went wrong when initializing the socket, " +
					"exact error is: "+e.toString());
			e.printStackTrace();
		}
		startReceiving();
	}

	// This method starts the receiving thread which is the thread that receives all data and then 
	// sends it to the appropriate ClientHandler
	private static void startReceiving() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {

					DatagramPacket receive = getPacket();

					try {

						socket.receive(receive);
						InetAddress address = receive.getAddress();

						ClientKey key = new ClientKey(address, receive.getPort());

						if (!addressBook.containsKey(key)) {

							ClientHandler handler = new ClientHandler(key, playerCount);
							playerCount ++;
							addressBook.put(key, handler);
							LogHandler.write("(Packet Handler) New player connected!", key.getAddress().toString());;

						}
						
						ClientPacket packet = (ClientPacket) deserializeBytes(receive.getData());
						
						LogHandler.write("(Packet Handler) Packet received: " + packet, key.getAddress().toString());
						
						addressBook.get(key).loadPacket(packet);

					} catch (IOException e) {
						LogHandler.write("(Packet Handler) Something went wrong receiving data, " +
								"exact error is: "+e.toString());
						e.printStackTrace();
					}

				}

			}

		}).start();

	}
	
	public static void fakeReceive(ClientPacket packet, InetAddress address, int port) {

		ClientKey key = new ClientKey(address, port);

		if (!addressBook.containsKey(key)) {

			ClientHandler handler = new ClientHandler(key, playerCount);
			playerCount++;
			addressBook.put(key, handler);
			LogHandler.writeReceive("(Packet Handler) New player connected!", key.getAddress().toString());

		}

		LogHandler.writeReceive("(Packet Handler) Packet received: " + packet, key.getAddress().toString());

		addressBook.get(key).loadPacket(packet);

	}

	// This method sends a packet to a given client
	public static void sendPacket(ServerPacket packet, ClientKey key) {
		
		fakeSend(packet, key);
		
//		DatagramPacket dgPacket = getPacket(packet, key);
//		
//		try {
//			socket.send(dgPacket);
//			LogHandler.write("(Packet Handler) Sending packet: " + packet, key.getAddress().toString());
//		} catch (IOException e) {
//			LogHandler.write("(Client handler) Something went wrong sending a packet," +
//					"exact error is: "+e.toString()+
//					" The packet was going to be sent to:"+key.getAddress().toString());
//			e.printStackTrace();
//		}

	}
	
	private static void fakeSend(ServerPacket packet, ClientKey key) {
		
		LogHandler.writeSend(packet.toString(), key.getAddress().toString());
		
	}

	// This method is a helper method used to generate blank packets which are used to write data into from 
	// the receiver thread
	private static DatagramPacket getPacket() {

		byte[] buffer = new byte[8192];

		return new DatagramPacket(buffer, buffer.length);

	}

	// This method is a helper method used to get a DatagramPacket which can be sent to the client 
	// given a ServerPacket and ClientKey
	private static DatagramPacket getPacket(ServerPacket packet, ClientKey key) {

		byte[] data = serializeObject(packet);

		return new DatagramPacket(data, data.length, key.getAddress(), key.getPort());

	}

	// This method is a helper method used to serialize an object into a byte array
	private static byte[] serializeObject(Object o) {

		byte[] answer = new byte[1];

		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(b);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			oos.writeObject(o);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		answer = b.toByteArray();

		try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return answer;

	}

	// This method is a helper method used to deserialize a byte array into an Object
	private static Object deserializeBytes(byte[] data) {

		Object answer = null;
		ByteArrayInputStream b = new ByteArrayInputStream(data);
		ObjectInputStream o = null;
		try {
			o = new ObjectInputStream(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			answer = o.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			o.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;

	}
}
