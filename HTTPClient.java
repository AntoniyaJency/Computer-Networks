import java.io.*;
import java.net.*;

public class HTTPClient {
    public static void main(String[] args) {
        String host="example.com";
        int port=80;
        String path="/";

        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET " + path + " HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: Close");
            out.println(); 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter file = new BufferedWriter(new FileWriter("output.html"));
        

            String line;
            while ((line = in.readLine()) != null) {
                file.write(line);
                file.newLine();
                System.out.println(line);
            }
            file.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}