import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server. Type 'exit' to quit.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msgFromServer, msgFromClient;

            while (true) {
                // Send message to server
                System.out.print("You: ");
                msgFromClient = userInput.readLine();
                out.println(msgFromClient);

                if (msgFromClient.equalsIgnoreCase("exit")) {
                    System.out.println("Chat ended.");
                    break;
                }

                // Receive message from server
                msgFromServer = in.readLine();
                if (msgFromServer == null || msgFromServer.equalsIgnoreCase("exit")) {
                    System.out.println("Server disconnected.");
                    break;
                }
                System.out.println("Server: " + msgFromServer);
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
