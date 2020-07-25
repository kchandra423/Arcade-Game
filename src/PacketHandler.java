import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedList;

public class PacketHandler {
	private HashMap<ClientKey, ClientHandler> addressBook;
//    private LinkedList<InetAddress> phonebook;
	private DatagramSocket socket;
	private final int PORT_NUMBER = 25565;

	public PacketHandler() {
		addressBook = new HashMap<>();
		try {
			socket = new DatagramSocket(PORT_NUMBER);
		} catch (SocketException e) {
			LogHandler.write("Something went wrong when initializing the socket");
			e.printStackTrace();
		}

	}

	private void startReceiving() {

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

							ClientHandler handler = new ClientHandler(key);
							addressBook.put(key, handler);

						}
						
						ClientPacket packet = (ClientPacket) deserializeBytes(receive.getData());
						
						addressBook.get(key).loadPacket(packet);

					} catch (IOException e) {
						LogHandler.write("Something went wrong receiving information");
						e.printStackTrace();
					}

				}

			}

		}).start();

	}

//	private void startSending() {
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				Timer timer = new Timer(1000 / 60, new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//
//						ClientKey[] addressArray = (ClientKey[]) addressBook.keySet().toArray();
//
//						for (int i = 0; i < addressBook.size(); i++) {
//
//							DatagramPacket data = addressBook.get(addressArray[i]).getPacket();
//
//							sendPacket(data);
//
//						}
//
//					}
//				});
//				timer.start();
//			}
//
//		}).start();
//	}

	private void sendPacket(DatagramPacket packet) {

		try {
			socket.send(packet);
		} catch (IOException e) {
			LogHandler.write("Something went wrong sending a packet");
			e.printStackTrace();
		}

	}

	private static DatagramPacket getPacket() {

		byte[] buffer = new byte[8192];

		return new DatagramPacket(buffer, buffer.length);

	}

	private static DatagramPacket getPacket(ClientPacket packet, InetAddress clientAddress, int clientPort) {

		byte[] data = serializeObject(packet);

		return new DatagramPacket(data, data.length, clientAddress, clientPort);

	}

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
