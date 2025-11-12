import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter data bits: ");
        String data = sc.nextLine();

        System.out.print("Enter divisor (generator): ");
        String divisor = sc.nextLine();

        // Append zeros to data (length of divisor - 1)
        String dividend = data;
        for (int i = 0; i < divisor.length() - 1; i++)
            dividend = dividend +"0";

        String remainder = divide(dividend, divisor);

        // Append remainder to data to get transmitted frame
        String transmitted = data + remainder;
        System.out.println("Transmitted Codeword: " + transmitted);

        // Receiver side: check if error occurred
        String receiverRemainder = divide(transmitted, divisor);
        if (receiverRemainder.contains("1"))
            System.out.println("Error detected in received data!");
        else
            System.out.println("No error detected.");

        sc.close();
    }

    // Function to perform XOR division
    static String divide(String dividend, String divisor) {
        char[] result = dividend.toCharArray();
        int len = divisor.length();

        for (int i = 0; i <= result.length - len; i++) {
            if (result[i] == '1') {
                for (int j = 0; j < len; j++) {
                    result[i + j] = (result[i + j] == divisor.charAt(j)) ? '0' : '1';
                }
            }
        }

        // Extract remainder (last len-1 bits)
        return new String(result).substring(result.length - len + 1);
    }
}
