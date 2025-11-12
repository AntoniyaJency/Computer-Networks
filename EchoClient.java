import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try {
            
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server. Type 'exit' to quit.");

          
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;

            
            while (true) {
                System.out.print("You: ");
                message = userInput.readLine();

                if (message.equalsIgnoreCase("exit"))
                    break;

                out.println(message);
                System.out.println(in.readLine());
            }

            // Close connection
            socket.close();
            System.out.println("Disconnected from server.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
