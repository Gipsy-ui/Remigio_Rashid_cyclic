#include <iostream>
#include <vector>
#include <queue>

using namespace std;

bool bfsCycle(int start, vector<vector<int>>& graph, vector<bool>& visited) {
    vector<int> parent(graph.size(), -1);
    queue<int> q;

    visited[start] = true;
    q.push(start);

    while (!q.empty()) {
        int node = q.front();
        q.pop();

        for (int neighbor = 0; neighbor < graph[node].size(); ++neighbor) {
            if (graph[node][neighbor]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    q.push(neighbor);
                } else if (parent[node] != neighbor) {
                    cout << "Cycle found involving: " << node << " and " << neighbor << endl;
                    return true;
                }
            }
        }
    }

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
    for (int i = 0; i < n; ++i) {
        if (!visited[i] && bfsCycle(i, graph, visited)) {
            return 0;
        }
    }

    cout << "No cycle found.\n";
    return 0;
}
