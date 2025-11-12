import java.net.*;

public class DNSServer {
    public static void main(String[] args) {
        try {
            
            DatagramSocket server = new DatagramSocket(1234);
            System.out.println("DNS Server started...");

            String[] hosts = {"google.com", "yahoo.com", "facebook.com"};
            String[] ips = {"8.8.8.8", "98.137.11.163", "31.13.71.36"};

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                server.receive(receivePacket);

                String domain = new String(receivePacket.getData()).trim();
                System.out.println("Received request for: " + domain);

                String response = "Host Not Found";
                for (int i = 0; i < hosts.length; i++) {
                    if (hosts[i].equalsIgnoreCase(domain)) {
                        response = ips[i];
                        break;
                    }
                }

                sendData = response.getBytes();
                InetAddress clientAddr = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddr, clientPort);
                server.send(sendPacket);

                System.out.println("Sent: " + response + "\n");
                
            }
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}

