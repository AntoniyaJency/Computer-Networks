import java.io.*;
import java.net.*;

public class DNSClient {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress serverAddr = InetAddress.getByName("localhost");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.println("DNS Client started. Type a domain name (type 'exit' to quit):");

            while (true) {
                System.out.print("Enter domain: ");
                String domain = userInput.readLine();

                if (domain.equalsIgnoreCase("exit"))
                    break;

                sendData = domain.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, 1234);
                client.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                client.receive(receivePacket);

                String response = new String(receivePacket.getData()).trim();
                System.out.println("IP Address: " + response + "\n");
            }

            client.close();
            System.out.println("Client closed.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
