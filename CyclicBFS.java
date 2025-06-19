import java.util.*;

public class CyclicBFS {
    static boolean bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    queue.add(neighbor);
                } else if (parent[node] != neighbor) {
                    System.out.println("Cycle found between: " + node + " and " + neighbor);
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (sc.nextInt() == 1)
                    graph.get(i).add(j);

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++)
            if (!visited[i] && bfs(i, graph, visited))
                return;

        System.out.println("No cycle found.");
    }
}
