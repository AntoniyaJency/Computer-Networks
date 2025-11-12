// RARP_Simulation.java
import java.util.*;

public class RARP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ip[] = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
        String mac[] = {"AA:BB:CC:DD:EE:01", "AA:BB:CC:DD:EE:02", "AA:BB:CC:DD:EE:03"};

        System.out.print("Enter MAC Address: ");
        String macAddr = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < mac.length; i++) {
            if (macAddr.equalsIgnoreCase(mac[i])) {
                System.out.println("IP Address: " + ip[i]);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("IP Address not found for given MAC.");

        sc.close();
    }
}
