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
                
                System.out.print("You: ");
                msgFromClient = userInput.readLine();
                out.println(msgFromClient);

                msgFromServer = in.readLine();
                
                System.out.println("Server: " + msgFromServer);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
