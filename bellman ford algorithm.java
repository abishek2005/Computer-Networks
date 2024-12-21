import java.util.Scanner;
public class bellmanford{
    public static void bellmanFord(int[][] graph, int V, int E, int src) 
{
        int[] dist = new int[V];
        // Step 1: Initialize distances from src to all other vertices as INFINITE
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;
        // Step 2: Relax all edges |V| - 1 times.
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = graph[j][0];
                int v = graph[j][1];
                int weight = graph[j][2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
        // Step 3: Check for negative-weight cycles.
        for (int j = 0; j < E; j++) {
            int u = graph[j][0];
            int v = graph[j][1];
            int weight = graph[j][2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }
        printSolution(dist, V);
    }
    public static void printSolution(int[] dist, int V) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = sc.nextInt();
        int[][] graph = new int[E][3];
        System.out.println("Enter the edges with source, destination, and weight:");
        for (int i = 0; i < E; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            graph[i][0] = sc.nextInt(); // Source vertex
            graph[i][1] = sc.nextInt(); // Destination vertex
            graph[i][2] = sc.nextInt(); // Weight of the edge
        }
        System.out.print("Enter the source vertex: ");
        int src = sc.nextInt();
        bellmanFord(graph, V, E, src);
       sc.close();
    }
}
