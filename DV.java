import java.io.*;
import java.util.Scanner;

public class DV {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int n = sc.nextInt();
        int[][] cost = new int[n][n], 
        distance = new int[n][n], 
        nextHop = new int[n][n];

        System.out.println("Enter the cost matrix (each row on a separate line, values separated by spaces):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] =  sc.nextInt();
                if (cost[i][j] == 0 && i != j) 
                    cost[i][j] = 999;
                distance[i][j] = cost[i][j];
                nextHop[i][j] = j;
            }
        }

        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (distance[i][j] > cost[i][k] + distance[k][j]) {
                            distance[i][j] = cost[i][k] + distance[k][j];
                            nextHop[i][j] = k;
                            changed = true;
                        }
                    }
                }
            }
        } while (changed);

        // Display the routing tables
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouting table for node " + (i + 1) + ":");
            System.out.println("Destination\tNext Hop\tCost");
            for (int j = 0; j < n; j++) {
                System.out.println((j + 1) + "\t\t" + (nextHop[i][j] + 1) + "\t\t" + distance[i][j]);
            }
        }
        sc.close();
    }
}
