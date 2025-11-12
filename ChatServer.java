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
               
                msgFromClient = in.readLine();
                
                System.out.println("Client: " + msgFromClient);

                System.out.print("You: ");
                msgFromServer = serverInput.readLine();
                out.println(msgFromServer);

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
