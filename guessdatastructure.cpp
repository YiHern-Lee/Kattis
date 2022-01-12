#include <bits/stdc++.h>

using namespace std;

int main() {
    int n;
    while (cin >> n) {
        stack<int> s;
        queue<int> q;
        priority_queue<int> pq;
        bool is_s = true, is_q = true, is_pq = true;
        for (int i = 0; i < n; i++) {
            int type, x;
            cin >> type >> x;
            if (type == 1) {
                if (is_s) s.push(x);       
                if (is_q) q.push(x);
                if (is_pq) pq.push(x);
            } else if (type == 2) {
                if (is_s) {
                    if (s.size() <= 0 || s.top() != x) is_s = false;
                    else s.pop();
                }
                if (is_q) {
                    if (q.size() <= 0 || q.front() != x) is_q = false;
                    else q.pop();
                }
                if (is_pq) {
                    if (pq.size() <= 0 || pq.top() != x) is_pq = false;
                    else pq.pop();
                }
            }
        }
        if (is_s || is_q || is_pq) {
            if (is_s && !is_q && !is_pq) {
                cout << "stack" << endl;
            } else if (!is_s && is_q && !is_pq) {
                cout << "queue" << endl;
            } else if (!is_s && !is_q && is_pq) {
                cout << "priority queue" << endl;
            } else {
                cout << "not sure" << endl;
            }
        } else {
            cout << "impossible" << endl;
        }
    }
    return 0;
}