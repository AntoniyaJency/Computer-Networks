import java.util.*;

public class LinkStateRouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int n = sc.nextInt();

        int[] pred = new int[n];
        int[] distance = new int[n];
        int[][] matrix = new int[n][n];
        int[] visited = new int[n];

        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] == 0 && i != j) matrix[i][j] = 999;
            }
        }

        for (int i = 0; i < n; i++) {
            distance[i] = matrix[0][i];
            pred[i] = 0;
            visited[i] = 0;
        }
        distance[0] = 0;
        visited[0] = 1;

        for (int count = 1; count < n; count++) {
            int min = 999, nextNode = 0;
            for (int i = 0; i < n; i++) {
                if (distance[i] < min && visited[i] == 0) {
                    min = distance[i];
                    nextNode = i;
                }
            }
            visited[nextNode] = 1;
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0 && min + matrix[nextNode][i] < distance[i]) {
                    distance[i] = min + matrix[nextNode][i];
                    pred[i] = nextNode;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            System.out.print("Path to node " + i + " = " + i);
            int j = i;
            while (j != 0) {
                j = pred[j];
                System.out.print(" <- " + j);
            }
            System.out.println("\nCost = " + distance[i]);
        }
        sc.close();
    }
}
