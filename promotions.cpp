#include <bits/stdc++.h>
using namespace std;

int main() {
    int a, b, e, p;
    cin >> a >> b >> e >> p;
    vector<vector<int>> parents(e);
    vector<vector<int>> children(e);
    vector<int> indeg(e,0);
    vector<int> above(e,0);
    vector<int> aboveOrEqual(e,0);
    for (int i = 0; i < p; i++) {
        int x, y;
        cin >> x >> y;
        children[x].push_back(y);
        parents[y].push_back(x);
        indeg[y] += 1;
    }

    queue<int> degzeroes;
    for (int i = 0; i < e; i++) {
        if (indeg[i] == 0) degzeroes.push(i);
    }

    for (int i = 0; i < e; i++) {
        queue<int> q(degzeroes);
        vector<int> indeg2(indeg);
        while (!q.empty()) {
            int curr = q.front();
            q.pop();
            if (curr == i) continue;
            aboveOrEqual[i] += 1;
            for (int child : children[curr]) {
                indeg2[child] -= 1;
                if (indeg2[child] == 0) {
                    q.push(child);
                }
            }
        }
    }

    for (int i = 0; i < e; i++) {
        queue<int> q;
        q.push(i);
        vector<int> visited(e,0);
        visited[i] = 1;
        while (!q.empty()) {
            int curr = q.front();
            q.pop();
            for (int parent : parents[curr]) {
                if (!visited[parent]) {
                    above[i] += 1;
                    visited[parent] = 1;
                    q.push(parent);
                }
            }
        }
    }

    int promoteA = 0;
    int promoteB = 0;
    int notPromoting = 0;

    for (int i = 0; i < e; i++) {
        if (aboveOrEqual[i] < a) promoteA += 1;
    }
    for (int i = 0; i < e; i++) {
        if (aboveOrEqual[i] < b) promoteB += 1;
    }
    for (int i = 0; i < e; i++) {
        if (above[i] >= b) notPromoting += 1;
    }

    cout << promoteA << '\n' << promoteB << '\n' << notPromoting << endl;

    return 0;
}