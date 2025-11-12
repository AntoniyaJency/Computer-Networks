import java.io.*;
import java.net.*;

public class CRCClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        System.out.print("Enter data bits: ");
        String data = br.readLine();

        System.out.print("Enter generator bits: ");
        String generator = br.readLine();

        // Append CRC remainder to data
        String codeword = generateCRC(data, generator);
        System.out.println("Transmitted Codeword: " + codeword);

        // Send to server
        out.println(codeword);
        out.println(generator);

        s.close();
    }

    // Generate CRC codeword
    public static String generateCRC(String data, String generator) {
        int n = generator.length();
        String appendedData = data + "0".repeat(n - 1);
        String remainder = divide(appendedData, generator);
        return data + remainder;
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
