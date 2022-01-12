#include <bits/stdc++.h>

using namespace std;

class UFDS {
    public:
        vector<int> p, rank, size;
    
        UFDS(int n) {
            p = vector<int>(n, 0);
            rank = vector<int>(n, 0);
            size = vector<int>(n, 1);
            for (int i = 0; i < n; i++) p[i] = i;
        }

        int findSet(int i) {
            if (p[i] == i) return i;
            else {
                p[i] = findSet(p[i]);
                return p[i];
            }
        }

        bool isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                int x = findSet(i), y = findSet(j);
                if (rank[x] > rank[y]) {
                    p[y] = x;
                    size[x] += size[y];
                } else {
                    p[x] = y;
                    if (rank[x] == rank[y]) rank[y] = rank[x] + 1;
                    size[y] += size[x];
                }
            }
        }
};

int main() {
    int testcases;
    cin >> testcases;
    for (int i = 0; i < testcases; i++) {
        int F;
        cin >> F;
        unordered_map<string, int> nametable;
        int id = 0;
        UFDS network = UFDS(100000);
        for (int i = 0; i < F; i++) {
            string x, y;
            cin >> x >> y;
            if (nametable.find(x) == nametable.end()) {
                nametable[x] = id++;
            }
            if (nametable.find(y) == nametable.end()) {
                nametable[y] = id++;
            }
            int x_id = nametable[x];
            int y_id = nametable[y];
            network.unionSet(x_id, y_id);
            cout << network.size[network.findSet(x_id)] << endl;
        }
    }

    return 0;
}