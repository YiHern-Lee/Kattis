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
    int N, K;
    cin >> N >> K;
    UFDS u = UFDS(N);
    for (int i = 0; i < K; i++) {
        int x, y;
        cin >> x >> y;
        u.unionSet(x - 1, y - 1);
    }
    
    int start = 0, end = N - 1;
    bool sortable = true;
    while (start < end) {
        if (!u.isSameSet(start, end)) {
            sortable = false;
            break;
        }
        start++;
        end--;
    }

    if (sortable) cout << "Yes";
    else cout << "No";
    cout << endl;

    return 0;
}