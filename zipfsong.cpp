#include <bits/stdc++.h>

using namespace std;

bool Compare(tuple<long,string,int> x, tuple<long,string,int> y) {
    if (get<0>(x) == get<0>(y)) {
        return get<2>(x) > get<2>(y);
    } else return get<0>(x) < get<0>(y);
}

int main() {
    int n, m;
    cin >> n >> m;
    vector<long> frequencies(n);
    vector<string> names(n);
    for (int i = 0; i < n; i++) {
        long f;
        string name;
        cin >> f >> name;
        frequencies[i] = f;
        names[i] = name;
    }
    vector<tuple<long,string,int>> quality(n);
    for (int i = 0; i < n; i++) {
        quality[i] = tuple<long,string,int> {frequencies[i] * (i + 1), names[i], i};
    }

    priority_queue<tuple<long,string,int>,vector<tuple<long,string,int>>,function<decltype(Compare)>> pq(quality.begin(), quality.end(), Compare);
    for (int i = 0; i < m; i++) {
        cout << get<1>(pq.top()) << endl;
        pq.pop();
    }

    return 0;
}