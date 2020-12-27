//Solution to question 2 of Graphs Assignment
//Implement BFS for a tree taking 1 as root.
//Osama Azmi

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector <int> adj[100];
bool visited[100];

void bfs(int s) {
    queue <int> q;
    q.push(s);
    visited[s] = true;
    while(!q.empty()){
        int p = q.front();
        q.pop();
        for(int i = 0;i < adj[p].size() ; i++){
            if(visited[adj[p][i]] == false){
                q.push(adj[p][i]);
                visited[adj[p][i]] = true;
            }
        }
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
        adj[y].push_back(x);
    }
    initialize();
    bfs(1);
    cout << "BFS complete.";
    for(int i = 1;i <= nodes;i++) {
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