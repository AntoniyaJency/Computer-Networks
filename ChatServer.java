 import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Server started. Waiting for client...");

            Socket client = server.accept();
            System.out.println("Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            String msgFromClient, msgFromServer;

            while (true) {
                // Receive message from client
                msgFromClient = in.readLine();
                if (msgFromClient == null || msgFromClient.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + msgFromClient);

                // Send message to client
                System.out.print("You: ");
                msgFromServer = serverInput.readLine();
                out.println(msgFromServer);

                if (msgFromServer.equalsIgnoreCase("exit")) {
                    System.out.println("Chat ended.");
                    break;
                }
            }

            client.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
