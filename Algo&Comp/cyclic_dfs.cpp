#include <iostream>
#include <vector>

using namespace std;

bool dfsCycle(int node, vector<vector<int>>& graph, vector<bool>& visited, vector<bool>& recStack, vector<int>& path) {
    visited[node] = true;
    recStack[node] = true;
    path.push_back(node);

    for (int neighbor = 0; neighbor < graph[node].size(); ++neighbor) {
        if (graph[node][neighbor]) {
            if (!visited[neighbor] && dfsCycle(neighbor, graph, visited, recStack, path))
                return true;
            else if (recStack[neighbor]) {
                path.push_back(neighbor);
                return true;
            }
        }
    }

    path.pop_back();
    recStack[node] = false;
    return false;
}

int main() {
    int n;
    cout << "Enter number of vertices: ";
    cin >> n;

    vector<vector<int>> graph(n, vector<int>(n));
    cout << "Enter adjacency matrix:\n";
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            cin >> graph[i][j];

    vector<bool> visited(n, false);
    vector<bool> recStack(n, false);
    vector<int> path;

    for (int i = 0; i < n; ++i) {
        if (!visited[i] && dfsCycle(i, graph, visited, recStack, path)) {
            cout << "Cycle found: ";
            for (int v : path) cout << v << " ";
            cout << endl;
            return 0;
        }
    }

    cout << "No cycle found.\n";
    return 0;
}
