// ARP_Simulation.java
import java.util.*;

public class ARP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ip[] = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};
        String mac[] = {"AA:BB:CC:DD:EE:01", "AA:BB:CC:DD:EE:02", "AA:BB:CC:DD:EE:03"};

        System.out.print("Enter IP Address: ");
        String ipAddr = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < ip.length; i++) {
            if (ipAddr.equals(ip[i])) {
                System.out.println("MAC Address: " + mac[i]);
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("MAC Address not found for given IP.");

        sc.close();
    }
}
