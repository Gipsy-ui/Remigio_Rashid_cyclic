import java.util.*;

public class CyclicDFS {
    static boolean dfs(int node, boolean[] visited, boolean[] recStack, List<List<Integer>> graph, List<Integer> path) {
        visited[node] = true;
        recStack[node] = true;
        path.add(node);

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor] && dfs(neighbor, visited, recStack, graph, path))
                return true;
            else if (recStack[neighbor]) {
                path.add(neighbor); // for printing the cycle
                return true;
            }
        }

        recStack[node] = false;
        path.remove(path.size() - 1);
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
        boolean[] recStack = new boolean[n];
        List<Integer> path = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i] && dfs(i, visited, recStack, graph, path)) {
                System.out.println("Cycle detected: " + path);
                return;
            }
        }

        System.out.println("No cycle found.");
    }
}
