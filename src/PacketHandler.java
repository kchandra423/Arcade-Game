import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

public class PacketHandler {
    private HashMap<InetAddress, ClientHandler> addressBook;
    private DatagramSocket socket;
    private final int PORT_NUMBER=25565;
    public PacketHandler(){
            addressBook=new HashMap();
        try {
            socket=new DatagramSocket(PORT_NUMBER);
        } catch (SocketException e) {
            System.out.println("Something went wrong when initializing the socket");
            e.printStackTrace();
        }

    }
    private void startReceiving(){


            new Thread(new Runnable() {

                @Override
                public void run() {

                    while (true) {

                        DatagramPacket receive = getPacket();

                        try {

                            socket.receive(receive);
                            InetAddress address = receive.getAddress();
                            if(!addressBook.containsKey(address)){
                                ClientHandler handler= new ClientHandler();
                                addressBook.put(address,handler);

                            }
//                            ClientPacket packet = (ClientPacket) deserializeBytes(receive.getData());
                            addressBook.get(address).receivePacket(receive);





                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }).start();

        }
        private void sendPacket(DatagramPacket packet){


            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    private static DatagramPacket getPacket() {

        byte[] buffer = new byte[8192];

        return new DatagramPacket(buffer, buffer.length);

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
