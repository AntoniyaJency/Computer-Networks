import java.io.*;
import java.net.*;

public class CRCServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server started. Waiting for client...");

        Socket s = ss.accept();
        System.out.println("Client connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String receivedData = in.readLine();
        String generator = in.readLine();

        System.out.println("Received data from client: " + receivedData);
        System.out.println("Generator polynomial: " + generator);

        // Perform CRC check
        if (checkCRC(receivedData, generator)) {
            System.out.println("No Error detected in received data.");
        } else {
            System.out.println("Error detected in received data!");
        }

        ss.close();
        s.close();
    }

    // CRC checking function
    public static boolean checkCRC(String data, String generator) {
        String remainder = divide(data, generator);
        // If remainder is all zeros, no error
        return !remainder.contains("1");
    }

    // XOR division
    public static String divide(String data, String generator) {
        int n = generator.length();
        char[] result = data.toCharArray();

        for (int i = 0; i <= result.length - n; i++) {
            if (result[i] == '1') {
                for (int j = 0; j < n; j++) {
                    result[i + j] = (result[i + j] == generator.charAt(j)) ? '0' : '1';
                }
            }
        }

        // Return remainder (last n-1 bits)
        return new String(result, result.length - (n - 1), n - 1);
    }
}
