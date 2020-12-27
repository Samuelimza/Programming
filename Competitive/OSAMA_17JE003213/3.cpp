//Solution to question 3 of Graphs Assignment
//Implement DFS for an Directed graph taking 1 as root.
//Osama Azmi

#include <iostream>
#include <vector>
using namespace std;

vector <int> adj[100];
bool visited[100];

void dfs(int s) {
    visited[s] = true;
    for(int i = 0;i < adj[s].size();i++) {
        if(visited[adj[s][i]] == false)
            dfs(adj[s][i]);
    }    
}

void initialize() {
    for(int i = 0;i < 100;++i)
        visited[i] = false;
}

int main() {
    int nodes, edges, x, y;
    cin >> nodes >> edges;
    for(int i = 0;i < edges;i++) {
        cin >> x >> y;     
        adj[x].push_back(y);
    }
    initialize();
    dfs(1);
    cout << "DFS complete.";
    for(int i = 0;i < nodes;i++) {
        if(!visited[i]) {
            cout << "Graph could not be fully explored";
            cin.get();
            cin.get();
            return 0;
        }
    }
    cout << "Graph fully explored";
    cin.get();
    cin.get();
}